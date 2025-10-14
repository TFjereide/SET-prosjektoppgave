package com.set10.core;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Billett {
    public String type;      //enkel, periode osv.
    public LocalDateTime startTid;
    public LocalDateTime sluttTid;
    public List<Integer> gyldigForSoner;


    public Billett(String type, LocalDateTime startTid) {
        this.type = type.toLowerCase();
        this.startTid = startTid;
        this.gyldigForSoner = new ArrayList<>(); // Lager bare listen, en annen metode for å legge til soner.

        if (this.type.equals("enkel")) {

            this.sluttTid = startTid.plusMinutes(90);
        } else if (this.type.equals("periode")) {

            this.sluttTid = startTid.plusDays(30);
        } else {

            this.sluttTid = startTid;
        }
    }

    public void leggTilSone(int sone) {
        if (!gyldigForSoner.contains(sone)) {
            gyldigForSoner.add(sone);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "\nBillett:\n" +
                "Type: " + type +
                "\nStarttid: " + startTid.format(formatter) +
                "\nSlutttid: " + sluttTid.format(formatter) +
                "\nGyldig for soner: " + gyldigForSoner.toString();
    }
}
