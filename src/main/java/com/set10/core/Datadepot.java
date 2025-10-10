package com.set10.core;

import java.util.ArrayList;

/**
 * Holder på data som applikasjonen behøver.
 * Opprettholder 
 */
public class Datadepot {

    // Holder eventuelt på en database
    public IDatabase database;

    public ArrayList<Bruker> brukerCache = new ArrayList<>();
    public ArrayList<Stoppested> stoppestedCache = new ArrayList<>();
    public ArrayList<Rute> ruteCache = new ArrayList<>();

    // Burde fungere som Init
    public Datadepot(IDatabase database){
        this.database = database;
    }

    public void lagreTilDisk(){
        try {
            database.serialiser(this);
        }
        catch(Exception e){
            System.err.println("Kan ikke skrive til fil..");
        }
    }

    public void lasteFraDisk(){
        try{
            database.deserialiser(this);
        }
        catch(Exception e){
            System.err.println("Kan ikke laste fra fil..");
        }
    }

    public int opprettBruker(Bruker bruker){
        brukerCache.add(bruker);
        return brukerCache.size()-1;
    }

    public Bruker hentBruker(int id){
        return brukerCache.get(id);
    }

    public int opprettStoppested(Stoppested stoppested){
        stoppestedCache.add(stoppested);
        return stoppestedCache.size()-1;
    }

    public Stoppested hentStoppested(int id){
        return stoppestedCache.get(id);
    }

    // Returnerer id til nylagret objekt 
    public int opprettRute(Rute rute){
        ruteCache.add(rute);
        return ruteCache.size()-1;
    }

    public Rute hentRute(int id){
        return ruteCache.get(id);
    }

}
