package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * represent the data used to report the list of selected events
 * data are # of tickets sold, total money, grouped by event
 * <p>
 * Abtsract state:
 * - a map: event --> (n tickets, euros)
 * - a map: eventEdition --> (n tickets, euros)
 * - a map: (eventEdition, seatType) --> (n tickets, euros)
 * Some or all of these maps can be empty - if no data has been collected
 *
 * Concrete state:
 * - 3 map objects, non null
 */
public class ReportData {

    private Map<Event, TicketsAndMoney> eventMap =
            new HashMap<Event, TicketsAndMoney>();
    // TODO add other maps

    /**
     * add to the map of events, the pair (tickets, money)
     *
     * @param anEvent non null valid event
     * @param tickets >=0 sold for that event
     * @param money   >=0 revenue for that event
     *                No duplicates are handled: this  will rewrite
     *                any existing entry for event
     */
    public void addEntry(Event anEvent,
                         @NonNull Integer tickets,
                         @NonNull Double money) {

    }

    /**
     * add to the map of eventEditions, the pair (tickets, money)
     *
     * @param anEdition non null valid eventEdition
     * @param seatType a valid seat type for that edition
     * @param tickets >=0 sold for that edition
     * @param money   >=0 revenue for that edition
     *                No duplicates are handled: this will  rewrite
     *                any existing entry for event
     */
    public void addEntry(@NonNull EventEdition anEdition,
                         @NonNull String seatType,
                         @NonNull Integer tickets,
                         @NonNull Double money) {
    }

    /**
     * add to the map of eventEdition and seatType,
     * the pair (tickets, money)
     *
     * @param anEdition non null valid eventEdition
     * @param tickets >=0 sold for that edition
     * @param money   >=0 revenue for that edition
     *                No duplicates are handled: this will  rewrite
     *                any existing entry for event
     */
    public void addEntry(@NonNull EventEdition anEdition,
                         @NonNull Integer tickets,
                         @NonNull Double money) {
    }

    /**
     * return the data stored in the event map
     * or null if the event is not present
     * @param anEvent
     */
    public TicketsAndMoney findDataBy(Event anEvent){

    }
    public Set<Event> findEvents(){
        return this.eventMap.keySet();
    }
    // TODO add other accessors for the other maps
}
