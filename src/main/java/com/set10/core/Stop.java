package com.set10.core;
import java.util.ArrayList;

public class Stop {
    public int id;
    public String navn;
    public ArrayList<Departure> avganger = new ArrayList<>(); 
    public int sone;

    public Stop(String navn) {
        this.navn = navn;
    }

    public Stop(int id, String navn) {
        this.id = id;
        this.navn = navn;
    }

    public Stop(String navn, int sone) {
        this.navn = navn;
        this.sone = sone;
    }

    public void addDeparture(Departure departure) {
        avganger.add(departure);
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + navn + ":");
        for (Departure a : avganger) {
            System.out.println("  " + a);
        }
    }

    public int getSone() {
        return sone;
    }

    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + navn + ".";
    }

    public ArrayList<Departure> hentAvganger() {
        return avganger;
    }
}
