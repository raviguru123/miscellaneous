package LockerManagement.repository;

import LockerManagement.model.Locker;
import LockerManagement.model.Slot;
import lombok.NonNull;

import java.util.List;

public interface ILockerRepository {
    public Locker createLocker(@NonNull final String id);

    public List<Slot> getAllAvailableSlots();
}
