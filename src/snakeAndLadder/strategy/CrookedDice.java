package snakeAndLadder.strategy;

import java.util.Random;

public class CrookedDice implements Dice {
    @Override
    public int rollDice() {
        return 2+ new Random().nextInt(3) * 2;
    }
}
