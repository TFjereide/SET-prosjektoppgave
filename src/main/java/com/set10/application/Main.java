package com.set10.application;

import com.set10.core.DataRepository;
import com.set10.core.NavigationService;
import com.set10.core.UserDataService;
import com.set10.database.DatabaseText;

import imgui.app.Application;

public class Main {

    public static void main(String[] args) {    
        System.out.println("Starting application");    
        DataRepository datarepository = new DataRepository(new DatabaseText());
        try{
            datarepository.loadFromDisk();
        }catch(Exception e){
            e.printStackTrace();
            datarepository.generateDummyData();
        }
        NavigationService navigationService = new NavigationService(datarepository);
        UserDataService userDataService = new UserDataService(datarepository);

        // Application her, fra ImGui, starter en native applikasjon og vindu, med debug
        // gui og lignende funksjonalitet. Ikke nødvendig for at programmet fungerer, men 
        // for å kunne visualisere hvordan ting fungerer.
        Application.launch(new DebugView(navigationService, userDataService, datarepository));
        
    }
}