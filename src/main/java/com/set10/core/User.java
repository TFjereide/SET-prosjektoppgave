package com.set10.core;

import java.util.ArrayList;

public class User {
    
    public int id;
    public String name;
    public ArrayList<Ticket> activeTickets;
    public ArrayList<Ticket> oldTickets;

    // A trip the user is considered to be on, at the present moment.
    public Trip activeTrip = null;

    public User(int id, String navn){
        this.id = id;
        this.name = navn;
        this.activeTickets = new ArrayList<>();
        this.oldTickets = new ArrayList<>();
    }

    public User(String navn){
        this.name = navn;
        this.activeTickets = new ArrayList<>();
        this.oldTickets = new ArrayList<>();
    }

    @Override
    public String toString(){
         return "Id: " + id + " name: " + name;
    }
    
    public String getDisplayName() {
        return name;
    }
}
