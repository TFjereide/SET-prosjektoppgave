package com.set10.core;

import java.util.ArrayList;

/**
 * Holder på data som applikasjonen behøver.
 * Opprettholder 
 */
public class Datadepot {

    // Holder eventuelt på en database
    public IDatabase database;

    public ArrayList<Bruker> brukerCache = new ArrayList<>(); // ikke lagret for øyeblikket
    public ArrayList<Billett> billettCache = new ArrayList<>(); // ikke lagret for øyeblikket

    public ArrayList<Stoppested> stoppestedCache = new ArrayList<>();
    public ArrayList<Rute> ruteCache = new ArrayList<>();

    // Burde fungere som Init
    public Datadepot(IDatabase database){
        this.database = database;
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
        return brukerCache.size()-1;
    }

    public Bruker hentBruker(int id){
        return brukerCache.get(id);
    }

    // Returnerer id til nylaget objekt 
    public int opprettStoppested(Stoppested stoppested){
        stoppestedCache.add(stoppested);
        return stoppestedCache.size()-1;
    }

    public Stoppested hentStoppested(int id){
        return stoppestedCache.get(id);
    }

    // Returnerer id til nylaget objekt 
    public int opprettRute(Rute rute){
        ruteCache.add(rute);
        return ruteCache.size()-1;
    }

    public Rute hentRute(int id){
        return ruteCache.get(id);
    }

}
