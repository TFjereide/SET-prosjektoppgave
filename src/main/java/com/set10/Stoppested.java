package com.set10;

public class Stoppested {
    public int id;
    public String adresse;


    public Stoppested(int id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }

    // Legg til avgang FRA dette stoppet
    //kode fra Isma
    public void leggTilAvgang(int ruteNr, LocalDateTime tidspunkt, int linje) {
        avganger.add(new Avgang(ruteNr, tidspunkt, linje));
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + adresse + ":");
        for (Avgang a : avganger) {
            System.out.println("  " + a);
        }
    }

    @Override
    public String toString() {
        return "StoppID: " + id + " Stoppested: " + adresse + ".";
    }



    
}
