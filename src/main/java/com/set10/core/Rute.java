package com.set10.core;

import java.util.ArrayList;

public class Rute {
    public int id;
    public ArrayList<Stoppested> stopp = new ArrayList<>();

    public Rute(int id) {
        this.id = id;
    };

    public Rute(int id, ArrayList<Stoppested> stopp) {
        this.id = id;
        this.stopp = stopp;
    };

    public void leggTilStopp(Stoppested stoppested) {
        stopp.add(stoppested);
    }

    public double beregnRuteLengde() {
        int antallStopp  = stopp.size();
        double totalAvstand = 0;

        for (int x = 0; x < antallStopp - 1; x++) {
            Stoppested nåværendeStopp = stopp.get(x);
            Stoppested nesteStopp = stopp.get(x + 1);
            totalAvstand += GPSTjeneste.avstandMellomStopp(nåværendeStopp, nesteStopp);
        }

        return totalAvstand;
    }

    public void visRute() {
        System.out.println("Rute med " + stopp.size() + " stopp:");
        for (Stoppested s : stopp) {
            System.out.println("  - " + s);
        }
        System.out.println("Total lengde: " + beregnRuteLengde() + " km");
    }
}