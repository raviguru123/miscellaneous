package nurture.strategy;

import lombok.NonNull;
import nurture.models.Block;

import java.util.ArrayList;
import java.util.List;

public class MemorySegmentStrategy implements IMemorySegmentStrategy {

    public List<Block> divideMemoryIntoBlocks(@NonNull final int memorySize, @NonNull final int blockSize) {

        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < memorySize / blockSize; i++) {
            blockList.add(new Block(blockSize));
        }
        return blockList;
    }
}
