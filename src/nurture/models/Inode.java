package nurture.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@ToString
public class Inode {
    private Date createdAt;

    @Setter
    private Date updatedAt;
    @Setter
    private Integer start;
    @Setter
    private Integer size;

    public Inode() {
        this.createdAt = new Date();
        this.updatedAt = null;
        this.size = 0;
        this.start = -1;
    }
}
