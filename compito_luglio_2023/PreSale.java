package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Mission: represent the presale for an event
 * Abstract state:
 * - the event, non null
 * - the state of the presale: open, to-be-open, closed
 * - price for each combination of (edition, type-of-seat)
 * - number of available seats for each combination of
 *   (edition, type-of-seat): is 0 when max capacity is reached
 *   should be >=0
 */

public class PreSale {

    private @Getter PresaleKind state;
    private @Getter Event theEvent;
    // TODO add other fields

    // TODO add constructors that validate their args

    public boolean isOpen() {
        return this.state == PresaleKind.OPEN;
    }

    public void changeStateTo(PresaleKind newState){
        this.state = newState;
    }

    /**
     * proceed with the payment of the requested ticket (1 seat
     * of given type for given edition)
     * create the ticket, update available seats
     * @param desiredTicket should be valid - not null with not null fields
     *                      also checked for availability
     * @return the ticket or raise exception if payment went wrong
     */
    public Ticket buyTicket(DesiredTicket desiredTicket) {
        Double priceToPay = this.findPriceFor(desiredTicket.getTypeOfSeat(),
                desiredTicket.getEdition());
        try {
            this.proceedWithPayment(priceToPay,
                    desiredTicket.getEdition().getEvent().getName(),
                    desiredTicket.getEdition().getTheDate(),
                    // TODO add here also data about payment method
                    desiredTicket.getEdition().typesOfSeats());
        } catch (Exception ex){
            // handle it by printing a message
            throw new Exception("Payment unsuccessful")
        }
    }

    /**
     * process the payment by contacting the bank and issuing a transaction
     * (this is a very simplified view of what needs to happen in reality)
     * @param priceToPay
     * @param name
     * @param theDate
     * @param typesOfSeats
     */
    private void proceedWithPayment(Double priceToPay, String name, LocalDate theDate, List<String> typesOfSeats) {
    }

    /**
     * find the price for the given edition of the event of this presale
     * and the given type of seat.
     * Raise an exception if the seat type does not exist
     * @param typeOfSeat non null non empty valid description of a seat type
     * @param edition non null edition of the event
     * @return the price
     */
    private Double findPriceFor(@NonNull String typeOfSeat,
                                @NonNull EventEdition edition) {
        return null; // TODO
    }

    /**
     * check if there are available seats for the required tickets
     * @param desiredTicket non null, specifying i>0 seats
     * @return true if there is availability for the required seats
     * false otherwise
     * @throws Exception if the seat type does not exist
     */
    public boolean isAvailable(@NonNull DesiredTicket desiredTicket) {
        // TODO
    }

    /**
     * check if the given type is valid for the given edition
     * @param typeOfSeat non null non empty description
     * @param theEdition non null edition
     * @return true if it is valid or false otherwise
     */
    public boolean isValidTypeOfSeat(@NonNull String typeOfSeat,
                                     @NonNull EventEdition theEdition){
        // TODO
    }
}
