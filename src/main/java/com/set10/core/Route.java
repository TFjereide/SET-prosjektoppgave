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

    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }
        if(!(object instanceof Route)){
            return false;
        }

        Route other = (Route) object;

        if( other.id != id){
            return false;
        }

        if(stops.size() != other.stops.size()){
            return false;
        }

        for (int i = 0; i < stops.size();i++){
            if(!stops.get(i).equals(other.stops.get(i))){
                return false;
            }
        } 
        return true;
    }


    public String toString(){
        return "Rute: " + this.id;
    }
}