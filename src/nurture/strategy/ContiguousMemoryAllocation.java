package nurture.strategy;

import lombok.NonNull;
import lombok.Synchronized;
import nurture.exception.MemoryNotAvailableException;
import nurture.models.Block;
import nurture.models.File;
import nurture.models.Inode;
import nurture.models.Memory;
import nurture.utils.FileAllocationUtils;

import java.util.Date;
import java.util.List;

public class ContiguousMemoryAllocation implements IMemoryAllocationStrategy {

    @Override
    public boolean isMemoryBlocksAvailable(@NonNull final String Content, @NonNull Memory memory) {
        List<Block> blockList = memory.getBlockList();
        int start = this.getAvailableBlocks(Content.length(), blockList);
        if (start > -1) {
            return true;
        }
        return false;
    }

    public synchronized boolean allocateBlocks(@NonNull File file, @NonNull final String content, @NonNull final String slot, @NonNull Memory memory) {
        List<Block> blockList = memory.getBlockList();
        int start = this.getAvailableBlocks(content.length(), blockList);
        if (start > -1) {
            List<String> splitContentList = FileAllocationUtils.splitContents(content, blockList.get(start).getSize());
            int blockNumber = start;
            for (String splitContent : splitContentList) {
                Block block = blockList.get(blockNumber);
                block.setContent(splitContent);
                blockNumber += 1;
            }
            Inode inode = file.getInode();
            inode.setUpdatedAt(new Date());
            inode.setStart(start);
            inode.setSize(splitContentList.size());
            inode.setSlot(slot);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean deAllocateBlocks(@NonNull File file, List<Block> blockList) {
        Inode inode = file.getInode();
        int start = inode.getStart();
        int end = start + inode.getSize();
        while (start < end) {
            Block block = blockList.get(start);
            block.setFree(true);
            start += 1;
        }
        return true;
    }

    @Override
    public String mergeContent(@NonNull File file, List<Block> blockList) {

        Inode inode = file.getInode();
        int start = inode.getStart().intValue();
        int end = start + inode.getSize();
        StringBuilder splitFileContent = new StringBuilder();
        while (start < end) {
            splitFileContent.append(blockList.get(start).getContent());
            start += 1;
        }
        return splitFileContent.toString();
    }

    private int getAvailableBlocks(@NonNull final int size, List<Block> blockList) {
        if (blockList.size() > 0) {
            int requireBlocks = FileAllocationUtils.getRequireBlocks(size, blockList.get(0));

            int availableBlocks = 0;
            int start = 0;
            int index = 0;

            for (Block block : blockList) {

                if (block.isFree()) {
                    availableBlocks += 1;
                } else {
                    start = index + 1;
                    availableBlocks = 0;
                }

                if (availableBlocks == requireBlocks) {
                    return start;
                }
                index += 1;
            }
        }
        return -1;
    }

    @Override
    public synchronized boolean updateFileContent(@NonNull File file, @NonNull final String fileContent, @NonNull Memory memory) {
        List<Block> blockList = memory.getBlockList();
        List<String> splitContentList = FileAllocationUtils.splitContents(fileContent, blockList.get(0).getSize());
        if (isFileSizeIncrease(file, fileContent, blockList)) { // if updated content is larger than existing content
            if (checkAdjacentMemoryBlocksAvailable(file, fileContent, blockList)) { // check if extra blocks needed is available adjacent to existing file content
                int start = file.getInode().getStart();
                for (String content : splitContentList) {
                    blockList.get(start).setContent(content);
                    start += 1;
                }
                return true;
            }
            return false;
        } else { // if file content is less than previous content update existing block and mark residual block free.
            int start = file.getInode().getStart();
            int end = file.getInode().getStart() + file.getFileSize();

            for (String content : splitContentList) {
                blockList.get(start).setContent(content);
                start += 1;
            }
            markBlockFree(start, end, blockList);
        }
        file.getInode().setSize(splitContentList.size());
        return true;
    }

    public void markBlockFree(@NonNull Integer start, @NonNull Integer end, List<Block> blockList) {
        while (start < end && start < blockList.size()) {
            blockList.get(start).setFree(true);
            start += 1;
        }
    }

    public boolean isFileSizeIncrease(@NonNull File file, @NonNull final String content, @NonNull List<Block> blockList) {
        if (blockList.size() > 0) {
            List<String> splitContentList = FileAllocationUtils.splitContents(content, blockList.get(0).getSize());
            int fileSize = file.getFileSize();
            return splitContentList.size() > fileSize;
        }
        throw new MemoryNotAvailableException();
    }

    public boolean checkAdjacentMemoryBlocksAvailable(@NonNull File file, @NonNull final String content, @NonNull List<Block> blockList) {
        if (this.isFileSizeIncrease(file, content, blockList)) {
            int existingFileSize = blockList.get(0).getSize() * file.getFileSize();
            int newFileSize = content.length();
            int requiredSize = newFileSize - existingFileSize;
            int start = file.getInode().getStart();
            int existingSize = file.getInode().getSize();
            int startIndex = start + existingSize;
            int freeBlock = 0;
            while (freeBlock < requiredSize) {
                if (blockList.get(startIndex).isFree()) {
                    freeBlock += 1;
                } else {
                    break;
                }
                startIndex += 1;
            }

            if (freeBlock == requiredSize) {
                return true;
            }
            return false;
        }
        return true;
    }
}
