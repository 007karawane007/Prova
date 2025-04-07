package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MISSION: to represent the api that the backend component should provide to enable implementation of the application
 * ABSTRACT STATE:
 * It contains a store of all known past present and future events,
 * a store of tickets that have been sold and
 * a store of presales.
 *
 * The stores do not contain duplicates. Duplicates are checked via equals().
 *
 * CONCRETE STATE:
 * - the 3 instance variables, 1:1 with components of the abstract state
 *
 * INVARIANTS:
 * - every ticket that is sold has to refer to an event that exists in
 *   knownEvents;
 * - every ticket that is sold has to be sold via 1 presale object stored
 *   in presales;
 * - every presale in presales has to refer to an event stored in knownEvents.
 */
public class EventiLive {

    private EventsRegister knownEvents = new EventsRegister();
    private TicketRegister soldTickets = new TicketRegister();
    private PresalesRegister presales = new PresalesRegister();

    public Factory factory() {
        return new Factory();
    }

    /**
    return the event edition if the presale is open,
    if it exists and if there is an edition for the given date
    return Null otherwise
     */
    public EventEdition findEventEdition(String presaleForEvent, LocalDate dateOfEdition) {
    }

    /**
     * issue tickets as requested. Process each request in order and try to fulfill it.
     * Issue as many tickets as possible, possibly 0.
     *
     * @param customer       should be not null,
     * @param desiredTickets the list should not be empty and each desired ticket not null
     * @param thePresale the presale for the event
     * @return list of new tickets, one for each desiredTicket. If some of the tickets
     * cannot be bought, skip that request and keep going with remaining ones.
     * @throws Exception if the presale is not open or args are wrong
     */
    public List<Ticket> buyTickets(@NonNull Customer customer,
                                   @NonNull List<DesiredTicket> desiredTickets,
                                   @NonNull PreSale thePresale) throws Exception {
        if (desiredTickets.size() == 0){
            throw new Exception("No desired tickets specified");
        }
        if (!thePresale.isOpen()){
            throw new Exception("Presale for the event is not open");
        }

        List<Ticket> newTickets = new ArrayList<>();
        for (DesiredTicket desiredTicket : desiredTickets){
            if (! thePresale.isAvailable(desiredTicket)){
                // log message ("Not enough seats");
            } else {
                for (int i =0;i< desiredTicket.getNumberOfSeats(); i++){
                    Ticket theTicket = thePresale.buyTicket(desiredTicket);
                    if (theTicket != null){
                        this.soldTickets.add(theTicket);
                        newTickets.add(theTicket);
                    }
                }
            }

        }
        return newTickets;
    }

    /**
     * find first presale in register that is associated to event name
     * @param eventName should not be empty
     * @return the presale object or raises NoSuchElementException
     */
    public PreSale findEventPresale(@NonNull String eventName) throws Exception {
        if (eventName.isEmpty()){
            throw new Exception("wrong event name");
        }
        return this.presales.findPresaleByEventName(eventName);

    }

    /**
     * scan the sales data and populate part of the
     * report data (the map concerning events) with data about sold tickets
     * for any edition of that event.
     * Do this only for event editions that have already occurred.
     * @return a new ReportData object
     */
    public ReportData generateReportByEvent() {
        // per ogni evento in EventsRegsiter
        //  per ogni sua edition open o closed
        //   cerca biglietti venduti per quella edition
        //   e colleziona i dati
        ReportData data = new ReportData();
        Iterator<Event> events = this.knownEvents.iterator();
        while (events.hasNext()){
            Event anEvent = events.next();
            Long num=this.soldTickets.numberOfTicketsForEvent(anEvent);
            Double euros=this.soldTickets.revenueForEvent(anEvent);
            data.addEntry(anEvent, num, euros);
        }
        return data;
    }

    // TODO as above
    public ReportData generateReportByEventEdition() {
    }

    public ReportData generateReportByEventEditionAndSeatType() {
    }
}
