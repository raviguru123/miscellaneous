package snakeAndLadder.strategy;

import java.util.Random;

public class NormalDice implements Dice {
    @Override
    public int rollDice() {
        return new Random().nextInt(6) + 1;
    }
}
