package LockerManagement.strategies;

import lombok.NonNull;

public interface IOtpGenerator {
    @NonNull
    public String generateOtp();
}
