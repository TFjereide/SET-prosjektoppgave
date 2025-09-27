package com.set10;
import java.util.ArrayList;

public class StoppestedLeggTil {
    public ArrayList<Stoppested> stoppesteder = new ArrayList<>();


    public void leggTilStoppested(int id, String adresse) {
        stoppesteder.add(new Stoppested(id, adresse));
    }


    public void visStoppesteder() {
        for (Stoppested s : stoppesteder) {
            System.out.println(s);
        }
    }


}