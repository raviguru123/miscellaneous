package udaan.strategy;

import udaan.models.ParkingLot;
import udaan.models.Slot;
import udaan.models.Ticket;
import udaan.models.Vehicle;
import udaan.models.VehicleType;

import java.util.Map;

public interface IParkingStrategy {
    public Slot getAvailableSlot(Map<String, ParkingLot> parkingLotMap, Vehicle vehicle);

    public Ticket parkVehicle(Map<String, ParkingLot> parkingLotMap, Vehicle vehicle);

    public void unParkVehicle(Ticket ticket);
}
