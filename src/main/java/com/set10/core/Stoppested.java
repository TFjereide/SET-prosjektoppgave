package com.set10.core;

import com.set10.core.Avgang;
import java.time.LocalTime;
import java.util.ArrayList;

public class Stoppested {
    public int id;
    public String navn;
    public ArrayList<Avgang> avganger = new ArrayList<>(); 

    public Stoppested( String navn) {
        
        this.navn = navn;
    }
    public Stoppested(int stoppID, String navn) {
        this.id = stoppID;
        this.navn = navn;
    }

    public void leggTilAvgang(Avgang avgang) {
        avganger.add(avgang);
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + navn + ":");
        for (Avgang a : avganger) {
            System.out.println("  " + a);
        }
    }

    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + navn + ".";
    }

    public String hentNavn() {
        return navn;
    }

    public ArrayList<Avgang> hentAvganger() {
        return avganger;
    }
    
}

