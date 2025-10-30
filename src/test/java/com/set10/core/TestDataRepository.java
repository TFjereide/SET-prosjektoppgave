package com.set10.core;

// import com.set10.core.Datadepot;
// import com.set10.core.Stoppested;
import com.set10.database.DatabaseText;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDataRepository {
    
    @Test
    @DisplayName("testSerialiseringOgDeserialisering")
    void testSerialiseringOgDeserialisering(){
        DatabaseText dbt = new DatabaseText("data\\testdata.txt");
        DataRepository depot = new DataRepository(dbt);
        depot.generateDummyData();
        assertDoesNotThrow(depot::saveToDisk);
        assertDoesNotThrow(depot::loadFromDisk);
    }

    @Test
    @DisplayName("serialiseringsintegritet for brukere")
    void testBrukerSerialiseringsintegritet(){
        DatabaseText dbt = new DatabaseText("data\\testdata.txt");
        DataRepository depot = new DataRepository(dbt); 
        DataRepository depot2 = new DataRepository(dbt); 

        depot.generateDummyData();

        try {depot.saveToDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        try {depot2.loadFromDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        assertEquals(depot.userCache.size(), depot2.userCache.size());

        for(int i = 0; i< depot.userCache.size(); i++){
            User b1 = depot.userCache.get(i);
            User b2 = depot2.userCache.get(i);
            assertEquals(b1.id, b2.id);
        }
    }

    @Test
    @DisplayName("serialiseringsintegritet for Billetter")
    void testBillettSerialiseringsintegritet(){
        DatabaseText dbt = new DatabaseText("data\\testdata.txt");
        DataRepository depot = new DataRepository(dbt); 
        DataRepository depot2 = new DataRepository(dbt); 

        depot.generateDummyData();

        try {depot.saveToDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        try {depot2.loadFromDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        assertEquals(depot.ticketCache.size(), depot2.ticketCache.size());
        
        for(int i = 0; i< depot.ticketCache.size(); i++){
            Ticket b1 = depot.ticketCache.get(i);
            Ticket b2 = depot2.ticketCache.get(i);
            assertEquals(b1.id, b2.id);
        }
    }

    @Test
    @DisplayName("serialiseringsintegritet for stoppested")
    void testStoppestedSerialiseringsintegritet(){
        DatabaseText dbt = new DatabaseText("data\\testdata.txt");
        DataRepository depot = new DataRepository(dbt); 
        DataRepository depot2 = new DataRepository(dbt); 

        depot.generateDummyData();

        try {depot.saveToDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        try {depot2.loadFromDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        assertEquals(depot.stopCache.size(), depot2.stopCache.size());

        for(int i = 0; i < depot.stopCache.size(); i++){
            Stop b1 = depot.stopCache.get(i);
            Stop b2 = depot2.stopCache.get(i);
            assertEquals(b1.id, b2.id);
        }

    }

    
    
    @Test
    @DisplayName("serialiseringsintegritet for stoppested")
    void testRuterSerialiseringsintegritet(){
        DatabaseText dbt = new DatabaseText("data\\testdata.txt");
        DataRepository depot = new DataRepository(dbt); 
        DataRepository depot2 = new DataRepository(dbt); 

        depot.generateDummyData();

        try {depot.saveToDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        try {depot2.loadFromDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        assertEquals(depot.routeCache.size(), depot2.routeCache.size());
        for(int i = 0; i< depot.routeCache.size(); i++){
            Route b1 = depot.routeCache.get(i);
            Route b2 = depot2.routeCache.get(i);
            assertEquals(b1.id, b2.id);
        }
    }
    
    @Test
    @DisplayName("serialiseringsintegritet for avganger")
    void testAvgangSerialiseringsintegritet(){
        DatabaseText dbt = new DatabaseText("data\\testdata.txt");
        DataRepository depot = new DataRepository(dbt); 
        DataRepository depot2 = new DataRepository(dbt); 

        depot.generateDummyData();

        try {depot.saveToDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        try {depot2.loadFromDisk();}
        catch(Exception e){
            e.printStackTrace();
        }

        assertEquals(depot.departureCache.size(), depot2.departureCache.size());

        for(int i = 0; i< depot.departureCache.size(); i++){
            Departure b1 = depot.departureCache.get(i);
            Departure b2 = depot2.departureCache.get(i);
            assertEquals(b1.id, b2.id);
        }

    }
    
    
}
