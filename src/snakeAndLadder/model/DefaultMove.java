package snakeAndLadder.model;

import snakeAndLadder.exceptions.InvalidPositionException;

public class DefaultMove extends Move {

    public DefaultMove() {
        super(0);
    }

    @Override
    public boolean isValidPosition(Integer cellPosition) throws InvalidPositionException {
        return false;
    }
}
