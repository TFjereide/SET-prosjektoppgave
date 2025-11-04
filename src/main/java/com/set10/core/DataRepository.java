package com.set10.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Holder på data som applikasjonen behøver.
 * 
 */
public class DataRepository {

    public IDatabase database;

    public ArrayList<User> userCache = new ArrayList<>(); 
    public ArrayList<Ticket> ticketCache = new ArrayList<>(); 

    public ArrayList<Stop> stopCache = new ArrayList<>();
    public ArrayList<Route> routeCache = new ArrayList<>();
    public ArrayList<Departure> departureCache = new ArrayList<>();

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    // Burde fungere som Init
    public DataRepository(IDatabase database){
        this.database = database;
    }

    public void generateDummyData(){

        //Brukere
        createUser(new User("Administrator"));
        createUser(new User("Jonas Olsen"));
        createUser(new User("Issac Evinskog"));
        createUser(new User("Erika Hansen"));
        createUser(new User("Olga Bentsdotter"));

       //Ruter  
        Route r33 = new Route(33);
        opprettRute(r33);
        Route r34 = new Route(34);
        opprettRute(r34);
        Route r35 = new Route(35);
        opprettRute(r35);


        //Stoppesteder og avganger
        
        opprettStoppested(new Stop("Halden bussterminal")); //0
            leggTilAvgangerForStopp(r34.id, 0,  "05:25","06:25","07:25","08:25","09:25","10:25","11:25","12:25","13:25","14:25","15:25","16:25","17:25","19:25");
            leggTilAvgangerForStopp(r33.id, 0,  "05:10","06:10","07:10","08:10","09:10","10:10","11:10","12:10","13:10","14:10","15:10","16:10","17:40","19:40");
            leggTilAvgangerForStopp(r35.id, 0,  "05:40","06:40","07:40","08:40","09:40","10:40","11:40","12:40","13:40","14:40","15:40","16:40","17:40","19:10");
           
            //retur til bussterminal
            leggTilAvgangerForStopp(r34.id, 0,"05:55","06:55","07:55","08:55","09:55","10:55","11:55","12:55","13:55","14:55","15:55","16:55","17:55","19:55");
            leggTilAvgangerForStopp(r33.id, 0,  "05:40","06:40","07:40","08:40","09:40","10:40","11:40","12:40","13:40","14:40","15:40","16:40","18:10","20:10");
            leggTilAvgangerForStopp(r35.id, 0,  "06:10","07:10","08:10","09:10","10:10","11:10","12:10","13:10","14:10","15:10","16:10","17:10","18:10","19:40");
        
        opprettStoppested(new Stop("Fiskebrygga")); //1
            leggTilAvgangerForStopp(r34.id, 1,  "05:26","06:26","07:26","08:26","09:26","10:26","11:26","12:26","13:26","14:26","15:26","16:26","17:26","19:26");
            leggTilAvgangerForStopp(r33.id, 1,  "05:11","06:11","07:11","08:11","09:11","10:11","11:11","12:11","13:11","14:11","15:11","16:11","17:41","19:41");
            leggTilAvgangerForStopp(r35.id, 1,  "05:41","06:41","07:41","08:41","09:41","10:41","11:41","12:41","13:41","14:41","15:41","16:41","17:41","19:11");

        opprettStoppested(new Stop("Kulturkvartalet")); //2
            leggTilAvgangerForStopp(r34.id, 2,  "05:27","06:27","07:27","08:27","09:27","10:27","11:27","12:27","13:27","14:27","15:27","16:27","17:27","19:27");
            leggTilAvgangerForStopp(r33.id, 2,  "05:12","06:12","07:12","08:12","09:12","10:12","11:12","12:12","13:12","14:12","15:12","16:12","17:42","19:42");
            leggTilAvgangerForStopp(r35.id, 2,  "05:42","06:42","07:42","08:42","09:42","10:42","11:42","12:42","13:42","14:42","15:42","16:42","17:42","19:12");


        opprettStoppested(new Stop("Parken")); //3
            leggTilAvgangerForStopp(r34.id, 3,  "05:28","06:28","07:28","08:28","09:28","10:28","11:28","12:28","13:28","14:28","15:28","16:28","17:28","19:28");
            leggTilAvgangerForStopp(r33.id, 3,  "05:13","06:13","07:13","08:13","09:13","10:13","11:13","12:13","13:13","14:13","15:13","16:13","17:43","19:43");
            leggTilAvgangerForStopp(r35.id, 3,  "05:43","06:43","07:43","08:43","09:43","10:43","11:43","12:43","13:43","14:43","15:43","16:43","17:43","19:13");

        opprettStoppested(new Stop("Snippen Halden")); //4
            leggTilAvgangerForStopp(r34.id, 4,  "05:29","06:29","07:29","08:29","09:29","10:29","11:29","12:29","13:29","14:29","15:29","16:29","17:29","19:29");
            
        opprettStoppested(new Stop("Østre Lie"));//5
            leggTilAvgangerForStopp(r34.id, 5,  "05:36","06:36","07:36","08:36","09:36","10:36","11:36","12:36","13:36","14:36","15:36","16:36","17:36","19:36");
       
        opprettStoppested(new Stop("Bjørklund")); //6
            leggTilAvgangerForStopp(r34.id, 6,  "05:39","06:39","07:39","08:39","09:39","10:39","11:39","12:39","13:39","14:39","15:39","16:39","17:39","19:39");
        
        opprettStoppested(new Stop("Remmen Høgskolen")); //7
            leggTilAvgangerForStopp(r34.id, 7,  "05:42","06:42","07:42","08:42","09:42","10:42","11:42","12:42","13:42","14:42","15:42","16:42","17:42","19:42");
       
        opprettStoppested(new Stop("Park Hotell")); //8
            leggTilAvgangerForStopp(r34.id, 8,  "05:45","06:45","07:45","08:45","09:45","10:45","11:45","12:45","13:45","14:45","15:45","16:45","17:45","19:45");
        
        opprettStoppested(new Stop("Skofabrikken")); //9
            leggTilAvgangerForStopp(r34.id, 9,  "05:48","06:48","07:48","08:48","09:48","10:48","11:48","12:48","13:48","14:48","15:48","16:48","17:48","19:48");
            leggTilAvgangerForStopp(r33.id, 9,  "05:14","06:14","07:14","08:14","09:14","10:14","11:14","12:14","13:14","14:14","15:14","16:14","17:44","19:44");
        
        opprettStoppested(new Stop("Stranda")); //10
            leggTilAvgangerForStopp(r34.id, 10, "05:50","06:50","07:50","08:50","09:50","10:50","11:50","12:50","13:50","14:50","15:50","16:50","17:50","19:50");
            leggTilAvgangerForStopp(r35.id, 10, "06:05","07:05","08:05","09:05","10:05","11:05","12:05","13:05","14:05","15:05","16:05","17:05","18:05","19:35");

        opprettStoppested(new Stop("Bybrua Halden")); //11
            leggTilAvgangerForStopp(r34.id, 11, "05:51","06:51","07:51","08:51","09:51","10:51","11:51","12:51","13:51","14:51","15:51","16:51","17:51","19:51");
            leggTilAvgangerForStopp(r35.id, 11, "06:07","07:07","08:07","09:07","10:07","11:07","12:07","13:07","14:07","15:07","16:07","17:07","18:07","19:37");

        opprettStoppested(new Stop("Borgergata")); //12
            leggTilAvgangerForStopp(r34.id, 12, "05:52","06:52","07:52","08:52","09:52","10:52","11:52","12:52","13:52","14:52","15:52","16:52","17:52","19:52");
            leggTilAvgangerForStopp(r35.id, 12, "06:08","07:08","08:08","09:08","10:08","11:08","12:08","13:08","14:08","15:08","16:08","17:08","18:08","19:38");

        opprettStoppested(new Stop("Halden stasjon")); //13
            leggTilAvgangerForStopp(r34.id, 13, "05:53","06:53","07:53","08:53","09:53","10:53","11:53","12:53","13:53","14:53","15:53","16:53","17:53","19:53");
            leggTilAvgangerForStopp(r33.id, 13, "05:38","06:38","07:38","08:38","09:38","10:38","11:38","12:38","13:38","14:38","15:38","16:38","18:08","20:08"); 
            leggTilAvgangerForStopp(r35.id, 13, "06:08","07:08","08:08","09:08","10:08","11:08","12:08","13:08","14:08","15:08","16:08","17:08","18:08","19:38");

        opprettStoppested(new Stop("Halden politistasjon")); //14
            leggTilAvgangerForStopp(r33.id, 14, "05:10","06:10","07:10","08:10","09:10","10:10","11:10","12:10","13:10","14:10","15:10","16:10","17:40","19:40");
            
        opprettStoppested(new Stop("Tistedalveien")); //15
            leggTilAvgangerForStopp(r33.id, 15, "05:17","06:17","07:17","08:17","09:17","10:17","11:17","12:17","13:17","14:17","15:17","16:17","17:47","19:47");
        
        opprettStoppested(new Stop("Øbergveien")); //16
            leggTilAvgangerForStopp(r33.id, 16, "05:20","06:20","07:20","08:20","09:20","10:20","11:20","12:20","13:20","14:20","15:20","16:20","17:50","19:50");
        
        opprettStoppested(new Stop("Vold skog")); //17
            leggTilAvgangerForStopp(r33.id, 17, "05:22","06:22","07:22","08:22","09:22","10:22","11:22","12:22","13:22","14:22","15:22","16:22","17:52","19:52");
        
        opprettStoppested(new Stop("Øbergkrysset")); //18
            leggTilAvgangerForStopp(r33.id, 18, "05:26","06:26","07:26","08:26","09:26","10:26","11:26","12:26","13:26","14:26","15:26","16:26","17:56","19:56");
        
        opprettStoppested(new Stop("Risum")); //19    
            leggTilAvgangerForStopp(r33.id, 19, "05:29","06:29","07:29","08:29","09:29","10:29","11:29","12:29","13:29","14:29","15:29","16:29","17:59","19:59"); 
        
        opprettStoppested(new Stop("Kommandantveien Risum")); //20
            leggTilAvgangerForStopp(r33.id, 20, "05:30","06:30","07:30","08:30","09:30","10:30","11:30","12:30","13:30","14:30","15:30","16:30","18:00","20:00");
        
        opprettStoppested(new Stop("Kommandantveien")); //21
            leggTilAvgangerForStopp(r33.id, 21, "05:32","06:32","07:32","08:32","09:32","10:32","11:32","12:32","13:32","14:32","15:32","16:32","18:02","20:02");

        opprettStoppested(new Stop("Halden Bil")); //22
            leggTilAvgangerForStopp(r35.id, 22, "05:44","06:44","07:44","08:44","09:44","10:44","11:44","12:44","13:44","14:44","15:44","16:44","17:44","19:14");

        opprettStoppested(new Stop("Åsveien")); //23
            leggTilAvgangerForStopp(r35.id, 23, "05:44","06:44","07:44","08:44","09:44","10:44","11:44","12:44","13:44","14:44","15:44","16:44","17:44","19:14");

        opprettStoppested(new Stop("Kullveien"));//24
            leggTilAvgangerForStopp(r35.id, 24, "05:45","06:45","07:45","08:45","09:45","10:45","11:45","12:45","13:45","14:45","15:45","16:45","17:45","19:15");

        opprettStoppested(new Stop("Brødløs sør")); //25
            leggTilAvgangerForStopp(r35.id, 25, "05:46","06:46","07:46","08:46","09:46","10:46","11:46","12:46","13:46","14:46","15:46","16:46","17:46","19:16");

        opprettStoppested(new Stop("Kiosken B.R.A. veien")); //26
            leggTilAvgangerForStopp(r35.id, 26, "05:46","06:46","07:46","08:46","09:46","10:46","11:46","12:46","13:46","14:46","15:46","16:46","17:46","19:16");

        opprettStoppested(new Stop("Næridsrød"));//27
            leggTilAvgangerForStopp(r35.id, 27, "05:47","06:47","07:47","08:47","09:47","10:47","11:47","12:47","13:47","14:47","15:47","16:47","17:47","19:17");

        opprettStoppested(new Stop("Blokkveien 2"));//28
            leggTilAvgangerForStopp(r35.id, 28, "05:48","06:48","07:48","08:48","09:48","10:48","11:48","12:48","13:48","14:48","15:48","16:48","17:48","19:18");

        opprettStoppested(new Stop("Blokkveien 18")); //29
            leggTilAvgangerForStopp(r35.id, 29, "05:49","06:49","07:49","08:49","09:49","10:49","11:49","12:49","13:49","14:49","15:49","16:49","17:49","19:19");

        opprettStoppested(new Stop("Blokkveien 28")); //30
            leggTilAvgangerForStopp(r35.id, 30, "05:50","06:50","07:50","08:50","09:50","10:50","11:50","12:50","13:50","14:50","15:50","16:50","17:50","19:20");

        opprettStoppested(new Stop("Atomveien")); //31
            leggTilAvgangerForStopp(r35.id, 31, "05:51","06:51","07:51","08:51","09:51","10:51","11:51","12:51","13:51","14:51","15:51","16:51","17:51","19:21");

        opprettStoppested(new Stop("Bergheim")); //32
            leggTilAvgangerForStopp(r35.id, 32, "05:54","06:54","07:54","08:54","09:54","10:54","11:54","12:54","13:54","14:54","15:54","16:54","17:54","19:24");

        opprettStoppested(new Stop("Veden")); //33
            leggTilAvgangerForStopp(r35.id, 33, "05:57","06:57","07:57","08:57","09:57","10:57","11:57","12:57","13:57","14:57","15:57","16:57","17:57","19:27");

        opprettStoppested(new Stop("Tistedalen")); //34
            leggTilAvgangerForStopp(r35.id, 34, "06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:30");


         }

