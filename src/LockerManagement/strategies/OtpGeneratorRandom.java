package LockerManagement.strategies;

import lombok.NonNull;

public class OtpGeneratorRandom implements IOtpGenerator {
    private final int otpLength;
    private final IRandomNumberGenerator randomNumberGenerator;

    public OtpGeneratorRandom(@NonNull final Integer otpLength, @NonNull final IRandomNumberGenerator randomNumberGenerator) {
        this.otpLength = otpLength;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @NonNull
    public String generateOtp() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.otpLength; i++) {
            stringBuilder.append(randomNumberGenerator.getLessThanRandomNumber(10));
        }
        return stringBuilder.toString();
    }
}
