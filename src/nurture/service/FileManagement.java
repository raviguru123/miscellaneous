package nurture.service;

import lombok.NonNull;
import nurture.exception.FileNotFoundException;
import nurture.exception.MemoryNotAvailableException;
import nurture.exception.MemoryNotExistInSlot;
import nurture.models.File;
import nurture.models.Memory;
import nurture.strategy.IMemoryAllocationStrategy;

import java.util.HashMap;
import java.util.Map;

public class FileManagement {
    private static FileManagement fileManagement = null;
    private IMemoryAllocationStrategy iMemoryAllocationStrategy;
    private Map<String, Memory> memoryMap;
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
        this.memoryMap = new HashMap<>();
        this.fileMap = new HashMap<>();
    }

    public void addMemory(@NonNull Memory memory, @NonNull final String slot) {
        this.memoryMap.put(slot, memory);
    }

    public synchronized File createFile(@NonNull final String name) {
        File file = new File(name);
        this.fileMap.put(name, file);
        return file;
    }

    public String readFile(@NonNull final String fileName) {
        File file = this.getFileObject(fileName);
        Memory memory = this.memoryMap.get(file.getInode().getSlot());
        String content = this.iMemoryAllocationStrategy.mergeContent(file, memory.getBlockList());
        return content;
    }


    public synchronized void writeFile(@NonNull String fileName, @NonNull final String content) {
        File file = this.getFileObject(fileName);
        showMemoryStats();
        for (Map.Entry<String, Memory> memoryEntry : this.memoryMap.entrySet()) {
            if (iMemoryAllocationStrategy.isMemoryBlocksAvailable(content, memoryEntry.getValue())) {
                if (this.iMemoryAllocationStrategy.allocateBlocks(file, content, memoryEntry.getKey(), memoryEntry.getValue())) {
                    System.out.println("File " + fileName + " successfully saved in system ====== " + memoryEntry.getKey());
                    showMemoryStats();
                    return;
                }
            }
        }
        throw new MemoryNotAvailableException();
    }

    public void updateFile(@NonNull final String fileName, String content) throws CloneNotSupportedException {
        File file = this.getFileObject(fileName);
        Memory memory = this.getMemory(fileName);
        if (this.iMemoryAllocationStrategy.updateFileContent(file, content, memory)) {
            System.out.println("File " + fileName + " Updated successfully");
        } else {
            int prevPos = file.getInode().getStart();
            int size = file.getInode().getSize();
            this.writeFile(fileName, content);
            this.iMemoryAllocationStrategy.markBlockFree(prevPos, prevPos + size, memory.getBlockList());
        }
        this.showMemoryStats();
    }

    public void deleteFile(@NonNull final String fileName) {
        File file = this.getFileObject(fileName);
        Memory memory = this.getMemory(fileName);
        boolean isDeleted = this.iMemoryAllocationStrategy.deAllocateBlocks(file, memory.getBlockList());
        if (isDeleted) {
            System.out.println("file " + fileName + " deleted successfully");
            this.fileMap.remove(fileName);
            showMemoryStats();
        }
    }

    public File getFileObject(@NonNull final String fileName) {
        if (!this.fileMap.containsKey(fileName)) {
            throw new FileNotFoundException(fileName);
        }
        return this.fileMap.get(fileName);
    }

    private Memory getMemory(@NonNull final String fileName) {
        File file = this.getFileObject(fileName);
        final String slot = file.getInode().getSlot();
        if (!this.memoryMap.containsKey(slot)) {
            throw new MemoryNotExistInSlot();
        }
        return this.memoryMap.get(slot);
    }

    public void showMemoryStats() {
        System.out.println();
        for (Map.Entry<String, Memory> memoryEntry : this.memoryMap.entrySet()) {
            System.out.print(memoryEntry.getKey() + "   ");
            memoryEntry.getValue().showMemoryStats();
        }
        System.out.println();
    }
}
