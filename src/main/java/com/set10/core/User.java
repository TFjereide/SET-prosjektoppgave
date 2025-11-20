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

    public String getDisplayName() {
        return name;
    }

    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }
        if(!(object instanceof User)){
            return false;
        }

        User other = (User) object;

        if( other.id != id){return false;}
        
        if(!other.name.equals(name)){return false;}
        
        if(activeTickets.size() != other.activeTickets.size()){return false;}

        if(oldTickets.size() != other.oldTickets.size()){return false;}

        for (int i = 0; i < activeTickets.size();i++){
            if(!activeTickets.get(i).equals(other.activeTickets.get(i))){
                return false;
            }
        }
        
        for (int i = 0; i < oldTickets.size();i++){
            if(! oldTickets.get(i).equals(other. oldTickets.get(i))){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString(){
         return "Id: " + id + " name: " + name;
    }
    
}
