package com.set10.core;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Route {
    public int id;
    public ArrayList<Stop> stops = new ArrayList<>();

    public Route(){}
    
    public Route(int id) {
        this.id = id;
    };

    public Route(int id, ArrayList<Stop> stop) {
        this.id = id;
        this.stops = stop;
    };

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    public List<Integer> getZonesFromRoute() {
        Set<Integer> soner = new java.util.HashSet<>();
        for (Stop stopp : stops) {
            soner.add(stopp.getZone());
        }
        return new ArrayList<>(soner);
    }

    public String toString(){
        return "Rute: " + this.id;
    }
}