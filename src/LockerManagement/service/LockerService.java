package LockerManagement.service;

import LockerManagement.exceptions.NoSlotAvailableException;
import LockerManagement.model.Locker;
import LockerManagement.model.LockerItem;
import LockerManagement.model.Size;
import LockerManagement.model.Slot;
import LockerManagement.repository.ILockerRepository;
import LockerManagement.strategies.ISlotAssignmentStrategy;
import LockerManagement.strategies.ISlotFilteringStrategy;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public class LockerService {
    private static LockerService lockerService = null;
    private final ISlotAssignmentStrategy iSlotAssignmentStrategy;
    private final ISlotFilteringStrategy iSlotFilteringStrategy;
    private final ILockerRepository iLockerRepository;

    public static LockerService getInstance(@NonNull final ISlotAssignmentStrategy iSlotAssignmentStrategy,
                                            @NonNull final ISlotFilteringStrategy iSlotFilteringStrategy,
                                            @NonNull final ILockerRepository iLockerRepository) {
        if (lockerService == null) {
            synchronized (LockerService.class) {
                lockerService = new LockerService(iSlotAssignmentStrategy, iSlotFilteringStrategy, iLockerRepository);
            }
        }
        return lockerService;
    }

    public LockerService(@NonNull final ISlotAssignmentStrategy iSlotAssignmentStrategy,
                         @NonNull final ISlotFilteringStrategy iSlotFilteringStrategy,
                         @NonNull final ILockerRepository iLockerRepository) {
        this.iSlotAssignmentStrategy = iSlotAssignmentStrategy;
        this.iSlotFilteringStrategy = iSlotFilteringStrategy;
        this.iLockerRepository = iLockerRepository;
    }

    public Locker createLocker(@NonNull final String id) {
        return iLockerRepository.createLocker(id);
    }

    public Slot createSlot(@NonNull final Locker locker, @NonNull final Size slotSize) {
        Slot slot = new Slot(UUID.randomUUID().toString(), slotSize, locker);
        locker.addSlot(slot);
        return slot;
    }

    public List<Slot> getAllAvailableSlot() {
        return this.iLockerRepository.getAllAvailableSlots();
    }

    public Slot allocateSlot(@NonNull final LockerItem lockerItem) {
        final List<Slot> slots = this.getAllAvailableSlot();
        final List<Slot> filterSlot = this.iSlotFilteringStrategy.filterSlots(slots, lockerItem);
        final Slot slotToBeAllocated = this.iSlotAssignmentStrategy.pickSlot(filterSlot);
        if (slotToBeAllocated == null) {
            throw new NoSlotAvailableException();
        }
        slotToBeAllocated.allocatePackage(lockerItem);
        return slotToBeAllocated;
    }

    public void deAllocateSlot(@NonNull final Slot slot) {
        slot.deallocateSlot();
    }
}

