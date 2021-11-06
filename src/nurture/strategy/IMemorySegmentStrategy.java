package nurture.strategy;

import lombok.NonNull;
import nurture.models.Block;

import java.util.List;

public interface IMemorySegmentStrategy {
    public List<Block> divideMemoryIntoBlocks(@NonNull final int memorySize, @NonNull final int blockSize);
}
