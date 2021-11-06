package LockerManagement.repository;

import lombok.NonNull;

public interface ISlotOtpRepository {
    public void addOtp(@NonNull final String otp, @NonNull final String id);

    public String getOtp(@NonNull final String otp);
}
