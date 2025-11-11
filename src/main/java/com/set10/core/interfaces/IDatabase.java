package com.set10.core.interfaces;

import java.util.ArrayList;

import com.set10.core.Departure;
import com.set10.core.Route;
import com.set10.core.Stop;
import com.set10.core.Ticket;
import com.set10.core.User;
import com.set10.database.RepositoryDataCache;

// Det er nok ikke helt slik en database fungerer, men dette er en midlertidig løsning
// som ikke føles __helt__ idiotisk.

public interface IDatabase {
    
    public void dumpDataCache(RepositoryDataCache dataCache) throws Exception;
    public RepositoryDataCache requestCacheData() throws Exception;

}
