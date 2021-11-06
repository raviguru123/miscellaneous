package nurture.service;

import lombok.NonNull;
import nurture.exception.FileNotFoundException;
import nurture.exception.FileSaveException;
import nurture.exception.MemoryNotAvailableException;
import nurture.models.Block;
import nurture.models.File;
import nurture.models.Memory;
import nurture.strategy.IMemoryAllocationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManagement {
    private static FileManagement fileManagement = null;
    private IMemoryAllocationStrategy iMemoryAllocationStrategy;
    private List<Memory> memoryList;
    private Map<String, File> fileMap;

    public static FileManagement getInstance(@NonNull IMemoryAllocationStrategy iMemoryAllocationStrategy) {
        if (fileManagement == null) {
            synchronized (FileManagement.class) {
                if (fileManagement == null) {
                    fileManagement = new FileManagement((iMemoryAllocationStrategy));
                }
            }
        }
        return fileManagement;
    }

    private FileManagement(@NonNull IMemoryAllocationStrategy iMemoryAllocationStrategy) {
        this.iMemoryAllocationStrategy = iMemoryAllocationStrategy;
        this.memoryList = new ArrayList<>();
        this.fileMap = new HashMap<>();
    }

    public void addMemory(@NonNull Memory memory) {
        this.memoryList.add(memory);
    }

    public synchronized File createFile(@NonNull final String name) {
        File file = new File(name);
        this.fileMap.put(name, file);
        return file;
    }

    public void writeFile(@NonNull String fileName, @NonNull final String content) {
        if (!this.fileMap.containsKey(fileName)) {
            throw new FileNotFoundException(fileName);
        }
        File file = this.fileMap.get(fileName);
        showMemoryStats();
        for (Memory memory : this.memoryList) {
            List<Block> blockList = memory.getBlockList();
            if (iMemoryAllocationStrategy.isMemoryBlocksAvailable(content, blockList)) {
                if (this.iMemoryAllocationStrategy.allocateBlocks(file, content, blockList)) {
                    System.out.println("File successfully saved in system");
                    showMemoryStats();
                    return;
                }
                throw new FileSaveException();
            }
            throw new MemoryNotAvailableException();
        }
    }

    public String readFile(@NonNull final String fileName) {
        if (!this.fileMap.containsKey(fileName)) {
            throw new FileNotFoundException(fileName);
        }
        File file = this.fileMap.get(fileName);
        String content = this.iMemoryAllocationStrategy.mergeContent(file, this.memoryList.get(0).getBlockList());
        return content;
    }

    public void updateFile(@NonNull final String fileName, String content) {
        if (!this.fileMap.containsKey(fileName)) {
            throw new FileNotFoundException(fileName);
        }
        File file = this.fileMap.get(fileName);
        if (this.iMemoryAllocationStrategy.updateFileContent(file, content, this.memoryList.get(0).getBlockList())) {
            System.out.println("File " + fileName + " updated successfully");
        }
        this.showMemoryStats();
    }

    public void deleteFile(@NonNull final String fileName) {
        if (!this.fileMap.containsKey(fileName)) {
            throw new FileNotFoundException(fileName);
        }
        File file = this.fileMap.get(fileName);
        boolean isDeleted = this.iMemoryAllocationStrategy.deAllocateBlocks(file, this.memoryList.get(0).getBlockList());
        if (isDeleted) {
            System.out.println("file " + fileName + " deleted successfully");
            this.fileMap.remove(fileName);
            showMemoryStats();
        }
    }

    public void showMemoryStats() {
        for (Memory memory : this.memoryList) {
            memory.showMemoryStats();
        }
    }
}
