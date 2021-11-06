package LockerManagement.gojek.models;

import LockerManagement.gojek.exception.InvalidOperation;
import LockerManagement.gojek.exception.NoTicketAssignedException;
import lombok.NonNull;

public class Supervisor extends User {
    public Supervisor(@NonNull final String name, @NonNull final Role role) {
        super(name, role);
    }

    @Override
    public Ticket resolveTicket() {

        if (this.ticketList.size() == 0) {
            throw new NoTicketAssignedException();
        }
        Ticket ticket = this.ticketList.remove(0);
        if (!ticket.ticketStatus.equals(TicketStatus.CLOSED)) {
            throw new InvalidOperation();
        }

        ticket.changeTicketStatus(TicketStatus.VERIFIED);
        return ticket;
    }
}
