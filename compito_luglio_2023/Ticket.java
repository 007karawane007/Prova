package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Represent a valid ticket.
 * Abstract state:
 * - an non empty, non null id
 * - a non null and valid date <= Now
 * - a reference to a customer that bought it (non null)
 * - its price (>0)
 * - the event and the event edition it refers to (non null, not empty)
 * - the seat type it refers to (non null, not empty)
 */
public class Ticket {
    private @NonNull String id;
    private @NonNull LocalDate soldOn;
    private @NonNull @Getter Event theEvent;
    // TODO add remaining fields

    public Ticket() {
        // somehow obtain a unique id (eg via timestamp or UUID)
        this.id = id;
        this.soldOn = LocalDate.now();
        // TODO add other fields
    }

    /**
     * 2 tickets are equals if they have the same id
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()){
            return false;
        } else if (this.id.equals(((Ticket) obj).id)){
            return true;
        } else return false;
    }

    public Double getPrice() {
    }

    // TODO implement also hashcode
}
