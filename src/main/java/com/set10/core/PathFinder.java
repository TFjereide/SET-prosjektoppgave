package com.set10.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.set10.core.interfaces.IDataRepository;


/** 
 * Klasse som inneholder metoder for å beregne reiser
 * Forhåpentligvis trenger den ikke å ha noen form for lokal data,
 * men heller være en form for statisk klasse, med statiske metoder/funksjoner
*/
public class PathFinder {

    public class Edge{
        public int to;
        public float cost;
        public Edge( int to, float cost){
            this.to = to;
            this.cost = cost;
        }
    }

    public class Node {
        public Stop stop;
        public ArrayList<Edge> edges = new ArrayList<>();

        public boolean addEdge(int nodeid, float cost){
            Edge edge = new Edge(nodeid, cost);
            edges.add(edge);
            return true;
        }

        public boolean hasEdge(int nodeid){
            for(Edge edge : edges){
                if (edge.to == nodeid){
                    return true;
                }
            }
            return false;
        }
    }

    public class NodeGraph{
        public ArrayList<Node> nodes = new ArrayList<>();
        // itemid -> nodeidx
        public HashMap<Integer, Integer> itemIDToNodeID = new HashMap<>();

        public int addNode(Node node){
            if(!itemIDToNodeID.containsKey(node.stop.id)){
                if (nodes.add(node)){
                    itemIDToNodeID.put(node.stop.id, nodes.size()-1);
                    return nodes.size()-1;
                }else{
                    System.err.println("Problem adding node " + node.stop.id +"to the graph.");
                }   

            }
            return -1;
        }
        
        public boolean hasConnection(int a, int b){
            return false;
        }

        public boolean hasNode(int stopId){
            return itemIDToNodeID.containsKey(stopId);
        }

        public int getNodeId(int itemId){
            if (itemIDToNodeID.containsKey(itemId)){
                return itemIDToNodeID.get(itemId);
            }
            return -1;
        }

        public Node getNodeByItemId(int itemId){
            int id = getNodeId(itemId);
            if(id == -1){
                return null;
            }
            return nodes.get(id);
        }


        public void print(){
            System.out.println("Nodegraph:");
            for (Node node : nodes){
                System.out.println("N->id:"+node.stop.id + " name:" + node.stop.name);
                for (Edge edge : node.edges){
                    System.out.println("    E->to:"+edge.to+" cost:"+edge.cost);
                }
            }
        }
    }
    /**
     * Entrypoint for recursive graphbuilder. 
     * Recursively trawls through stops on routes, adding them to a nodegraph.
     * @param repo The DataRepository that contains the route data
     * @return The complete nodegraph.
    */

    public NodeGraph buildNodeGraph(IDataRepository repo){
        var routes = repo.getAllRoutes();
        if(routes.size() == 0){
            System.err.println("Routecache empty! No routes to connect stops with.");
        }
        System.out.println("Building nodegraph...");
        NodeGraph nodeGraph = new NodeGraph();
        HashSet<Integer> visitedRoutes = new HashSet<>();
        for (Route route : routes){
            getConnectedStopsRecursive(routes, visitedRoutes, nodeGraph, route);  
        }
        return nodeGraph;
    }

    /**
     * 
     */
    void getConnectedStopsRecursive(ArrayList<Route> routes, HashSet<Integer> visitedRoutes, NodeGraph graph, Route route){
        if (visitedRoutes.contains(route.id)){
            return;
        }
        
        visitedRoutes.add(route.id);

        if(route.stops.size() == 0){
            return;
        }

        System.out.println("Adding " +route.stops.size()+" stops to the graph.");
        
        Stop firststop = route.stops.get(0);
        Node startnode = new Node();
        startnode.stop = firststop;
        graph.addNode(startnode);
        
        // TODO: implement actual cost
        for(int i = 0; i < route.stops.size()-1; i++){
            Stop stop = route.stops.get(i);
            Stop nextStop = route.stops.get(i+1);
            
            int nextNodeId = -1;
            if(!graph.hasNode(nextStop.id)){
                Node node = new Node();
                node.stop = nextStop;
                nextNodeId = graph.addNode(node);
            }else{
                nextNodeId = graph.getNodeId(nextStop.id);
            }
            
            // Will stop the recursion if the node is already in the graph.
            if(nextNodeId != -1){
                Node node = graph.getNodeByItemId(stop.id);
                Edge edge = new Edge(nextNodeId, 1);
                node.edges.add(edge);
                
                // Spread out from current stop if more routes go from here.
                for (int newroute:stop.routes){
                    Route newrouteinstance = routes.get(newroute);
                    if (newrouteinstance != null){
                        getConnectedStopsRecursive(routes, visitedRoutes,  graph, newrouteinstance);
                    }
                }
            }
        }
    }


    // https://www.redblobgames.com/pathfinding/a-star/introduction.html
    public Trip calculatePath(NodeGraph graph, int StopIDA, int StopIDB){
        
        int start = graph.getNodeId(StopIDA);
        if (start == -1){
            System.out.println("startnode does not exist.");
            return null;
        }
        
        int end = graph.getNodeId(StopIDB);
        if (end == -1){
            System.out.println("endnode does not exist.");
            return null;
        }

        LinkedList<Integer> frontier = new LinkedList<Integer>();
        frontier.add(start);

        HashMap<Integer, Integer> came_from = new HashMap<>();
        came_from.put(start, -1);

        // Flowmap
        while(!frontier.isEmpty()){
            int current = frontier.pop();
            for (Edge edge : graph.nodes.get(current).edges){
                if(!came_from.containsKey(edge.to)){
                    frontier.add(edge.to);
                    came_from.put(edge.to, current);
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        int current = end;
        while(current != start){
            path.add(current);
            current = came_from.get(current);
        }

        Trip trip = new Trip();        

        // A bit nasty
        trip.stops.add(graph.getNodeByItemId(StopIDA).stop);

        //Convert to stops
        for(int idx : path.reversed()){
            trip.stops.add(graph.nodes.get(idx).stop);
        }

        return trip;
    }
}
