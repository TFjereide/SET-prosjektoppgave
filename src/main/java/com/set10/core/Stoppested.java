package com.set10.core;

import com.set10.core.Avgang;
import java.time.LocalTime;
import java.util.ArrayList;

public class Stoppested {
    public int stoppID;
    public String navn;
    ArrayList<Avgang> avganger = new ArrayList<>(); 

    public Stoppested(int stoppID, String navn) {
        this.stoppID = stoppID;
        this.navn = navn;
    }

    public Avgang leggTilAvgang(int ruteID, LocalTime tidspunkt) {
        Avgang a = new Avgang(ruteID, this, tidspunkt);
        avganger.add(a);
        return a;
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + navn + ":");
        for (Avgang a : avganger) {
            System.out.println("  " + a);
        }
    }

    @Override
    public String toString() {
        return "StoppID: " + stoppID + " Stoppested: " + navn + ".";
    }

    public String hentNavn() {
        return navn;
    }

public ArrayList<Avgang> hentAvganger() {
    return avganger;
}
    
}

