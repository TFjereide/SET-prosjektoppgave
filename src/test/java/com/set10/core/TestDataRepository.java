package com.set10.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.set10.core.interfaces.IDataRepository;
import com.set10.database.DatabaseText;


/**
 * @implNote in the current implementation testing getters will seem a bit silly,
 * but they will in the future be fetched in another way than the dead simple current one.
 * If, for example, the database is an _actual_ database, the results will need to match.
 * Therefore, in the future, these tests will end up more important.
 */
public class TestDataRepository {

    // Meant to represent the different types of repositories that should be tested.
    // they should all exhibit the same expected behaviour.
    ArrayList<IDataRepository> repositoryTypes;

    @BeforeEach
    public void setUp(){
        repositoryTypes = new ArrayList<>();
        {
            DummyDataRepository dummyDataRepository = new DummyDataRepository(
                new DatabaseText("data/testdata.txt")
                );
        
            dummyDataRepository.generateDummyData();
            repositoryTypes.add(dummyDataRepository);
        }
    }


    @Test
    @DisplayName("get user by id")
    public void testGetuser(){
        for(IDataRepository repo: repositoryTypes){
            assertNotNull(repo.getUser(0));
            assertNotNull(repo.getUser(1));

            assertNull(repo.getUser(Integer.MIN_VALUE));
            assertNull(repo.getUser(Integer.MAX_VALUE));
        }
    }

    @Test
    @DisplayName("get stops")
    public void testGetStop(){
        for(IDataRepository repo: repositoryTypes){
            assertNotNull(repo.getStop(0));
            assertNotNull(repo.getStop(1));

            assertNull(repo.getStop(Integer.MIN_VALUE));
            assertNull(repo.getStop(Integer.MAX_VALUE));
        }
    }

    @Test
    @DisplayName("get stops by name")
    public void testGetStopByName(){
        for(IDataRepository repo: repositoryTypes){
            assertNotNull(repo.getStopByName("Fiskebrygga"),"Can't find stop using case-sensitive names!");
            assertNotNull(repo.getStopByName("halden bussterminal"), "Can't find stop using case-insensitive names!");

            assertNull(repo.getStopByName("New york central station"));
            assertNull(repo.getStopByName(""));
            assertNull(repo.getStopByName(null));
        }
    }

    @Test
    @DisplayName("get routes")
    public void testGetRoute(){
        for(IDataRepository repo: repositoryTypes){
            assertNotNull(repo.getRoute(0));
            assertNotNull(repo.getRoute(1));

            assertNull(repo.getRoute(Integer.MIN_VALUE));
            assertNull(repo.getRoute(Integer.MAX_VALUE));
        }
    }

    @Test
    @DisplayName("get route departures for stop")
    public void testGetDeparture(){
        for(IDataRepository repo: repositoryTypes){
            assertNotNull(repo.getRouteDeparturesForStop(0,0));
            assertNotNull(repo.getRouteDeparturesForStop(1,0));

            assertTrue(repo.getRouteDeparturesForStop(Integer.MIN_VALUE, 0).size() == 0);
            assertTrue(repo.getRouteDeparturesForStop(0, Integer.MIN_VALUE).size() == 0);

            assertTrue(repo.getRouteDeparturesForStop(Integer.MAX_VALUE, 0).size() == 0);
            assertTrue(repo.getRouteDeparturesForStop(0, Integer.MAX_VALUE).size() == 0);
        }
    }

    @Test
    @DisplayName("get ticket")
    public void testGetTicket(){
          for(IDataRepository repo: repositoryTypes){
            assertNotNull(repo.getTicket(0));
            assertNotNull(repo.getTicket(1));

            assertNull(repo.getTicket(Integer.MIN_VALUE));
            assertNull(repo.getTicket(Integer.MAX_VALUE));
        }
    }

}
