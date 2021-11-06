package nurture.strategy;

import lombok.NonNull;
import nurture.models.Block;
import nurture.models.File;

import java.util.ArrayList;
import java.util.List;

public interface IMemoryAllocationStrategy {
    public boolean isMemoryBlocksAvailable(@NonNull final String content, List<Block> blockList);

    public boolean allocateBlocks(@NonNull File file, @NonNull final String content, List<Block> blockList);

    public boolean deAllocateBlocks(@NonNull File file, List<Block> blockList);

    public boolean updateFileContent(@NonNull File file, @NonNull final String content, @NonNull List<Block> blockList);

    public String mergeContent(@NonNull File file, @NonNull List<Block> blockList);
}
