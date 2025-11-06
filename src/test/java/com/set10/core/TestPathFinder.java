package com.set10.core;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.set10.database.DatabaseText;

public class TestPathFinder {


    @Test
    public void TestCreateNodeNetwork(){
        DataRepository repo = new DataRepository(new DatabaseText("data\\testdata.txt"));

        Route route0 = new Route(0);
        Route route1 = new Route(1);
        
        int stopId = repo.createStop("start 1",0); 
        repo.addDepartureToStop(route0.id, stopId ,  "05:25");
        stopId = repo.createStop("A",0); 
        repo.addDepartureToStop(route0.id, stopId ,  "05:25");
        stopId = repo.createStop("B",0); 
        repo.addDepartureToStop(route0.id, stopId ,  "05:25");
        stopId = repo.createStop("C",0); 
        repo.addDepartureToStop(route0.id, stopId ,  "05:25");
        stopId = repo.createStop("end 1",0); 
        repo.addDepartureToStop(route0.id, stopId ,  "05:25");
        
        stopId = repo.createStop("start 2",0); 
        repo.addDepartureToStop(route1.id, stopId ,  "05:25");
        stopId = repo.createStop("D",0); 
        repo.addDepartureToStop(route1.id, stopId ,  "05:25");
        stopId = repo.createStop("end 2",0); 
        repo.addDepartureToStop(route1.id, stopId ,  "05:25");

        PathFinder pathfinder = new PathFinder();
        PathFinder.NodeGraph graph = pathfinder.buildNodeGraph(repo);
        
        pathfinder.calculatePath(graph, null, null);



    }

}
