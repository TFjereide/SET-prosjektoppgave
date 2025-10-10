package com.set10.core;

import com.set10.database.Datadepot;
import com.set10.core.Stoppested;

public class TestDatadepot {
    public static void main(String[] args) {
        Datadepot depot = new Datadepot(); 

        System.out.println("==== TEST AV DUMMYDATA ====");
        System.out.println("Antall brukere:      " + depot.hentBrukere().size());
        System.out.println("Antall stoppesteder: " + depot.hentStoppesteder().size());
        System.out.println("Antall avganger:     " + depot.hentAvganger().size());
        System.out.println("Antall ruter:        " + depot.hentRuter().size());

    
        Stoppested s = depot.hentStoppested(1);
        if (s != null) {
            System.out.println("\nAvganger fra " + s.navn + ":");
            s.visAvganger();
        } else {
            System.out.println("Fant ikke stoppested med ID 1!");
        }
    }
}
