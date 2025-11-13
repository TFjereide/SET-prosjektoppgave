package com.set10.core.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import com.set10.core.Departure;
import com.set10.core.Route;
import com.set10.core.Stop;
import com.set10.core.Ticket;
import com.set10.core.User;

public interface IDataRepository {

    // Users
    public int createUser(User user);
    
    public User getUser(int id);
    
    public ArrayList<User> getAllUsers();
    
    // Stops
    public int createStop(String name, int zone);
    
    public Stop getStop(int id);
    
    public Stop getStop(String name);
    
    public int getStopID(String name);
    
    public ArrayList<Stop> getAllStops();

    // Departures
    public void addDepartureToStop(int ruteID, int stoppID, String... tider);
    
    public int createDeparture(Departure departure);

    public ArrayList<Departure> getRouteDeparturesForStop(int routeID, int stopID);
    
    public ArrayList<Departure> getAllDepartures();

    // Routes
    public int createRoute(Route route);

    public Route getRoute(int id);
    
    public ArrayList<Route> getAllRoutes();

    // Tickets
    public ArrayList<Ticket> getallTickets();
    public Ticket createTicket(Ticket.Type type, HashSet<Integer> zones, LocalDateTime fromTime);


}
