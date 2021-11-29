package udaan.service;

import lombok.NonNull;
import udaan.models.ParkingLot;
import udaan.models.Ticket;
import udaan.models.Vehicle;
import udaan.repository.Parking;

public class ParkingService {
    private Parking parking;

    public ParkingService(Parking parking) {
        this.parking = parking;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        return this.parking.parkVehicle(vehicle);
    }

    public Ticket unParkVehicle(@NonNull final String number) {
        return this.parking.unparkVehicle(number);
    }

    public void addParking(@NonNull final String place, @NonNull ParkingLot parkingLot) {
        this.parking.addPrkingLot(place, parkingLot);
    }
}
