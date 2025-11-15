package com.set10.core.DTO;

import java.time.LocalTime;

import com.set10.core.Departure;

public record DepartureDTO(int id, int routeID, int stopID, LocalTime time) {
    
    public DepartureDTO(Departure departure){
        this(departure.id, departure.routeId, departure.stopId, departure.time);
    }
}
