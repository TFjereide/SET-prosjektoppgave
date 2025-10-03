package com.set10;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Avgang {
    int ruteNr;
    LocalDateTime tidspunkt;
    int linje;

    public Avgang(int ruteNr, LocalDateTime tidspunkt, int linje) {
        this.ruteNr = ruteNr;
        this.tidspunkt = tidspunkt;
        this.linje = linje;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "Linje " + linje + " (Rute " + ruteNr + ") - Avgang: " + tidspunkt.format(formatter);
    }
}