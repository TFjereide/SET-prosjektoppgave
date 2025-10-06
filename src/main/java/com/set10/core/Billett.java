package com.set10.core;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Billett {
    public String type;      //enkel, periode osv.
    public LocalDateTime startTid;
    public LocalDateTime sluttTid;


    public Billett(String type, LocalDateTime startTid) {
        this.type = type.toLowerCase();
        this.startTid = startTid;

        if (this.type.equals("enkel")) {

            this.sluttTid = startTid.plusMinutes(90);
        } else if (this.type.equals("periode")) {

            this.sluttTid = startTid.plusDays(30);
        } else {

            this.sluttTid = startTid;
        }
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "\nBillett:\n" +
                "Type: " + type +
                "\nStarttid: " + startTid.format(formatter) +
                "\nSlutttid: " + sluttTid.format(formatter);
    }
}
