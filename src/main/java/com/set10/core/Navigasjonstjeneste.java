package com.set10.core;

import java.util.ArrayList;
import java.util.List;

/*
 * Denne klassen har en serie med metoder ment for å kunne utføre grunnleggende handlinger 
 * som har med navigasjon å gjøre. 
 */
public class Navigasjonstjeneste {

    public Datadepot dataDepot;

    public Navigasjonstjeneste(){
    }

    // Vil eventuelt ha slike metoder
    // public Reise FinnReise(Stoppested A, Stoppested B){}
    
    // public reise FinnNærmesteStoppested(Posisjon posisjon){}

    public List<Reiseforslag> FinnReiser(Reisesok sok){
        List<Reiseforslag> forslag = new ArrayList<>();

        for (Avgang avgang : sok.getFraStoppested().hentAvganger()) {
            Reiseforslag anbefalteReiser = new Reiseforslag(avgang, sok);
            anbefalteReiser.leggTilAvgang(avgang);
        }
        return forslag;
    }

}
