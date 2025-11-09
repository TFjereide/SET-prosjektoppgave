package com.set10.core;

import java.util.ArrayList;
public class User {
    public int id;
    public String name;
    public ArrayList<Ticket> activeTickets;
    public ArrayList<Ticket> oldTickets;

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
         return "BrukerId: " + id + "; Navn: " + name;

    }

    public String getDisplayName() {
        return name;
    }
}
