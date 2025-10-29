package com.set10.core;

import java.util.ArrayList;


/** 
 * Klasse som inneholder metoder for å beregne reiser
 * Forhåpentligvis trenger den ikke å ha noen form for lokal data,
 * men heller være en form for statisk klasse, med statiske metoder/funksjoner
*/
public class Veiviser {

    public class Kant{
        public int fra;
        public int til;
        public float kostnad;
        public Kant(int fra, int til, float kostnad){
            this.fra = fra;
            this.til = til;
            this.kostnad = kostnad;
        }
    }

    public class Node {
        public int id = -1;
        public Stoppested sted;
        public ArrayList<Kant> kanter;
    }

    public static Reise beregnReise(Stoppested A, Stoppested B){
        
        // GÅ igjennom alle ruter, og legg til noder fra stoppesteder
        // Legg til kanter fra koblingene.
        // 

        return null;
    }


}
