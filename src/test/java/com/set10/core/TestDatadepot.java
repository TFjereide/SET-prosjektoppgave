package com.set10.core;

import com.set10.core.Datadepot;
import com.set10.core.Stoppested;
import com.set10.database.DatabaseText;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDatadepot {
    
    @Test
    @DisplayName("Liten test av datadepot")
    void testDataDepot() {
        
        Datadepot depot = new Datadepot(new DatabaseText()); 

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
