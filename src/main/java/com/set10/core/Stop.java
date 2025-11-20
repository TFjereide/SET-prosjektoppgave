package com.set10.core;

import java.util.ArrayList;
import java.util.HashSet;

public class Stop {
    public int id;
    public String name;
    public ArrayList<Departure> departures = new ArrayList<>();
    public HashSet<Integer> routes = new HashSet<>();
    public int zone;

    public Stop(String navn) {
        this.name = navn;
    }

    public Stop(int id, String navn) {
        this.id = id;
        this.name = navn;
    }

    public Stop(String navn, int sone) {
        this.name = navn;
        this.zone = sone;
    }

    public void addDeparture(Departure departure) {
        departures.add(departure);
    }

    public void addRoute(int routeID){
        routes.add(routeID);
    }

    public void visAvganger() {
        System.out.println("Avganger fra " + name + ":");
        for (Departure a : departures) {
            System.out.println("  " + a);
        }
    }

    public int getZone() {
        return zone;
    }

    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }
        if(!(object instanceof Stop)){
            return false;
        }

        Stop other = (Stop) object;

        if(departures.size()!= other.departures.size()){
            System.err.println("departures is not the same");
            return false;
        }
        for(int i = 0; i < departures.size(); i++){
            if(!departures.get(i).equals(other.departures.get(i))){
                System.err.println("departure " + i + " is not the same!");
                return false;
            }
        }


        if (!routes.equals(other.routes)){
            System.err.println("routes is not the same");
            return false;
        }
        if( other.id != id){
            System.err.println("id is not the same");
            return false;
        }
        if(!other.name.equals(name)){
            System.err.println("name is not the same");
            return false;
        }
        if(other.zone != zone){
            System.err.println("zone is not the same");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "ID: " + id + " name: '" + name + "'";
    }

    public ArrayList<Departure> hentAvganger() {
        return departures;
    }
}
