package com.set10.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NavigationContainerTests {

    @Test
    @DisplayName("can create and link navigation data")
    void createAndLinkNavigationData() {
        NavigationContainer navcontainer = new NavigationContainer();
        navcontainer.stoppesteder.add(new Stoppested(0, "a"));
        navcontainer.stoppesteder.add(new Stoppested(1, "b"));
        navcontainer.stoppesteder.add(new Stoppested(2, "c"));
        navcontainer.stoppesteder.add(new Stoppested(3, "d"));
        navcontainer.stoppesteder.add(new Stoppested(4, "e"));

        Rute rute = new Rute(0);
        navcontainer.ruter.add(rute);

        rute.leggTilStopp(navcontainer.stoppesteder.get(0));
        rute.leggTilStopp(navcontainer.stoppesteder.get(1));
        rute.leggTilStopp(navcontainer.stoppesteder.get(2));
        rute.leggTilStopp(navcontainer.stoppesteder.get(3));
        rute.leggTilStopp(navcontainer.stoppesteder.get(4));

        assertEquals(rute.stops.get(0), navcontainer.stoppesteder.get(0));
        assertEquals(rute.stops.get(2), navcontainer.stoppesteder.get(2));

        assertEquals(rute.stops.size(), 5);
        assertEquals(navcontainer.stoppesteder.size(), 5);
    }

}
