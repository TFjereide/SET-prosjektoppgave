package com.set10.core;
import com.set10.core.Avgang;

import java.time.LocalTime;
import java.util.ArrayList;

public class Stoppested {
    public int id;
    public String adresse;
    ArrayList<Avgang> avganger = new ArrayList<>();
    public int sone;

    public Stoppested(int id, String adresse, int sone) {
        this.id = id;
        this.adresse = adresse;
        this.sone = sone;
    }

    public void leggTilAvgang(int ruteNr, LocalTime tidspunkt) {
        avganger.add(new Avgang(ruteNr, this, tidspunkt));
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + adresse + ":");
        for (Avgang a : avganger) {
            System.out.println("  " + a);
        }
    }

    public int getSone() {
        return sone;
    }

    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + adresse + ".";
    }



    
}
