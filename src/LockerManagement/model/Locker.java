package LockerManagement.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Locker {
    private final String id;
    private final List<Slot> slots;

    public Locker(@NonNull final String id) {
        this.id = id;
        this.slots = new ArrayList<>();
    }

    public void addSlot(@NonNull final Slot slot) {
        this.slots.add(slot);
    }

    public List<Slot> getAvailableSlots() {
        List<Slot> result = new ArrayList<>();
        for (Slot slot : this.slots) {
            if (slot.isSlotAvailable()) {
                result.add(slot);
            }
        }
        return result;
    }
}
