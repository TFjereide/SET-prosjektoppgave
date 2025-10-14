package com.set10.core;
import java.time.LocalDateTime;


public class Validering {
    

    public static boolean erBillettGyldigTid(Billett billett) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(billett.startTid) && now.isBefore(billett.sluttTid);
    }

    public static boolean erBillettGyldigSone(Billett billett, int sone) {
        return billett.gyldigForSoner.contains(sone);
    }



}
