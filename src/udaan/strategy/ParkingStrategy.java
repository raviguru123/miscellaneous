package udaan.strategy;

import udaan.models.ParkingLot;
import udaan.models.Slot;
import udaan.models.Ticket;
import udaan.models.Vehicle;
import udaan.models.VehicleType;

import java.util.Map;

public class ParkingStrategy implements IParkingStrategy {

    @Override
    public Slot getAvailableSlot(Map<String, ParkingLot> parkingLotMap, Vehicle vehicle) {
        for (Map.Entry<String, ParkingLot> entry : parkingLotMap.entrySet()) {

            for (Slot slot : entry.getValue().getSlotList()) {
                if (slot.isFree() && slot.getSlotType().toString().equals(vehicle.getSlotType().toString())) {
                    return slot;
                }
            }
        }
        return null;
    }

    @Override
    public synchronized Ticket parkVehicle(Map<String, ParkingLot> parkingLotMap, Vehicle vehicle) {
        Slot slot = getAvailableSlot(parkingLotMap, vehicle);
        if (slot != null) {
            slot.setFree(false);
            slot.setNumber(vehicle.getNumber());
            Ticket ticket = new Ticket(vehicle.getNumber());
            ticket.setSlot(slot);
            return ticket;
        }
        return null;
    }

    @Override
    public void unParkVehicle(Ticket ticket) {
        ticket.getSlot().setFree(true);
    }
}
