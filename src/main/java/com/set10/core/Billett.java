package com.set10.core;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Billett {  

    public enum Type{
        Enkel,
        Periode
    }

    public int id;
    public Type type;
    
    public LocalDateTime startTid;
    public LocalDateTime sluttTid;


    public Billett(Type type, LocalDateTime startTid) {
        this.type = type;
        this.startTid = startTid;

        switch(type){
            case Enkel:
                this.sluttTid = startTid.plusMinutes(90);
                break;
            case Periode:
                this.sluttTid = startTid.plusDays(30);
                break;
            default:
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
