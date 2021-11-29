package udaan.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@ToString
public class Ticket {
    private String ticketNo;
    private String vehicleNumber;
    private Date startTime;
    private Date endTime;
    private double amount;
    private Slot slot;

    public Ticket(@NonNull final String vehicleNumber) {
        this.ticketNo = UUID.randomUUID().toString();
        this.startTime = new Date();
        this.vehicleNumber = vehicleNumber;
    }
}
