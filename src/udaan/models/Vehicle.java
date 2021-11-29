package udaan.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Vehicle {
    private String number;
    private VehicleType vehicleType;
    private SlotType slotType;

    public Vehicle(@NonNull final String number, @NonNull final VehicleType vehicleType) {
        this.number = number;
        this.vehicleType = vehicleType;
        this.slotType = SlotType.ONE;
    }
}
