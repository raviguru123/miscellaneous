package udaan.models;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ParkingLot {
    List<Slot> slotList;

    public ParkingLot(@NonNull final Integer capacity) {
        this.slotList = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            this.slotList.add(new Slot(SlotType.ONE));
        }
    }
}
