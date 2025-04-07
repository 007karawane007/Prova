package it.uniud.poo.compiti.compito_luglio_2023;

import lombok.Getter;

public class Event {
    private @Getter  String name;

    @Override
    public Object clone(){
        return (Event) this.clone();
    }
}
