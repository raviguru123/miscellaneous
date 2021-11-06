package nurture.utils;

import lombok.NonNull;
import nurture.models.Block;

import java.util.ArrayList;
import java.util.List;

public class FileAllocationUtils {
    public static List<String> splitContents(@NonNull String content, @NonNull final Integer size) {
        List<String> output = new ArrayList<>();
        for (int start = 0; start < content.length(); start = start + size) {
            output.add(content.substring(start, start + size < content.length() ? start + size : content.length()));
        }
        return output;
    }

    public static Integer getRequireBlocks(@NonNull final int size, Block block) {
        return (int) Math.ceil((float) size / block.getSize());
    }
}
