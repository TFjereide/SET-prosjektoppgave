package com.set10.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
public class Bruker {
    public int brukerId;
    public String navn;
    public ArrayList<Billett> aktiveBilletter;
    public ArrayList<Billett> gamleBilletter;

    private static int nesteLedigId = 1;

    public Bruker(String navn){
        this.brukerId = nesteLedigId++;
        this.navn = navn;
        this.aktiveBilletter = new ArrayList<>();
        this.gamleBilletter = new ArrayList<>();

    }
    public void kjopBillett(String type, LocalDateTime startTid) {
    Billett nyBillett = new Billett(type, startTid);
    aktiveBilletter.add(nyBillett);
    System.out.println(navn + " kjøpte en " + type + " billett.");}

    public void kjopBillettTilAnnenBruker(String type, LocalDateTime startTid, Bruker mottaker) {
    Billett nyBillett = new Billett(type, startTid);
    mottaker.aktiveBilletter.add(nyBillett);
    System.out.println(navn + " kjøpte " + type + "billett til " + mottaker.navn);}

    @Override
    public String toString(){
         return "BrukerId: " + brukerId + " Navn: " + navn;

    }
}
