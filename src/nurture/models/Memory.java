package nurture.models;

import lombok.Getter;
import lombok.NonNull;
import nurture.exception.InvalidMemoryValue;
import nurture.strategy.IMemorySegmentStrategy;

import java.util.List;

@Getter
public class Memory {
    private List<Block> blockList;
    private IMemorySegmentStrategy iMemorySegmentStrategy;

    public Memory(@NonNull final int memorySize, @NonNull final int blockSize, @NonNull IMemorySegmentStrategy iMemorySegmentStrategy) {
        if (memorySize <= 0) {
            throw new InvalidMemoryValue();
        }
        this.iMemorySegmentStrategy = iMemorySegmentStrategy;
        this.blockList = this.iMemorySegmentStrategy.divideMemoryIntoBlocks(memorySize, blockSize);
    }

    public void showMemoryStats() {
        int freeMemory = 0;
        int blockSize = this.blockList.get(0).getSize();
        for (Block block : this.blockList) {
            if (block.isFree()) {
                freeMemory += 1;
            }
        }
        System.out.println("Total memory available in slot : " + freeMemory * blockSize);
    }
}
