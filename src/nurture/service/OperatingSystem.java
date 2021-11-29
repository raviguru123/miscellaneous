package nurture.service;

import lombok.NonNull;
import nurture.models.File;
import nurture.models.Memory;
import nurture.strategy.IMemoryAllocationStrategy;
import nurture.strategy.IMemorySegmentStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class OperatingSystem<T> {
    FileManagement fileManagement;
    IMemorySegmentStrategy iMemorySegmentStrategy;
    IMemoryAllocationStrategy iMemoryAllocationStrategy;
    ExecutorService executor = Executors.newFixedThreadPool(5);


    public OperatingSystem(@NonNull IMemorySegmentStrategy iMemorySegmentStrategy, @NonNull IMemoryAllocationStrategy iMemoryAllocationStrategy) {
        this.iMemorySegmentStrategy = iMemorySegmentStrategy;
        this.iMemoryAllocationStrategy = iMemoryAllocationStrategy;
        this.fileManagement = FileManagement.getInstance(this.iMemoryAllocationStrategy);
    }

    public void addMemory(@NonNull Memory memory, @NonNull final String slot) {
        fileManagement.addMemory(memory, slot);
    }

    public void removeMemory(@NonNull final String slot) {

    }

    public File createFile(@NonNull final String fileName) {
        return null;
    }

    public T readFile(@NonNull final String name) {
        return null;
    }

    public File writeFile(@NonNull final String fileName, T content) {
        return null;
    }

    public File updateFile(@NonNull final String fileName, T content) {
        return null;
    }

    public boolean deleteFile(@NonNull final String fileName) {
        return true;
    }
}
