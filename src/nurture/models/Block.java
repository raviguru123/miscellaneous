package nurture.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Block {
    private boolean isFree;
    private final Integer size;
    private String content;

    public Block(@NonNull final Integer size) {
        this.isFree = true;
        this.size = size;
        this.content = null;
    }

    public void setContent(@NonNull final String content) {
        this.isFree = false;
        this.content = content;
    }
}
