package nurture.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class File {
    private final String name;
    private Inode inode;

    public File(@NonNull final String name) {
        this.name = name;
        this.inode = new Inode();
    }

    public int getFileSize() {
        return this.inode.getSize();
    }
}
