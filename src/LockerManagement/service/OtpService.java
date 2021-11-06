package LockerManagement.service;

import LockerManagement.model.Slot;
import LockerManagement.repository.ISlotOtpRepository;
import LockerManagement.strategies.IOtpGenerator;
import lombok.NonNull;

public class OtpService {
    private final ISlotOtpRepository iSlotOtpRepository;
    private final IOtpGenerator iOtpGenerator;

    public OtpService(@NonNull final ISlotOtpRepository iSlotOtpRepository, @NonNull final IOtpGenerator iOtpGenerator) {
        this.iSlotOtpRepository = iSlotOtpRepository;
        this.iOtpGenerator = iOtpGenerator;
    }

    public String generateOtp(@NonNull final Slot slot) {
        String otp = this.iOtpGenerator.generateOtp();
        iSlotOtpRepository.addOtp(slot.getSlotId(), otp);
        return otp;
    }

    public boolean validateOtp(@NonNull final Slot slot, @NonNull final String otp) {
        String saveOtp = iSlotOtpRepository.getOtp(slot.getSlotId());
        return saveOtp != null && saveOtp.equals(otp);
    }
}
