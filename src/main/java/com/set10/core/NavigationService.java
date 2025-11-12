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


    public Trip FindRoute(String A, String B){
        System.err.println("FindRoute() is not implemented yet!");
        System.exit(1);
        return null;
    }
   
    private class Position{
        public int x;
        public int y;
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
