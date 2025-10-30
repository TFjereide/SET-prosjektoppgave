package com.set10.core;
import java.time.LocalDateTime;


public class Validation {
    

    public static boolean isTicketValidTime(Ticket ticket) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(ticket.validFrom) && now.isBefore(ticket.validTo);
    }

    public static boolean isTicketValidZone(Ticket billett, int zone) {
        return billett.validForZones.contains(zone);
    }

}
