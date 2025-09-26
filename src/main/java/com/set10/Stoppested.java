package com.set10;

public class Stoppested {
    private int id;
    private String adresse;


    public Stoppested(int id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }


    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + adresse + ".";
    }


    public String getAdresse() {return adresse;}
    public int getId() {return id;}

    


}
