package LockerManagement.gojek.repository;

import LockerManagement.gojek.exception.TicketAlreadyExist;
import LockerManagement.gojek.exception.TicketNotExist;
import LockerManagement.gojek.models.Ticket;
import LockerManagement.gojek.models.TicketStatus;
import LockerManagement.gojek.models.TicketType;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketRepository {
    private static TicketRepository ticketRepository = null;

    Map<Integer, Ticket> ticketMap;

    Map<TicketStatus, List<Ticket>> ticketStatusListMap;

    private TicketRepository() {
        this.ticketMap = new HashMap<>();
    }

    public TicketRepository getInstance() {
        if (ticketRepository == null) {
            synchronized (TicketRepository.class) {
                if (ticketRepository == null) {
                    ticketRepository = new TicketRepository();
                }
            }
        }
        return ticketRepository;
    }

    public void addTicket(@NonNull Ticket ticket) {
        if (this.ticketMap.containsKey(ticket.getId())) {
            throw new TicketAlreadyExist();
        }

        if (!this.ticketStatusListMap.containsKey((ticket.getTicketStatus()))) {
            this.ticketStatusListMap.put(ticket.getTicketStatus(), new ArrayList<>());
        }

        this.ticketStatusListMap.get(ticket.getTicketType()).add(ticket);
    }

    public void printTicketStats() {

        for (Map.Entry<TicketStatus, List<Ticket>> entry : this.ticketStatusListMap.entrySet()) {
            System.out.println(entry.getKey() + " :" + entry.getValue().size());
        }
    }


    public void updateStatus(@NonNull Ticket ticket, @NonNull TicketStatus ticketStatus) {
        List<Ticket> ticketList = this.ticketStatusListMap.get(ticketStatus);

    }



    public Ticket getTicketType(@NonNull TicketType ticketType) {

        if (!this.ticketStatusListMap.containsKey(ticketType) && this.ticketStatusListMap.get(ticketType).size() == 0) {
            throw new TicketNotExist();
        }

        List<Ticket> ticketList = this.ticketStatusListMap.get(ticketType);
        return ticketList.get(0);
    }
}
