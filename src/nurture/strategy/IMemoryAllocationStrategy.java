package nurture.strategy;

import lombok.NonNull;
import nurture.models.Block;
import nurture.models.File;
import nurture.models.Memory;

import java.util.ArrayList;
import java.util.List;

public interface IMemoryAllocationStrategy {
    public boolean isMemoryBlocksAvailable(@NonNull final String content, @NonNull Memory memory);

    public boolean allocateBlocks(@NonNull File file, @NonNull final String content, @NonNull String slot, @NonNull Memory memory);

    public boolean deAllocateBlocks(@NonNull File file, List<Block> blockList);

    public boolean updateFileContent(@NonNull File file, @NonNull final String content, @NonNull Memory memory);

    public String mergeContent(@NonNull File file, @NonNull List<Block> blockList);

    public void markBlockFree(@NonNull Integer start, @NonNull Integer end, List<Block> blockList);
}
