package com.set10.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    String path = "data/data.txt";

    public DatabaseText(){
    }

    public DatabaseText(String path){
        this.path = path;
    }

    public void dumpDataCache(RepositoryDataCache cache) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        
        var allDepartures = cache.departures;
        System.out.println("Writing " + allDepartures.size()+ " departures to textfile");
        for(int i = 0; i < allDepartures.size(); i++){
            Departure avgang = allDepartures.get(i);
            writer.append("a;" + avgang.id + ";" + avgang.routeId+";" + avgang.stopId+ ";" + avgang.time +"\n");
        }

        var allStops = cache.stops;
        System.out.println("Writing " + allStops.size()+ " stops to textfile");
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
        System.out.println("Writing " + allRoutes.size()+ " routes to textfile");
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

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        var allTickets = cache.tickets;
        System.out.println("Writing " + allTickets.size()+ " tickets to textfile");
        for(int i = 0; i < allTickets.size(); i++){
            Ticket ticket = allTickets.get(i);
            String zoneString = "";
            for (int j = 0; j< ticket.validForZones.size(); j++){
                zoneString+=ticket.validForZones.get(j) + ",";
            }
            writer.append("i;" + ticket.id + ";" + ticket.type + ";" +ticket.validFrom.format(formatter) + ";" + ticket.validTo.format(formatter) + ";" + zoneString + "\n");
        }

        var allUsers = cache.users;
        System.out.println("Writing " + allUsers.size()+ " users to textfile");
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

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
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
                Ticket ticket = new Ticket(Ticket.Type.valueOf(bits[2]), LocalDateTime.parse(bits[3],formatter));
                ticket.id = Integer.parseInt(bits[1]);
                ticket.validTo = LocalDateTime.parse(bits[4],formatter);
                for(String zone: bits[5].split(",")){
                    ticket.addZone(Integer.parseInt(zone));
                }
                cache.tickets.add(ticket);

            }else if(line.startsWith("b;")){
                String[] bits = line.split(";");
                User user = new User(Integer.parseInt(bits[1]),bits[2]);

                if(bits.length > 3){
                    String[] aktiveBilletterStr = bits[3].split(",");
                    for(String strId : aktiveBilletterStr){
                        Ticket billett = cache.tickets.get(Integer.parseInt(strId));
                        user.activeTickets.add(billett);
                    }
                }
                if(bits.length > 4){
                    String[] gamleBilletterStr = bits[4].split(",");
                    for(String strId : gamleBilletterStr){
                        Ticket billett = cache.tickets.get(Integer.parseInt(strId));
                        user.oldTickets.add(billett);
                    }
                }

                cache.users.add(user);
            }
        }
        reader.close();
        return cache;
    }

}
