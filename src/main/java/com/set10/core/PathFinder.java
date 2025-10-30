package com.set10.core;

import java.util.ArrayList;


/** 
 * Klasse som inneholder metoder for å beregne reiser
 * Forhåpentligvis trenger den ikke å ha noen form for lokal data,
 * men heller være en form for statisk klasse, med statiske metoder/funksjoner
*/
public class PathFinder {

    public class Edge{
        public int from;
        public int to;
        public float cost;
        public Edge(int from, int to, float cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public class Node {
        public int id = -1;
        public Stop stop;
        public ArrayList<Edge> edges;
    }

    public static Trip calculatePath(Stop A, Stop B){
        
        // GÅ igjennom alle ruter, og legg til noder fra stoppesteder
        // Legg til kanter fra koblingene.
        // 

        return null;
    }


}
