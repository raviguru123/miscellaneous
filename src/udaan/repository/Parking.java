package udaan.repository;

import lombok.NonNull;
import udaan.exceptions.ParkingSpaceNotAvailable;
import udaan.exceptions.VehicleAlreadyExist;
import udaan.exceptions.VehicleNotExist;
import udaan.models.ParkingLot;
import udaan.models.Slot;
import udaan.models.SlotType;
import udaan.models.Ticket;
import udaan.models.Vehicle;
import udaan.strategy.IParkingStrategy;
import udaan.strategy.IPriceCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parking {
    private static Parking parking = null;

    private Map<String, ParkingLot> parkingLotMap;
    private Map<String, Ticket> stringTicketMap;
    private Map<String, List<Ticket>> history;

    private IParkingStrategy parkingStrategy;
    private IPriceCalculator priceCalculator;

    private Parking(@NonNull final IParkingStrategy parkingStrategy, IPriceCalculator priceCalculator) {
        this.stringTicketMap = new HashMap<>();
        this.parkingLotMap = new HashMap<>();
        this.history = new HashMap<>();
        this.parkingStrategy = parkingStrategy;
        this.priceCalculator = priceCalculator;
    }

    public static Parking getInstance(@NonNull final IParkingStrategy parkingStrategy, IPriceCalculator priceCalculator) {
        if (parking == null) {
            synchronized (Parking.class) {
                if (parking == null) {
                    parking = new Parking(parkingStrategy, priceCalculator);
                }
            }
        }
        return parking;
    }

    public void addPrkingLot(@NonNull String place, @NonNull ParkingLot parkingLot) {
        this.parkingLotMap.put(place, parkingLot);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        System.out.println("<======== Before Parking =========>");
        this.printParkingStats();

        if (!this.checkVehicleExist(vehicle.getNumber())) {

            Ticket ticket = parkingStrategy.parkVehicle(parkingLotMap, vehicle);
            if (ticket != null) {
                this.stringTicketMap.put(ticket.getVehicleNumber(), ticket);
                System.out.println("<======== After Parking =========>");
                this.printParkingStats();
                return true;
            }
            throw new ParkingSpaceNotAvailable();
        }
        throw new VehicleAlreadyExist();
    }

    public boolean checkVehicleExist(@NonNull final String vehicleNo) {
        if (this.stringTicketMap.containsKey(vehicleNo)) {
            return true;
        }
        return false;
    }

    public Ticket unparkVehicle(@NonNull final String vehicleNumber) {
        if (!this.checkVehicleExist(vehicleNumber) && this.stringTicketMap.containsKey(vehicleNumber)) {
            throw new VehicleNotExist();
        }
        Ticket ticket = this.stringTicketMap.get(vehicleNumber);
        this.parkingStrategy.unParkVehicle(ticket);
        double price = this.priceCalculator.calculatePrice(ticket);
        ticket.setAmount(price);

        if(!this.history.containsKey(ticket.getVehicleNumber())) {
            this.history.put(ticket.getVehicleNumber(), new ArrayList<>());
        }

        System.out.println("After Un-park the car Stats=======>");
        this.printParkingStats();
        return ticket;
    }

    public void printParkingStats() {
        Map<SlotType, Integer> slotTypeIntegerMap = new HashMap<>();
        for (Map.Entry<String, ParkingLot> entry : this.parkingLotMap.entrySet()) {
            for (Slot slot : entry.getValue().getSlotList()) {
                if (slot.isFree()) {
                    if (!slotTypeIntegerMap.containsKey(slot.getSlotType())) {
                        slotTypeIntegerMap.put(slot.getSlotType(), 0);
                    }
                    slotTypeIntegerMap.put(slot.getSlotType(), slotTypeIntegerMap.get(slot.getSlotType()) + 1);
                }
            }
        }
        System.out.println("Parking lot stats ====>" + slotTypeIntegerMap);
    }
}
