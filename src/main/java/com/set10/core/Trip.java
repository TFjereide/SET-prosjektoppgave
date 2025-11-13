package com.set10.core;
import java.util.ArrayList;
import java.util.HashSet;


// For øyeblikket skal ikke en reise lagres, ihvertfall i denne formen.
public class Trip {
    public ArrayList<Stop> stops = new ArrayList<>();
    public float totalTime = 0;
    public HashSet<Integer> zones = new HashSet<Integer>();
}
