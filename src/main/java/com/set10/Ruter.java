package com.set10;
import java.util.ArrayList;

public class Ruter {
    ArrayList<Stoppested> stops = new ArrayList<>();

    public void leggTilStopp(Stoppested stoppested) {
        stops.add(stoppested);
    }

    public double calculateRouteLength() {
        int numberOfStops = stops.size();
        double totalDistance = 0;

        for (int x = 0; x < numberOfStops - 1; x++) {
            Stoppested currentStop = stops.get(x);
            Stoppested nextStop = stops.get(x + 1);
            totalDistance += GPSService.distanceBetweenStops(currentStop, nextStop);
        }

        return totalDistance;
    }

    public void visRoute() {
        System.out.println("Rute med " + stops.size() + " stopp:");
        for (Stoppested s : stops) {
            System.out.println("  - " + s);
        }
        System.out.println("Total lengde: " + calculateRouteLength() + " km");
    }
}