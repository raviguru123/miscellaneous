package LockerManagement.strategies;

import LockerManagement.model.LockerItem;
import LockerManagement.model.Slot;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class SlotFilteringStrategySizeBased implements ISlotFilteringStrategy {
    @Override
    @NonNull
    public List<Slot> filterSlots(@NonNull List<Slot> slots, @NonNull LockerItem lockerItem) {
        return slots.stream().filter(slot -> slot.getSize().canAccommodate(lockerItem.getSize()))
                .collect(Collectors.toList());
    }
}
