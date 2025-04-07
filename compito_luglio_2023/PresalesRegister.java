package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * MISSION: represent the collection of all presales that have been
 * created for past and future events.
 *
 * Abstract state:
 * - set of presale objects, each identified by an ID
 *
 * Invariant:
 * - uniqueness of IDs
 * - non null set
 */
public class PresalesRegister {

    private @NonNull List<PreSale> allPresales = new ArrayList<>();

    public Stream<PreSale> stream(){
        return this.allPresales.stream();
    }

    /**
     * find first presale in register that is associated to event name
     * @param eventName should not be empty
     * @return the presale object or raises NoSuchElementException
     */
    public PreSale findPresaleByEventName(String eventName) {
        return this.allPresales.stream()
                .findFirst(p -> p.getTheEvent().getName().equals(eventName))
                .get();
    }

    /**
     * add a presale to the collection
     * @param aPresale non null with unique id presale
     */
    public void add(@NonNull Presale aPresale){

    }
}
