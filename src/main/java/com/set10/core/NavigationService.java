package com.set10.core;

/**
 * Denne klassen er en form for API ment for å kunne utføre grunnleggende handlinger 
 * som har med navigasjon å gjøre. I.E. finne reiser, og lignende.
 */
public class NavigationService {

    public DataRepository dataRepository;

    public NavigationService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    // Vil eventuelt ha slike metoder
    public Trip FindRoute(Stop A, Stop B){
        Trip trip  = PathFinder.calculatePath(A, B);
        return trip;
    }
    
    // public reise FinnNærmesteStoppested(Posisjon posisjon){}

}
