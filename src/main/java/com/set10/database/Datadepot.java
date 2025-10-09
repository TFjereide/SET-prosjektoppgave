package com.set10.database;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalTime;

import com.set10.core.Bruker;
import com.set10.core.Stoppested;
import com.set10.core.Rute;
import com.set10.core.Avgang;

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
    public ArrayList<Avgang> avgangCache = new ArrayList<>();

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    // Burde fungere som Init
    public Datadepot(){
        opprettDummydata();
    }

    public void opprettDummydata(){

        //Brukere
        opprettBruker(new Bruker("Jonas Olsen"));
        opprettBruker(new Bruker("Issac Evinskog"));
        opprettBruker(new Bruker("Erika Hansen"));
        opprettBruker(new Bruker("Olga Bentsdotter"));

        //Stoppesteder
        
        opprettStoppested(new Stoppested(1, "Halden bussterminal"));
        opprettStoppested(new Stoppested(2, "Fiskebrygga"));
        opprettStoppested(new Stoppested(3, "Kulturkvartalet"));
        opprettStoppested(new Stoppested(4, "Parken"));
        opprettStoppested(new Stoppested(5, "Snippen Halden"));
        opprettStoppested(new Stoppested(6, "Østre Lie"));
        opprettStoppested(new Stoppested(7, "Bjørklund"));
        opprettStoppested(new Stoppested(8, "Remmen Høgskolen"));
        opprettStoppested(new Stoppested(9, "Park Hotell"));
        opprettStoppested(new Stoppested(10, "Skofabrikken"));
        opprettStoppested(new Stoppested(11, "Stranda"));
        opprettStoppested(new Stoppested(12, "Bybrua Halden"));
        opprettStoppested(new Stoppested(13, "Borgergata"));
        opprettStoppested(new Stoppested(14, "Halden stasjon"));

        //Ruter
        Rute r34 = new Rute(34);
        opprettRute(r34);

        //Avganger
        leggTilAvgangerForStopp(r34.hentRuteID(), 1,  "05:25","06:25","07:25","08:25","09:25","10:25","11:25","12:25","13:25","14:25","15:25","16:25","17:25","19:25");
        leggTilAvgangerForStopp(r34.hentRuteID(), 2,  "05:26","06:26","07:26","08:26","09:26","10:26","11:26","12:26","13:26","14:26","15:26","16:26","17:26","19:26");
        leggTilAvgangerForStopp(r34.hentRuteID(), 3,  "05:27","06:27","07:27","08:27","09:27","10:27","11:27","12:27","13:27","14:27","15:27","16:27","17:27","19:27");
        leggTilAvgangerForStopp(r34.hentRuteID(), 4,  "05:28","06:28","07:28","08:28","09:28","10:28","11:28","12:28","13:28","14:28","15:28","16:28","17:28","19:28");
        leggTilAvgangerForStopp(r34.hentRuteID(), 5,  "05:29","06:29","07:29","08:29","09:29","10:29","11:29","12:29","13:29","14:29","15:29","16:29","17:29","19:29");
        leggTilAvgangerForStopp(r34.hentRuteID(), 6,  "05:36","06:36","07:36","08:36","09:36","10:36","11:36","12:36","13:36","14:36","15:36","16:36","17:36","19:36");
        leggTilAvgangerForStopp(r34.hentRuteID(), 7,  "05:39","06:39","07:39","08:39","09:39","10:39","11:39","12:39","13:39","14:39","15:39","16:39","17:39","19:39");
        leggTilAvgangerForStopp(r34.hentRuteID(), 8,  "05:42","06:42","07:42","08:42","09:42","10:42","11:42","12:42","13:42","14:42","15:42","16:42","17:42","19:42");
        leggTilAvgangerForStopp(r34.hentRuteID(), 9,  "05:45","06:45","07:45","08:45","09:45","10:45","11:45","12:45","13:45","14:45","15:45","16:45","17:45","19:45");
        leggTilAvgangerForStopp(r34.hentRuteID(), 10, "05:48","06:48","07:48","08:48","09:48","10:48","11:48","12:48","13:48","14:48","15:48","16:48","17:48","19:48");
        leggTilAvgangerForStopp(r34.hentRuteID(), 11, "05:50","06:50","07:50","08:50","09:50","10:50","11:50","12:50","13:50","14:50","15:50","16:50","17:50","19:50");
        leggTilAvgangerForStopp(r34.hentRuteID(), 12, "05:51","06:51","07:51","08:51","09:51","10:51","11:51","12:51","13:51","14:51","15:51","16:51","17:51","19:51");
        leggTilAvgangerForStopp(r34.hentRuteID(), 13, "05:52","06:52","07:52","08:52","09:52","10:52","11:52","12:52","13:52","14:52","15:52","16:52","17:52","19:52");
        leggTilAvgangerForStopp(r34.hentRuteID(), 14, "05:53","06:53","07:53","08:53","09:53","10:53","11:53","12:53","13:53","14:53","15:53","16:53","17:53","19:53");
    



    }

    private void leggTilAvgangerForStopp(int ruteID, int stoppID, String... tider) {
        Stoppested s = hentStoppested(stoppID);
        if (s == null) return;

        for (String t : tider) {
            LocalTime lt = LocalTime.parse(t, TIME_FMT);
            s.leggTilAvgang(ruteID, lt);
            Avgang a = s.leggTilAvgang(ruteID, lt); // legg på stoppested
            avgangCache.add(a); 
    }
}
    public void lagreTilDisk(){
        
    }

    public void lasteFraDisk(){

    }

    public void opprettBruker(Bruker b){
        brukerCache.add(b);
    }

    public Bruker hentBruker(int brukerID){
        for (Bruker b : brukerCache) {
            if (b.brukerId == brukerID) 
                return b;
        }
        return null;
    }

    public ArrayList<Bruker> hentBrukere(){
        return brukerCache;
    }

    public void opprettStoppested(Stoppested s){
        stoppestedCache.add(s);
    }

    public Stoppested hentStoppested(int stoppID){
        for (Stoppested s : stoppestedCache) {
            if (s.stoppID == stoppID) 
                return s;
        }
        return null;
    }

    public ArrayList<Stoppested> hentStoppesteder(){
        return stoppestedCache;
    }

    public void opprettRute(Rute r){
        ruteCache.add(r);
    }

    public Rute hentRute(int ruteID){
        for (Rute r : ruteCache){
            if(r.ruteID == ruteID)
            return r;
        }
        return null;
    }

    public ArrayList<Rute> hentRuter(){
        return ruteCache;
    }

    public void opprettAvganger(Avgang a){
        avgangCache.add(a);
    }


    public ArrayList<Avgang> hentAvganger() {
        return avgangCache;
    }


}
