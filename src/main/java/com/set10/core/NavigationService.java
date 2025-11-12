package com.set10.core;

import java.nio.file.Path;
import java.security.PrivateKey;
import java.util.ArrayList;

import com.set10.core.DTO.DepartureDTO;
import com.set10.core.DTO.RouteDTO;
import com.set10.core.DTO.StopDTO;
import com.set10.core.interfaces.IDataRepository;

/**
 * Denne klassen er en form for API ment for å kunne utføre grunnleggende handlinger 
 * som har med navigasjon å gjøre. I.E. finne reiser, og lignende.
 */
public class NavigationService {

    private IDataRepository dataRepository;
    private PathFinder pathFinder;
    public PathFinder.NodeGraph nodeGraph;

    public NavigationService(IDataRepository dataRepository){
        this.dataRepository = dataRepository;
        this.pathFinder = new PathFinder();
        nodeGraph = pathFinder.buildNodeGraph(dataRepository);
    }


    /**
     * 
     * @param nameA name of stop the trip starts from
     * @param nameB name of stop the trip ends at
     * @return Trip, null if trip is not possible not possible
     */
    public Trip FindTrip(String nameA, String nameB){
  
        int stopIdA = dataRepository.getStopID(nameA);
        int stopIdB = dataRepository.getStopID(nameB);

        if( stopIdA == -1){
            return null;
        }

        if(stopIdB == -1){
            return null;
        }

        return pathFinder.calculatePath(nodeGraph, stopIdA, stopIdB);
    }
   
    // Brukes ikke til noe enda
    private class Position{
        public int x;
        public int y;
    }

    public boolean stopExists(String name){
        if (dataRepository.getStopID(name) != -1){
            return true;
        }
        return false;
    }

    public String findNearestStop(Position position){
        System.err.println("findNearestStop() is not implemented yet!");
        System.exit(1);
        return null;
    }

    public ArrayList<RouteDTO> getRoutes(){
        ArrayList<RouteDTO> routeDTOs = new ArrayList<>();
        for (Route route : dataRepository.getAllRoutes()){
            routeDTOs.add(new RouteDTO(route));
        }
        return routeDTOs;
    }

    public ArrayList<DepartureDTO> getRouteDeparturesForStop(int routeID, int stopID) {
        ArrayList<DepartureDTO> departureDTOs = new ArrayList<>();
        for (Departure departure:dataRepository.getRouteDeparturesForStop(routeID, stopID)){
            departureDTOs.add(new DepartureDTO(departure));
        }
        return departureDTOs;
    }

    public ArrayList<StopDTO> getAllStops(){
        ArrayList<StopDTO> stops = new ArrayList<>();
        for (Stop stop : dataRepository.getAllStops()){
            stops.add(new StopDTO(stop));
        }
        return stops;
    }

}
