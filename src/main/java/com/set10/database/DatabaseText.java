package com.set10.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.set10.core.Route;
import com.set10.core.Stop;
import com.set10.core.Departure;
import com.set10.core.Ticket;
import com.set10.core.User;
import com.set10.core.interfaces.IDatabase;

/* 
 * Dataformat skamløst stjålet fra .obj:
 * a: avgang
 * s: stoppested
 * i: billett
 * b: bruker
 * r: rute
*/

public class DatabaseText implements IDatabase{
    String path = "data\\data.txt";

    public DatabaseText(){
    }

    public DatabaseText(String path){
        this.path = path;
    }

    public void dumpDataCache(RepositoryDataCache cache) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        
        var allDepartures = cache.departures;
        for(int i = 0; i < allDepartures.size(); i++){
            Departure avgang = allDepartures.get(i);
            writer.append("a;" + avgang.id + ";" + avgang.routeID+";" + avgang.stopID+ ";" + avgang.time +"\n");
        }

        var allStops = cache.stops;
        for(int i = 0; i < allStops.size(); i++){
            Stop stop = allStops.get(i);

            String departures = ""; 
            for(int j = 0; j < stop.departures.size()-1; j++){
                departures += stop.departures.get(j).id +",";
            }
            departures += stop.departures.getLast().id;
            
            String routes = "";
            var routeList = stop.routes.toArray();
            for(int j = 0; j < routeList.length-1; j++){
                routes += routeList[j]+",";
            }
            routes += routeList[routeList.length-1];

            writer.append("s;" + stop.id + ";"+ stop.name + ";" + departures + ";"+ routes +"\n");
        }

        var allRoutes = cache.routes;
        for(int i = 0; i < allRoutes.size(); i++){
            Route rute = allRoutes.get(i);
            String stop = "";
            if (rute.stops.size() != 0){
                for(int j = 0; j < rute.stops.size()-1; j++){
                    stop += rute.stops.get(j).id +",";
                }
                stop += rute.stops.getLast().id;
            }else{
                System.err.println(rute +" har ingen stop!");
            }
            writer.append("r;"+rute.id +";"+stop+"\n");
        }

        var allTickets = cache.tickets;
        for(int i = 0; i < allTickets.size(); i++){
            Ticket billett = allTickets.get(i);
            writer.append("i;" + billett.id + ";" + billett.type + ";" +billett.validFrom + ";" + billett.validTo + "\n");
        }

        var allUsers = cache.users;
        for(int i = 0; i < allUsers.size(); i++){
            User bruker = allUsers.get(i);
            String aktiveb = "";
            if (!bruker.activeTickets.isEmpty()){
                for(int j = 0; j < bruker.activeTickets.size()-1; j++){
                    aktiveb += bruker.activeTickets.get(j).id +",";
                }
                aktiveb += bruker.activeTickets.getLast().id;
            }

            String gamleb = "";
            if(!bruker.oldTickets.isEmpty()){
                for(int j = 0; j < bruker.oldTickets.size()-1; j++){
                    gamleb += bruker.oldTickets.get(j).id +",";
                }
                gamleb += bruker.oldTickets.getLast().id;
            }
            writer.append("b;" + bruker.id + ";" + bruker.name+";"+aktiveb + ";" + gamleb + "\n");
        }

        
        writer.close();
    }



    public RepositoryDataCache requestCacheData() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(path));
        RepositoryDataCache cache = new RepositoryDataCache();
        cache.users = new ArrayList<>();
        cache.tickets = new ArrayList<>();
        cache.stops = new ArrayList<>();
        cache.routes = new ArrayList<>();
        cache.departures = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null){
            if(line.startsWith("a;")){
                String[] bits = line.split(";");
                cache.departures.add(
                    new Departure(
                        Integer.parseInt(bits[1]),  // id
                        Integer.parseInt(bits[2]),  // ruteid
                        Integer.parseInt(bits[3]),  // stoppestedid
                        LocalTime.parse(bits[4])    // tidspunkt
                        )
                );
                
            }else if(line.startsWith("s;")){
                String[] bits = line.split(";");

                String[] avgIdxs = bits[3].split(",");
                ArrayList<Departure> departures = new ArrayList<>();
                for(String string : avgIdxs){
                    int idx = Integer.parseInt(string);
                    departures.add(cache.departures.get(idx));
                }
                Stop stop = new Stop(Integer.parseInt(bits[1]), bits[2]);
                stop.departures = departures;
                String[] routeIDsStr = bits[4].split(",");
                for(String string : routeIDsStr){
                    int idx = Integer.parseInt(string);
                    stop.addRoute(idx);
                }
                cache.stops.add(stop);

            }else if(line.startsWith("r;")){
                String[] bits = line.split(";");

                ArrayList<Stop> stop = new ArrayList<>();
                if(bits.length > 2){
                    String[] stoppidxs = bits[2].split(",");
                    for(String string : stoppidxs){
                        int idx = Integer.parseInt(string);
                        stop.add(cache.stops.get(idx));
                    }
                }


                cache.routes.add(
                    new Route(Integer.parseInt(bits[1]),stop)
                );

            }else if(line.startsWith("i;")){
                String[] bits = line.split(";");
                Ticket billett = new Ticket(Ticket.Type.valueOf(bits[2]), LocalDateTime.parse(bits[3]));
                cache.tickets.add(billett);

            }else if(line.startsWith("b;")){
                String[] bits = line.split(";");
                User bruker = new User(Integer.parseInt(bits[1]),bits[2]);

                if(bits.length > 3){
                    String[] aktiveBilletterStr = bits[3].split(",");
                    for(String strId : aktiveBilletterStr){
                        Ticket billett = cache.tickets.get(Integer.parseInt(strId));
                        bruker.activeTickets.add(billett);
                    }

                    String[] gamleBilletterStr = bits[4].split(",");
                    for(String strId : gamleBilletterStr){
                        Ticket billett = cache.tickets.get(Integer.parseInt(strId));
                        bruker.oldTickets.add(billett);
                    }
                }


                cache.users.add(bruker);
            }
        }
        reader.close();
        return cache;
    }

}
