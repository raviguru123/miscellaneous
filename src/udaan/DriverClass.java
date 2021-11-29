package udaan;

import udaan.models.ParkingLot;
import udaan.models.Vehicle;
import udaan.models.VehicleType;
import udaan.repository.Parking;
import udaan.service.ParkingService;
import udaan.strategy.IParkingStrategy;
import udaan.strategy.IPriceCalculator;
import udaan.strategy.ParkingStrategy;
import udaan.strategy.PriceCalculator;

public class DriverClass {
    public static void main(String args[]) {
        IParkingStrategy parkingStrategy = new ParkingStrategy();
        IPriceCalculator priceCalculator = new PriceCalculator();

        Parking parking = Parking.getInstance(parkingStrategy, priceCalculator);

        ParkingLot parkingLot1 = new ParkingLot(2,0,3);
        ParkingLot parkingLot2 = new ParkingLot(1);

        ParkingService parkingService = new ParkingService(parking);

        parkingService.addParking("floor1", parkingLot1);
        parkingService.addParking("floor2", parkingLot2);

        Vehicle vehicle1 = new Vehicle("123", VehicleType.HATCHBACK);

        Vehicle vehicle2 = new Vehicle("234", VehicleType.SUV);

        parkingService.parkVehicle(vehicle1);
        parkingService.parkVehicle(vehicle2);

        parkingService.unParkVehicle(vehicle1.getNumber());

    }
}
