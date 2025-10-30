package com.set10.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Departure {
    public int id;
    public int routeID;
    public int stopID;
    public LocalTime time;

    public Departure(int id, int routeID, int stopID, LocalTime time) {
        this.id = id;
        this.routeID = routeID;
        this.stopID = stopID;
        this.time = time;
    }

    public Departure(int routeID, int stopID, LocalTime time) {
        this.routeID = routeID;
        this.stopID = stopID;
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return "(Rute " + routeID + ") - Avgang: " + time.format(formatter);
    }

}