package com.set10.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Departure {
    public int id;
    public int routeId;
    public int stopId;
    public LocalTime time;

    public Departure(int id, int routeID, int stopID, LocalTime time) {
        this.id = id;
        this.routeId = routeID;
        this.stopId = stopID;
        this.time = time;
    }

    public Departure(int routeID, int stopID, LocalTime time) {
        this.routeId = routeID;
        this.stopId = stopID;
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return "(Rute " + routeId + ") - Avgang: " + time.format(formatter);
    }

}