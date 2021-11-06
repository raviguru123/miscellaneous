package snakeAndLadder.model;

import lombok.NonNull;
import snakeAndLadder.exceptions.InvalidPositionException;

public class Snake extends Move {
    public Snake(@NonNull final Integer nextPosition) {
        super(nextPosition);
    }

    public boolean isValidPosition(Integer cellPosition) throws InvalidPositionException {
        if (cellPosition > getNextPosition()) {
            return true;
        }
        throw new InvalidPositionException("Cell Position always greater than snake position");
    }
}
