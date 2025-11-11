package com.set10.core;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.set10.database.DatabaseText;

@DisplayName("Test Pathfinder")
public class Test_PathFinder {

    public DummyDataRepository repo;
    public PathFinder pathfinder;
    public PathFinder.NodeGraph graph;


    @BeforeEach
    public void setUp(){
        repo = new DummyDataRepository(new DatabaseText("data\\testdata.txt"));

        repo.ticketCache.clear();
        repo.userCache.clear();
        repo.stopCache.clear();
        repo.routeCache.clear();
        repo.departureCache.clear();
        
        int routeId0 = repo.createRoute(new Route());
        int routeId1 = repo.createRoute(new Route());

        int stopId = repo.createStop("start 1",0); 
        repo.addDepartureToStop(routeId0, stopId ,  "01:00");
        stopId = repo.createStop("A",0); 
        repo.addDepartureToStop(routeId0, stopId ,  "01:01");
        stopId = repo.createStop("B",0); 
        repo.addDepartureToStop(routeId0, stopId ,  "01:02");
        stopId = repo.createStop("C",0); 
        repo.addDepartureToStop(routeId0, stopId ,  "01:03");
        stopId = repo.createStop("end 1",0); 
        repo.addDepartureToStop(routeId0, stopId ,  "01:04");
        
        stopId = repo.createStop("start 2",0); 
        repo.addDepartureToStop(routeId1, stopId ,  "01:05");
        repo.addDepartureToStop(routeId1, repo.getStopID("C") ,  "01:06");
        stopId = repo.createStop("D",0); 
        repo.addDepartureToStop(routeId1, stopId ,  "01:07");
        stopId = repo.createStop("end 2",0); 
        repo.addDepartureToStop(routeId1, stopId ,  "01:08");

        pathfinder = new PathFinder();
        graph = pathfinder.buildNodeGraph(repo);
        graph.print();
    }

    @AfterEach
    public void tearDown(){
        repo = null;
        pathfinder = null;
        graph = null;
    } 

    @Test
    @DisplayName("test adding nodes to graph")
    public void testAddNodes(){
        assertEquals(true, graph.nodes.size() > 0, "The nodegraph was not initialized!");
        for(int i = 0; i< graph.nodes.size(); i++){
            PathFinder.Node node = graph.nodes.get(i);
            assertEquals(false, graph.getNodeId(node.stop.id) == -1);
            assertEquals(true, i == graph.getNodeId(node.stop.id));
        }
    
    }


    @Test
    @DisplayName("test creating nodegraph")
    public void TestCreateNodegraph(){
        
        assertEquals(true, graph.nodes.size() > 0, "The nodegraph was not initialized!");
        {
            int id1 = repo.getStopID("start 1");
            int id2 = repo.getStopID("A");
            PathFinder.Node node1 = graph.getNodeByItemId(id1);
            int node2id = graph.getNodeId(id2);
            assertEquals(true, node1.hasEdge(node2id)); 
        }
        {
            int id1 = repo.getStopID("start 2");
            int id2 = repo.getStopID("C");
            PathFinder.Node node1 = graph.getNodeByItemId(id1);
            int node2id = graph.getNodeId(id2);
            assertEquals(true, node1.hasEdge(node2id)); 
        }
        {
            int id1 = repo.getStopID("C");
            int id2 = repo.getStopID("D");
            PathFinder.Node node1 = graph.getNodeByItemId(id1);
            int node2id = graph.getNodeId(id2);
            assertEquals(true, node1.hasEdge(node2id)); 
        }
        {
            int id1 = repo.getStopID("start 1");
            int id2 = repo.getStopID("end 2");
            PathFinder.Node node1 = graph.getNodeByItemId(id1);
            int node2id = graph.getNodeId(id2);
            assertEquals(false, node1.hasEdge(node2id)); 
        }
    }

    @Test
    @DisplayName("Test Calculate path")
    public void TestCalculatePath(){
        
        {
            int start = repo.getStopID("start 1");
            assertEquals(false, start == -1);

            int end = repo.getStopID("end 1");
            assertEquals(false, end == -1);

            Trip trip1 = pathfinder.calculatePath(graph, start, end);

            ArrayList<Stop> targetStops = new ArrayList<>();
            targetStops.add(repo.getStop("start 1"));
            targetStops.add(repo.getStop("A"));
            targetStops.add(repo.getStop("B"));
            targetStops.add(repo.getStop("C"));
            targetStops.add(repo.getStop("end 1"));

            assertEquals(false, trip1 == null);
            assertArrayEquals(targetStops.toArray(), trip1.stops.toArray());
        }

        {
            int start = repo.getStopID("start 2");
            assertEquals(false, start == -1);

            int end = repo.getStopID("end 2");
            assertEquals(false, end == -1);

            Trip trip1 = pathfinder.calculatePath(graph, start, end);

            ArrayList<Stop> targetStops = new ArrayList<>();
            targetStops.add(repo.getStop("start 2"));
            targetStops.add(repo.getStop("C"));
            targetStops.add(repo.getStop("D"));
            targetStops.add(repo.getStop("end 2"));

            assertEquals(false, trip1 == null);
            assertArrayEquals(targetStops.toArray(), trip1.stops.toArray());
        }
    }
}
