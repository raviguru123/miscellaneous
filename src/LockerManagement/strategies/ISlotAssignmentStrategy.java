package LockerManagement.strategies;

import LockerManagement.model.Slot;
import lombok.NonNull;

import java.util.List;

public interface ISlotAssignmentStrategy {
    public Slot pickSlot(@NonNull List<Slot> slots);
}
