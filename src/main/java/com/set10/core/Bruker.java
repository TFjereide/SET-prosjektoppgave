package com.set10.core;

import java.util.ArrayList;
public class Bruker {
    public int id;
    public String navn;
    public ArrayList<Billett> aktiveBilletter;
    public ArrayList<Billett> gamleBiletter;


    public Bruker(int id, String navn){
        this.id = id;
        this.navn = navn;
        this.aktiveBilletter = new ArrayList<>();
        this.gamleBiletter = new ArrayList<>();
    }

    public Bruker(String navn){
        this.navn = navn;
        this.aktiveBilletter = new ArrayList<>();
        this.gamleBiletter = new ArrayList<>();
    }

    @Override
    public String toString(){
         return "BrukerId: " + id + " Navn: " + navn;

    }
}
