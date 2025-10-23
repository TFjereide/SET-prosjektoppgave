package com.set10.core;

/**
 * Denne klassen er en form for API ment for å kunne utføre grunnleggende handlinger 
 * som har med navigasjon å gjøre. I.E. finne reiser, og lignende.
 */
public class Navigasjonstjeneste {

    public Datadepot dataDepot;

    public Navigasjonstjeneste(Datadepot dataDepot){
        this.dataDepot = dataDepot;
    }
    // Vil eventuelt ha slike metoder
    public Reise FinnReise(Stoppested A, Stoppested B){
        Reise reise  = Veiviser.beregnReise(A, B);
        return reise;
    }
    
    // public reise FinnNærmesteStoppested(Posisjon posisjon){}

}
