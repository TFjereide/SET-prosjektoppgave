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
    public boolean equals(Object object){
        if (object == this){
            return true;
        }
        if(!(object instanceof Departure)){
            return false;
        }

        Departure other = (Departure) object;

        if( other.id != id){
            System.err.println("id is not the same");
            return false;
        }
        if(other.routeId != routeId){
            System.err.println("routeId is not the same");
            return false;
        }
        if(other.stopId != stopId){
            System.err.println("stopID is not the same");
            return false;
        }
        if(other.time.compareTo(time) != 0){
            System.err.println("time is not the same");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return "(Rute " + routeId + ") - Avgang: " + time.format(formatter);
    }

}