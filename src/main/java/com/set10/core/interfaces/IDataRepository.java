package com.set10.core.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import com.set10.core.Departure;
import com.set10.core.Route;
import com.set10.core.Stop;
import com.set10.core.Ticket;
import com.set10.core.User;

/**
 * Guarrantees an API that lets you get data from an undisclosed source.
 * Must remain stable over time to ensure sane development.
 */
public interface IDataRepository {

    // Users
    public int createUser(User user);
    
    public User getUser(int id);
    
    public ArrayList<User> getAllUsers();
    
    // Stops
    public int createStop(String name, int zone);
    
    public Stop getStop(int id);
    
    public Stop getStopByName(String name);
    
    public int getStopIDByName(String name);
    
    public ArrayList<Stop> getAllStops();

    // Departures
    public int createDeparture(Departure departure);
    
    public void addDepartureToStop(int routeId, int stopId, String... departureTimes);

    public ArrayList<Departure> getRouteDeparturesForStop(int routeId, int stopId);
    
    public ArrayList<Departure> getAllDepartures();

    // Routes
    public int createRoute(Route route);

    public Route getRoute(int id);
    
    public ArrayList<Route> getAllRoutes();

    // Tickets
    public Ticket createTicket(Ticket.Type type, HashSet<Integer> zones, LocalDateTime fromTime);
    
    public Ticket getTicket(int ticketId);
    
    public ArrayList<Ticket> getallTickets();

}
