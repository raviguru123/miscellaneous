package udaan.strategy;

import lombok.NonNull;
import udaan.models.Range;
import udaan.models.Ticket;
import udaan.models.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class PriceCalculator implements IPriceCalculator {
    Map<VehicleType, Map<Range, Double>> priceMap;

    public PriceCalculator() {
        this.priceMap = new HashMap<>();
    }

    public void addPrice(@NonNull VehicleType vehicleType, @NonNull final int start, @NonNull final int end, @NonNull final double price) {
        Range range = new Range(start, end);
        if (!this.priceMap.containsKey(vehicleType)) {
            this.priceMap.put(vehicleType, new HashMap<>());
        }
        this.priceMap.get(vehicleType).put(range, price);
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        return 10.7;
    }


}
