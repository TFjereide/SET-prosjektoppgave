package com.set10.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Avgang {
    int ruteID;
    Stoppested stoppested;
    LocalTime tidspunkt;

    public Avgang(int ruteID, Stoppested stoppested, LocalTime tidspunkt) {
        this.ruteID = ruteID;
        this.stoppested = stoppested;
        this.tidspunkt = tidspunkt;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return "(Rute " + ruteID + ") - Avgang: " + tidspunkt.format(formatter);
    }
}