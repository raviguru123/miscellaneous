package LockerManagement.service;

import LockerManagement.model.LockerUser;
import LockerManagement.model.Slot;
import lombok.AllArgsConstructor;
import lombok.NonNull;


public class NotificationService {
    public void notifyUser(@NonNull LockerUser lockerUser, @NonNull final String otp, @NonNull final Slot slot) {
        System.out.println("Otp send successfully otp :" + otp + "  lockerUser :" + lockerUser.getContact().getEmail() + " slot :" + slot.getSlotId());
    }
}
