package com.set10.core;

import java.util.ArrayList;
import java.util.HashSet;


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
        public int id = -1;
        public Stop stop;
        public ArrayList<Edge> edges = new ArrayList<>();
    }

    public class NodeGraph{
        public ArrayList<Node> nodes = new ArrayList<>();
        public HashSet<Integer> presentItems = new HashSet<>();

        public boolean addNode(Node node){
            if(presentItems.add(node.id)){
                nodes.add(node);
                return true;
            }
            return false;
        }
        public void print(){
            for (Node node : nodes){
                System.out.println("N:"+node.id);
                for (Edge edge : node.edges){
                    System.out.println("    E:"+edge.to+","+edge.cost);
                }
            }
        }
    }

    public Trip calculatePath(DataRepository repo, Stop A, Stop B){

        // Construct node graph before doing pathfinding
        // TODO: this should be cached
        NodeGraph nodeGraph = new NodeGraph();
        HashSet<Integer> visitedRoutes = new HashSet<>();
        for (int routeID : A.routes){
            Route routeInstance = repo.routeCache.get(routeID);
            if (routeInstance != null){
                getConnectedStopsRecursive(repo.routeCache, visitedRoutes, nodeGraph, routeInstance);
            }
        }
        nodeGraph.print();

        return null;
    }
    
    /**
     * Recursively trawls through stops on routes,
     */
    void getConnectedStopsRecursive(ArrayList<Route> routes, HashSet<Integer> visitedRoutes, NodeGraph graph, Route route){
        if (visitedRoutes.contains(route.id)){
            return;
        }
        visitedRoutes.add(route.id);
        for(int i = 1; i < route.stops.size(); i++){
            Stop stop = route.stops.get(i-1);
            Stop nextStop = route.stops.get(i);
            
            // TODO: implement actual cost
            Node node = new Node();
            node.id = stop.id;

            // Will stop the recursion if the node is already in the graph.
            if(graph.addNode(node)){
                Edge edge = new Edge(nextStop.id, 1);
                node.edges.add(edge);
                
                for (int newroute :stop.routes){
                    Route newrouteinstance = routes.get(newroute);
                    if (newrouteinstance != null){
                        getConnectedStopsRecursive(routes, visitedRoutes,  graph, newrouteinstance);
                    }
                }
            }
        }
    }
        
}
