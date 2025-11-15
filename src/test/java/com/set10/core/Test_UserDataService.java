package com.set10.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Test_UserDataService {

    UserDataService userDataService;

    @BeforeEach
    public void setUp(){
        var repo = new DummyDataRepository(null);
        repo.generateDummyData();
        userDataService = new UserDataService(repo);
    }

    @AfterEach
    public void tearDown(){
        userDataService = null;
    } 

    // Not an important test initially, but when the logic gets more complex
    // this will be important.
    @Test
    @DisplayName("give user ticket for trip")
    public void testGiveUserTicketForTrip(){
        Trip trip = new Trip();
        trip.zones.add(0);
        assertTrue(userDataService.setUserActiveTrip(trip, 0));
        assertNotNull(userDataService.giveUserTicketForTrip(0, Ticket.Type.Single, trip));
        assertNotNull(userDataService.giveUserTicketForTrip(0, Ticket.Type.Period, trip));

        assertEquals(false, userDataService.setUserActiveTrip(trip, Integer.MAX_VALUE));
        assertNull(userDataService.giveUserTicketForTrip(Integer.MAX_VALUE, Ticket.Type.Single, trip));
    }
}
