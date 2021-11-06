package LockerManagement.model;

import LockerManagement.exceptions.SlotAlreadyOccupiedException;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class Slot {
    private final String slotId;
    private final Size size;
    private final Locker locker;
    private LockerItem lockerItem;
    private Date allocationDate;

    public Slot(@NonNull final String slotId, @NonNull final Size size, @NonNull final Locker locker) {
        this.slotId = slotId;
        this.size = size;
        this.locker = locker;
        this.lockerItem = null;
    }

    public void allocatePackage(@NonNull final LockerItem lockerItem) {
        if (this.lockerItem != null) {
            throw new SlotAlreadyOccupiedException();
        }
        this.lockerItem = lockerItem;
        this.allocationDate = new Date();
    }

    public void deallocateSlot() {
        this.lockerItem = null;
    }

    public boolean isSlotAvailable() {
        return this.lockerItem == null;
    }

}
