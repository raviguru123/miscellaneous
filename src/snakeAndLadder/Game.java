package snakeAndLadder;

import lombok.NonNull;
import snakeAndLadder.model.Board;
import snakeAndLadder.model.GameStatus;
import snakeAndLadder.model.Player;
import snakeAndLadder.model.Snake;
import snakeAndLadder.strategy.Dice;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Dice dice;
    private Queue<Player> playerQueue;
    private GameStatus gameStatus;

    public Game(@NonNull final Board board, @NonNull final Dice dice, @NonNull final List<Player> playerList) {
        this.board = board;
        this.dice = dice;
        this.playerQueue = new ArrayDeque<>(playerList);
        this.gameStatus = GameStatus.NOTSTARTED;
    }

    public Player getNextPlayer() {
        if (this.playerQueue.size() > 0) {
            return this.playerQueue.poll();
        }
        return null;
    }

    public void start() {
        this.gameStatus = GameStatus.STARTED;
        while (playerQueue.size() > 0 && this.gameStatus != GameStatus.END) {
            Player player = this.getNextPlayer();
        }
    }

    public void play(Player player) {

    }

    public boolean checkPlayerWin(Player player) {
        if (player.getPosition() == this.board.getSize()) {
            return true;
        }
        return false;
    }

}
