package udaan.strategy;

import udaan.models.Ticket;

public interface IPriceCalculator {
    public double calculatePrice(Ticket ticket);
}
