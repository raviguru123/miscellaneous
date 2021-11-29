package gojek.models;

import gojek.exception.InvalidOperation;
import gojek.exception.NoTicketAssignedException;
import lombok.NonNull;

public class Employee extends User {

    public Employee(@NonNull final String name, @NonNull final Role role) {
        super(name, role);
    }

    @Override
    public Ticket resolveTicket() {
        if (this.ticketList.size() == 0) {
            throw new NoTicketAssignedException();
        }

        Ticket ticket = this.ticketList.remove(0);
        if (!ticket.ticketStatus.equals(TicketStatus.ASSIGNED)) {
            throw new InvalidOperation();
        }

        ticket.changeTicketStatus(TicketStatus.CLOSED);
        return ticket;
    }
}
