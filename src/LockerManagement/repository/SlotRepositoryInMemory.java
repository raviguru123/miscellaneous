package LockerManagement.repository;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SlotRepositoryInMemory implements ISlotOtpRepository {
    Map<String, String> slotIdToOtpMap;

    public SlotRepositoryInMemory() {
        this.slotIdToOtpMap = new HashMap<>();
    }

    @Override
    public void addOtp(@NonNull final String id, @NonNull final String otp) {
        this.slotIdToOtpMap.put(id, otp);
    }

    @Override
    public String getOtp(@NonNull final String otp) {
        return this.slotIdToOtpMap.get(otp);
    }
}
