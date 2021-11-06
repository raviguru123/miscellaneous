package LockerManagement.repository;

import LockerManagement.exceptions.LockerAlreadyExistException;
import LockerManagement.model.Locker;
import LockerManagement.model.Slot;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LocakerRepositoryInMemory implements ILockerRepository {
    List<Locker> lockerList;
    public LocakerRepositoryInMemory() {
        this.lockerList = new ArrayList<>();
    }
    public Locker getLocker(@NonNull final String id) {
        for (Locker locker : this.lockerList) {
            if (locker.getId().equals(id)) {
                return locker;
            }
        }
        return null;
    }

    public Locker createLocker(@NonNull final String id) {
        if (this.getLocker(id) != null) {
            throw new LockerAlreadyExistException();
        }
        final Locker locker = new Locker(id);
        this.lockerList.add(locker);
        return locker;
    }

    public List<Slot> getAllAvailableSlots() {
        List<Slot> result = new ArrayList<>();
        for (Locker locker : this.lockerList) {
            result.addAll(locker.getAvailableSlots());
        }
        return result;
    }
}
