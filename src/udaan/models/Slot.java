package udaan.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Slot {
    private SlotType slotType;
    private boolean isFree;
    private String number;

    public Slot(@NonNull final SlotType slotType) {
        this.slotType = slotType;
        this.isFree = true;
    }
}
