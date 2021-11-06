package LockerManagement.gojek.models;

import LockerManagement.gojek.utils.IDGenerator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
public class Ticket {
    private final Integer id;
    TicketType ticketType;
    @Setter
    TicketStatus ticketStatus;

    public Ticket(@NonNull final String type) {
        this.id = IDGenerator.getId();
        for (TicketType ticketType : TicketType.values()) {
            if (type.equals(ticketType.value)) {
                this.ticketType = ticketType;
                break;
            }
        }
        this.ticketStatus = TicketStatus.OPEN;
    }

    public void changeTicketStatus(@NonNull TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
