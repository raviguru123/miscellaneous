package gojek.models;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


public abstract class User {
    public final String name;
    public final Role role;

    public List<Ticket> ticketList;

    public User(@NonNull final String name, @NonNull final Role role) {
        this.name = name;
        this.role = role;
        this.ticketList = new ArrayList<>();
    }

    public void assignTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }

    public abstract Ticket resolveTicket();

}
