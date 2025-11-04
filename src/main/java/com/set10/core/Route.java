package com.set10.core;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Route {
    public int id;
    public ArrayList<Stop> stops = new ArrayList<>();

    public Route(int id) {
        this.id = id;
    };

    public Route(int id, ArrayList<Stop> stop) {
        this.id = id;
        this.stops = stop;
    };

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    // // Denne gjør ingenting for øyeblikket
    // public double beregnRuteLengde() {
    //     int antallStopp  = stopp.size();
    //     double totalAvstand = 0;

    //     for (int x = 0; x < antallStopp - 1; x++) {
    //         Stop nåværendeStopp = stopp.get(x);
    //         Stop nesteStopp = stopp.get(x + 1);
    //         totalAvstand += GPSTjeneste.avstandMellomStopp(nåværendeStopp, nesteStopp);
    //     }

    //     return totalAvstand;
    // }

    public List<Integer> getZonesFromRoute() {
        Set<Integer> soner = new java.util.HashSet<>();
        for (Stop stopp : stops) {
            soner.add(stopp.getZone());
        }
        return new ArrayList<>(soner);
    }

    // public void visRute() {
    //     System.out.println("Rute med " + stopp.size() + " stopp:");
    //     for (Stop s : stopp) {
    //         System.out.println("  - " + s);
    //     }
    //     // System.out.println("Total lengde: " + beregnRuteLengde() + " km");
    // }

    public String toString(){
        return "Rute: " + this.id;
    }
}