package com.set10.core;

import java.util.ArrayList;
public class Bruker {
    public int brukerId;
    public String navn;
    public ArrayList<Billett> aktiveBilletter;
    public ArrayList<Billett> gamleBiletter;

    private static int nesteLedigId = 1;

    public Bruker(String navn){
        this.brukerId = nesteLedigId++;
        this.navn = navn;
        this.aktiveBilletter = new ArrayList<>();
        this.gamleBiletter = new ArrayList<>();

    }

    @Override
    public String toString(){
         return "BrukerId: " + brukerId + " Navn: " + navn;

    }
}
