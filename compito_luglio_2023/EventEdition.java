package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

/*
Mission: represent an edition of an event; is immutable
Abstract state:
- event non null
- list of names of types of seats (eg ["platea", "galleria"]);
  should be different names
- date of the edition of the event, in the past or future

Concrete state:
- the instance variables, 1:1 with abstract state

Invariants:
- none
 */
public class EventEdition {
    private Event theEvent;
    private List<String> typesOfSeats;
    private @Getter LocalDate theDate;

    /*
    return a non-empty list of non-empty strings describing the types of seats provided by the event edition.
    Protect the existing list against modifications.
     */
    public List<String> typesOfSeats() {
        return List.copyOf(typesOfSeats);
    }

    /**
     *
     * @return a copy of the event object
     */
    public Event getEvent(){
        return (Event) this.theEvent.clone();
    }

    public EventEdition(@NonNull Event theEvent,
                        @NonNull List<String> typesOfSeats,
                        @NonNull LocalDate theDate) {
        this.theEvent = theEvent;
        this.typesOfSeats = typesOfSeats;
        this.theDate = theDate;
        // TODO validate the args
    }
}
