package snakeAndLadder.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private final Integer currentPosition;
    private Move move;

    public Cell(@NonNull final Integer currentPosition) {
        this.currentPosition = currentPosition;
        this.move = new DefaultMove();
    }

    public Integer getNextPosition() {
        if (move.getNextPosition() == 0) {
            return this.currentPosition;
        }
        return move.getNextPosition();
    }
}
