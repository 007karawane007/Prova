package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/*
Mission: represents a request for a ticket (type of seat, number of seats, event edition)
    invariant: edition should exist for an event, number of seats >0, type of seat is a string that may or may not match
    the types that the edition of the event provides.

    Immutable
 */
public class DesiredTicket {
    private @Getter String typeOfSeat;
    private @Getter EventEdition edition;
    private @Getter int numberOfSeats;
    /*
    @param requestedTypeOfSeat has to be included in possibleTypesOfSeats; otherwise Exception is raised
    @param requestedTypeOfSeat should be non-null, non-empty; otherwise Exception is raised
     */
    private void setTypeOfSeat(@NotNull  String requestedTypeOfSeat, List<String> possibleTypesOfSeats ) throws Exception {
        if (requestedTypeOfSeat.isEmpty()){
            throw new Exception("Invalid type of seat");
        }
        if (! possibleTypesOfSeats.contains(requestedTypeOfSeat)){
            throw new Exception("Invalid type of seat for that event edition");
        }
        this.typeOfSeat = typeOfSeat;
    }

    private void setEdition(   EventEdition edition) {
        this.edition = edition;
    }

    private void setNumberOfSeats(int numberOfSeats) throws Exception {
        if (numberOfSeats<=0){
            throw new Exception("Wrong number of seats");
        }
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * return an instance unless args are invalid
     * @param typeOfSeat non-null non-empty and should be a valid description for that edition
     * @param edition has to be non-null
     * @param numberOfSeats has to be >0
     * @throws Exception
     */
    public DesiredTicket(@NonNull String typeOfSeat, @NonNull EventEdition edition, int numberOfSeats) throws Exception {
        this.setEdition(edition);
        List<String> possibleSeatTypes = edition.typesOfSeats();
        this.setTypeOfSeat(typeOfSeat, possibleSeatTypes);
        this.setNumberOfSeats(numberOfSeats);
    }
}
