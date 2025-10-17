package com.set10.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Holder på data som applikasjonen behøver.
 * Opprettholder 
 */
public class Datadepot {

    public IDatabase database;

    public ArrayList<Bruker> brukerCache = new ArrayList<>(); 
    public ArrayList<Billett> billettCache = new ArrayList<>(); 

    public ArrayList<Stoppested> stoppestedCache = new ArrayList<>();
    public ArrayList<Rute> ruteCache = new ArrayList<>();
    public ArrayList<Avgang> avgangCache = new ArrayList<>();

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    // Burde fungere som Init
    public Datadepot(IDatabase database){
        this.database = database;
    }

    public void opprettDummydata(){

        //Brukere
        opprettBruker(new Bruker("Jonas Olsen"));
        opprettBruker(new Bruker("Issac Evinskog"));
        opprettBruker(new Bruker("Erika Hansen"));
        opprettBruker(new Bruker("Olga Bentsdotter"));

        //Stoppesteder
        
        opprettStoppested(new Stoppested("Halden bussterminal"));
        opprettStoppested(new Stoppested("Fiskebrygga"));
        opprettStoppested(new Stoppested("Kulturkvartalet"));
        opprettStoppested(new Stoppested("Parken"));
        opprettStoppested(new Stoppested("Snippen Halden"));
        opprettStoppested(new Stoppested("Østre Lie"));
        opprettStoppested(new Stoppested("Bjørklund"));
        opprettStoppested(new Stoppested("Remmen Høgskolen"));
        opprettStoppested(new Stoppested("Park Hotell"));
        opprettStoppested(new Stoppested("Skofabrikken"));
        opprettStoppested(new Stoppested("Stranda"));
        opprettStoppested(new Stoppested("Bybrua Halden"));
        opprettStoppested(new Stoppested("Borgergata"));
        opprettStoppested(new Stoppested("Halden stasjon"));

        //Ruter
        Rute r34 = new Rute();
        opprettRute(r34);

        //Avganger
        leggTilAvgangerForStopp(r34.id, 0,  "05:25","06:25","07:25","08:25","09:25","10:25","11:25","12:25","13:25","14:25","15:25","16:25","17:25","19:25");
        leggTilAvgangerForStopp(r34.id, 1,  "05:26","06:26","07:26","08:26","09:26","10:26","11:26","12:26","13:26","14:26","15:26","16:26","17:26","19:26");
        leggTilAvgangerForStopp(r34.id, 2,  "05:27","06:27","07:27","08:27","09:27","10:27","11:27","12:27","13:27","14:27","15:27","16:27","17:27","19:27");
        leggTilAvgangerForStopp(r34.id, 3,  "05:28","06:28","07:28","08:28","09:28","10:28","11:28","12:28","13:28","14:28","15:28","16:28","17:28","19:28");
        leggTilAvgangerForStopp(r34.id, 4,  "05:29","06:29","07:29","08:29","09:29","10:29","11:29","12:29","13:29","14:29","15:29","16:29","17:29","19:29");
        leggTilAvgangerForStopp(r34.id, 5,  "05:36","06:36","07:36","08:36","09:36","10:36","11:36","12:36","13:36","14:36","15:36","16:36","17:36","19:36");
        leggTilAvgangerForStopp(r34.id, 6,  "05:39","06:39","07:39","08:39","09:39","10:39","11:39","12:39","13:39","14:39","15:39","16:39","17:39","19:39");
        leggTilAvgangerForStopp(r34.id, 7,  "05:42","06:42","07:42","08:42","09:42","10:42","11:42","12:42","13:42","14:42","15:42","16:42","17:42","19:42");
        leggTilAvgangerForStopp(r34.id, 8,  "05:45","06:45","07:45","08:45","09:45","10:45","11:45","12:45","13:45","14:45","15:45","16:45","17:45","19:45");
        leggTilAvgangerForStopp(r34.id, 9,  "05:48","06:48","07:48","08:48","09:48","10:48","11:48","12:48","13:48","14:48","15:48","16:48","17:48","19:48");
        leggTilAvgangerForStopp(r34.id, 10, "05:50","06:50","07:50","08:50","09:50","10:50","11:50","12:50","13:50","14:50","15:50","16:50","17:50","19:50");
        leggTilAvgangerForStopp(r34.id, 11, "05:51","06:51","07:51","08:51","09:51","10:51","11:51","12:51","13:51","14:51","15:51","16:51","17:51","19:51");
        leggTilAvgangerForStopp(r34.id, 12, "05:52","06:52","07:52","08:52","09:52","10:52","11:52","12:52","13:52","14:52","15:52","16:52","17:52","19:52");
        leggTilAvgangerForStopp(r34.id, 13, "05:53","06:53","07:53","08:53","09:53","10:53","11:53","12:53","13:53","14:53","15:53","16:53","17:53","19:53");
    
    }

    private void leggTilAvgangerForStopp(int ruteID, int stoppID, String... tider) {
        Stoppested stopp = hentStoppested(stoppID);
        if (stopp == null) return;

        Rute rute = hentRute(ruteID);
        if (rute == null) return;
        
        rute.leggTilStopp(stopp);
        
        for (String t : tider) {
            LocalTime lt = LocalTime.parse(t, TIME_FMT);
            Avgang avg = new Avgang(ruteID, stoppID, lt);
            opprettAvgang(avg);
            stopp.leggTilAvgang(avg);
            avgangCache.add(avg); 
        }
    }

    public void lagreTilDisk() throws Exception{
        database.serialiser(this);
    }

    public void lasteFraDisk() throws Exception{
        database.deserialiser(this);
    }

    // Returnerer id til nylaget objekt 
    public int opprettBruker(Bruker bruker){
        brukerCache.add(bruker);
        bruker.id = brukerCache.size()-1;
        return bruker.id ;
    }

    public Bruker hentBruker(int id){
        return 
        brukerCache.get(id);
    }

    public ArrayList<Bruker> hentBrukere(){
        return brukerCache;
    }

    // Returnerer id til nylaget objekt 
    public int opprettStoppested(Stoppested stoppested){
        stoppestedCache.add(stoppested);
        stoppested.id = stoppestedCache.size()-1;
        return stoppested.id;
    }

    public Stoppested hentStoppested(int id){
        return stoppestedCache.get(id);
    }

    public ArrayList<Stoppested> hentStoppesteder(){
        return stoppestedCache;
    }

    // Returnerer id til nylaget objekt 
    public int opprettRute(Rute rute){
        ruteCache.add(rute);
        rute.id = ruteCache.size()-1;
        return rute.id;
    }

    public Rute hentRute(int id){
        return ruteCache.get(id);
    }
    
    public ArrayList<Rute> hentRuter(){
        return ruteCache;
    }

    // Returnerer id til nylaget objekt 
    public int opprettAvgang(Avgang avgang){
        avgangCache.add(avgang);
        avgang.id = avgangCache.size()-1;
        return avgang.id;
    }

    public ArrayList<Avgang> hentAvganger() {
        return avgangCache;
    }

}
