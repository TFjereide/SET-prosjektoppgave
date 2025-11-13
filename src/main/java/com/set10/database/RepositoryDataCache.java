package com.set10.database;

import java.util.ArrayList;

import com.set10.core.Departure;
import com.set10.core.Route;
import com.set10.core.Stop;
import com.set10.core.Ticket;
import com.set10.core.User;

public class RepositoryDataCache {

    public ArrayList<User> users = new ArrayList<>(); 
    public ArrayList<Ticket> tickets = new ArrayList<>(); 

    public ArrayList<Stop> stops = new ArrayList<>();
    public ArrayList<Route> routes = new ArrayList<>();

    public ArrayList<Departure> departures = new ArrayList<>();

    public RepositoryDataCache(){}

    public RepositoryDataCache( ArrayList<User> userCache, ArrayList<Ticket> ticketCache, ArrayList<Stop> stopCache, ArrayList<Route> routeCache, ArrayList<Departure> departureCache){
        this.users = userCache;
        this.tickets = ticketCache;
        this.stops = stopCache;
        this.routes = routeCache;
        this.departures = departureCache;
    }

}
