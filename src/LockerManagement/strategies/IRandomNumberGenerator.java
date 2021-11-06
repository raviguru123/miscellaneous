package LockerManagement.strategies;

import lombok.NonNull;

public interface IRandomNumberGenerator {
    @NonNull
    public Integer getLessThanRandomNumber(@NonNull Integer lessThan);
}
