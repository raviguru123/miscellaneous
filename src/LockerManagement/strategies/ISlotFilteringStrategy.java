package LockerManagement.strategies;

import LockerManagement.model.LockerItem;
import LockerManagement.model.Slot;
import lombok.NonNull;

import java.util.List;

public interface ISlotFilteringStrategy {
    @NonNull
    public List<Slot> filterSlots(@NonNull List<Slot> slots, @NonNull LockerItem lockerItem);
}
