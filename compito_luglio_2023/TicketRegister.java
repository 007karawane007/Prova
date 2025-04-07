package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * MIssion is to represent the collection of tickets that
 * have been sold.
 * Abstract state: a set of tickets (each ticket has a unique id that
 * is used to maintain uniqueness) that have been sold for an event that
 * occurred in the past or a future one.
 *
 * Concrete state:
 * - a hash set;
 *
 * Invariant:
 * - uniqueness of tickets is guaranteed by equality of tickets
 * - the hashset should be non null
 */
public class TicketRegister {

    private Set<Ticket> soldTickets = new HashSet<>();

    /**
     * add the given ticket to the register
     * Raise Exception if ticket is duplicated
     * @param aTicket a valid ticket (ie with valid data)
     */
    public void add(@NonNull Ticket aTicket) {
        // TODO
    }

    /**
     * find a ticket by its id
     * @param id a non null non empty string
     * @return null if not found or the ticket that has that id
     */
    public Ticket findById(@NonNull String id) {
        // TODO
    }

    /**
     * scan the list of tickets and count the number of tickets that
     * refer to given event name
     * @param anEvent non null
     * @return the count
     */
    public Long numberOfTicketsForEvent(@NonNull Event anEvent) {
        Long num = this.soldTickets.stream()
                .filter(t -> t.getTheEvent().getName()==anEvent.getName())
                .count();
        return num;
    }

    /**
     * scan the list of tickets and sum the price of tickets that
     * refer to given event name
     * @param anEvent non null
     * @return the sum
     */
    public Double revenueForEvent(Event anEvent) {
        Double euros = this.soldTickets.stream()
                .filter(t -> t.getTheEvent().getName()==anEvent.getName())
                .map(t -> t.getPrice())
                .reduce(0.0, Double::sum);
        return euros;
    }
}
