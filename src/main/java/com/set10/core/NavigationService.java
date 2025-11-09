package com.set10.core;

import java.nio.file.Path;
import java.security.PrivateKey;
import java.util.ArrayList;

import com.set10.core.DTO.RouteDTO;
import com.set10.core.DTO.StopDTO;

/**
 * Denne klassen er en form for API ment for å kunne utføre grunnleggende handlinger 
 * som har med navigasjon å gjøre. I.E. finne reiser, og lignende.
 */
public class NavigationService {

    private DataRepository dataRepository;
    private PathFinder pathFinder;
    private PathFinder.NodeGraph nodeGraph;
   

    public NavigationService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
        this.pathFinder = new PathFinder();
        nodeGraph = pathFinder.buildNodeGraph(dataRepository);
    }


    public Trip FindRoute(String A, String B){
        return null;
    }
   
    private class Position{
        public int x;
        public int y;
    }

    public String findNearestStop(Position position){
        return null;
    }

    public ArrayList<RouteDTO> getRoutes(){
        return null;
    }

    public ArrayList<StopDTO> getStops(){
        ArrayList<StopDTO> stops = new ArrayList<>();
        for (Stop stop : dataRepository.stopCache){
            stops.add(new StopDTO(stop));
        }
        return stops;
    }

}