    private void leggTilAvgangerForStopp(int ruteID, int stoppID, String... tider) {
        Stop stopp = hentStoppested(stoppID);
        if (stopp == null) return;

        Route rute = hentRute(ruteID);
        if (rute == null) return;
        
        rute.addStop(stopp);
        
        
        for (String t : tider) {
            LocalTime lt = LocalTime.parse(t, TIME_FMT);
            Departure avg = new Departure(ruteID, stoppID, lt);
            opprettAvgang(avg);
            stopp.addDeparture(avg);
            departureCache.add(avg); 
        }
    }

    public void saveToDisk() throws Exception{
        database.serialize(this);
    }

    public void loadFromDisk() throws Exception{
        database.deserialize(this);
    }

    // Returnerer id til nylaget objekt 
    public int createUser(User bruker){
        userCache.add(bruker);
        bruker.id = userCache.size()-1;
        return bruker.id ;
    }

    public User getUser(int id){
        return 
        userCache.get(id);
    }

    public ArrayList<User> getUsers(){
        return userCache;
    }

    // Returnerer id til nylaget objekt 
    public int opprettStoppested(Stop stoppested){
        stopCache.add(stoppested);
        stoppested.id = stopCache.size()-1;
        return stoppested.id;
    }

    public Stop hentStoppested(int id){
        return stopCache.get(id);
    }

    public ArrayList<Stop> hentStoppesteder(){
        return stopCache;
    }

    // Returnerer id til nylaget objekt 
    /*public int opprettRute(Rute rute){
        ruteCache.add(rute);
        rute.id = ruteCache.size()-1;
        return rute.id;
    } */
    public int opprettRute(Route rute) {
        routeCache.add(rute);
        return rute.id;
    }

   /*public Rute hentRute(int id){
        return ruteCache.get(id);
    }*/
    public Route hentRute(int id) {
        for (Route r : routeCache) {
            if (r.id == id) {
                return r;
            }
        }
        return null;
    }
    
    public ArrayList<Route> hentRuter(){
        return routeCache;
    }

    // Returnerer id til nylaget objekt 
    public int opprettAvgang(Departure avgang){
        departureCache.add(avgang);
        avgang.id = departureCache.size()-1;
        return avgang.id;
    }

    public ArrayList<Departure> hentAvganger() {
        return departureCache;
    }

}
