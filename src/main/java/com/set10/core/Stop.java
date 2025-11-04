package com.set10.core;

import java.util.ArrayList;
import java.util.HashSet;

public class Stop {
    public int id;
    public String name;
    public ArrayList<Departure> departures = new ArrayList<>();
    public HashSet<Integer> routes = new HashSet<>();
    public int zone;

    public Stop(String navn) {
        this.name = navn;
    }

    public Stop(int id, String navn) {
        this.id = id;
        this.name = navn;
    }

    public Stop(String navn, int sone) {
        this.name = navn;
        this.zone = sone;
    }

    public void addDeparture(Departure departure) {
        departures.add(departure);
    }

    public void addRoute(int routeID){
        routes.add(routeID);
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + name + ":");
        for (Departure a : departures) {
            System.out.println("  " + a);
        }
    }

    public int getZone() {
        return zone;
    }

    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + name + ".";
    }

    public ArrayList<Departure> hentAvganger() {
        return departures;
    }
}
