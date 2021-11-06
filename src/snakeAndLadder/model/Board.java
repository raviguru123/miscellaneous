package snakeAndLadder.model;

import lombok.Getter;
import lombok.NonNull;
import snakeAndLadder.exceptions.InvalidPositionException;

import java.util.List;
import java.util.Optional;


public class Board {
    private List<Cell> cells;
    @Getter
    private final Integer size;

    public Board(@NonNull final Integer size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.cells.add(new Cell(i + 1));
        }
    }

    public int movetoNextPosition(int currentPosition, int score) {
        Optional<Cell> currentCell = getNextCellByPosition(currentPosition + score);
        return currentCell.map(Cell::getNextPosition).orElse(currentPosition);
    }

    public Optional<Cell> getNextCellByPosition(int position) {
        return this.cells.stream().filter(cell -> cell.getCurrentPosition() == position).findFirst();
    }

    public void addSnake(final int currentCellPos, final int endPosition) throws InvalidPositionException {
        Cell cell = this.cells.get(currentCellPos);
        Snake snake = new Snake(endPosition);
        if (snake.isValidPosition(currentCellPos)) {
            cell.setMove(snake);
        }
    }
}
