package LockerManagement.strategies;

import LockerManagement.model.Slot;
import lombok.NonNull;

import java.util.List;

public class SlotAssignmentStrategyRandom implements ISlotAssignmentStrategy {
    private final IRandomNumberGenerator iRandomNumberGenerator;

    public SlotAssignmentStrategyRandom(@NonNull IRandomNumberGenerator iRandomNumberGenerator) {
        this.iRandomNumberGenerator = iRandomNumberGenerator;
    }

    public Slot pickSlot(@NonNull List<Slot> slots) {
        if (slots.isEmpty()) {
            return null;
        }
        int slotNum = this.iRandomNumberGenerator.getLessThanRandomNumber(slots.size());
        return slots.get(slotNum);
    }
}
