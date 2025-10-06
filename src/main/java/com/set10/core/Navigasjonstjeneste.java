package com.set10.core;
import java.util.ArrayList;

public class Navigasjonstjeneste {

    public ArrayList<Stoppested> stoppesteder = new ArrayList<>();
    public ArrayList<Rute> ruter = new ArrayList<>();

    public Navigasjonstjeneste(){

    }
    
    public void visStoppesteder() {
        for (Stoppested s : stoppesteder) {
            System.out.println(s);
        }
    }
}
