package LockerManagement.strategies;

import lombok.NonNull;

public class RandomGeneratorDefault implements IRandomNumberGenerator {
    public Integer getLessThanRandomNumber(@NonNull Integer lessThan) {
        return (int) (Math.random() * lessThan);
    }
}
