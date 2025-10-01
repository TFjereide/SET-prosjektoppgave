package com.set10;

public class Stoppested {
    public int id;
    public String adresse;


    public Stoppested(int id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }


    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + adresse + ".";
    }



    
}
