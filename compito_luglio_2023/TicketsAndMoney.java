package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.Getter;

/**
 * represet a pair of <number of tickets, total euro>
 */
public class TicketsAndMoney {
    private @Getter Integer numTickets; // >=0
    private @Getter Double euro; //>=0

    public TicketsAndMoney(Integer numTickets, Double euro) {
        this.numTickets = numTickets;
        this.euro = euro;
    }
}
