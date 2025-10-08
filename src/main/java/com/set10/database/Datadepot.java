package com.set10.database;

import java.util.ArrayList;

import com.set10.core.Bruker;
import com.set10.core.Stoppested;
import com.set10.core.Rute;

/**
 * Holder på data som applikasjonen behøver.
 * Opprettholder 
 */
public class Datadepot {

    // Holder eventuelt på en database
    // public IDatabase database;

    public ArrayList<Bruker> brukerCache = new ArrayList<>();
    public ArrayList<Stoppested> stoppestedCache = new ArrayList<>();
    public ArrayList<Rute> ruteCache = new ArrayList<>();

    // Burde fungere som Init
    public Datadepot(){
    }

    public void lagreTilDisk(){
        
    }

    public void lasteFraDisk(){

    }

    public void opprettBruker(){
    }

    public Bruker hentBruker(){
        return null;
    }

    public ArrayList<Bruker> hentBrukere(){
        return null;
    }

    public void opprettStoppested(){
    }

    public Stoppested hentStoppested(){
        return null;
    }

    public ArrayList<Stoppested> hentStoppesteder(){
        return null;
    }

    public void opprettRute(){
    }

    public Rute hentRute(){
        return null;
    }

    public ArrayList<Rute> hentRuter(){
        return null;
    }

}
