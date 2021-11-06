package snakeAndLadder.model;

import lombok.Getter;
import lombok.NonNull;
import snakeAndLadder.exceptions.InvalidPositionException;

@Getter
public abstract class Move {
    private final Integer nextPosition;

    public Move(@NonNull final Integer nextPosition) {
        this.nextPosition = nextPosition;
    }

    public abstract boolean isValidPosition(Integer cellPosition) throws InvalidPositionException;
}
