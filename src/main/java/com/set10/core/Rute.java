package com.set10.core;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Rute {
    public int ruteID;
    public ArrayList<Stoppested> stopp = new ArrayList<>();

    public Rute(int ruteID) {
        this.ruteID = ruteID;
    };

    public Rute(int ruteID, ArrayList<Stoppested> stopp) {
        this.ruteID = ruteID;
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

    public List<Integer> hentSonerFraRute() {
        Set<Integer> soner = new java.util.HashSet<>();
        for (Stoppested stopp : stopp) {
            soner.add(stopp.getSone());
        }
        return new ArrayList<>(soner);
    }

    public void visRute() {
        System.out.println("Rute med " + stopp.size() + " stopp:");
        for (Stoppested s : stopp) {
            System.out.println("  - " + s);
        }
        System.out.println("Total lengde: " + beregnRuteLengde() + " km");
    }

    public int hentRuteID() {
        return ruteID;
  }


    public String toString(){
        return "Rute: " + this.ruteID;
    }
}