package com.set10.core;
import java.util.ArrayList;

public class NavigationContainer {

    public ArrayList<Stoppested> stoppesteder = new ArrayList<>();
    public ArrayList<Rute> ruter = new ArrayList<>();

    public NavigationContainer(){

    }

    public void visStoppesteder() {
        for (Stoppested s : stoppesteder) {
            System.out.println(s);
        }
    }
}
