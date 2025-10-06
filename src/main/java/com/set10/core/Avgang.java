package com.set10.core;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Avgang {
    int ruteID;
    LocalDateTime tidspunkt;

    public Avgang(int ruteID, LocalDateTime tidspunkt) {
        this.ruteID = ruteID;
        this.tidspunkt = tidspunkt;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "(Rute " + ruteID + ") - Avgang: " + tidspunkt.format(formatter);
    }
}