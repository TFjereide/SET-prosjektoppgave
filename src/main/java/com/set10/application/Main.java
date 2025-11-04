package com.set10.application;

import com.set10.core.DataRepository;
import com.set10.database.DatabaseText;

import com.set10.core.NavigationService;
import com.set10.core.Stop;
import com.set10.core.Route;
import com.set10.core.Departure;
import com.set10.core.Ticket;
import com.set10.core.User;
import com.set10.database.DatabaseText;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

public class Main extends Application {

    NavigationService navigationservice;
    DataRepository datadepot;
    private Integer chosenUserID = null;
    private String chosenUserName = null;

    @Override
    protected void configure(Configuration config) {
        config.setTitle("Østfold trafikk premium");
    }

    // Denne kjøres (forhåpentligvis) 60 ganger i sekundet, og er hvor logikk for gui og lignende legges inn
    @Override
    public void process() {
        ImGui.begin("Debug menu");
        
        if(ImGui.button("save Data")){
            try{datadepot.saveToDisk();}
            catch(Exception e){
                System.err.println("[ERROR] Can't save to disk ->" + e);
            }
        }
        ImGui.sameLine();
        if(ImGui.button("Load Data")){
            try{datadepot.loadFromDisk();}
            catch(Exception e){
                System.err.println("[ERROR] Can't load from disk ->" + e);
            }
        }
        ImGui.sameLine();
        if(ImGui.button("generate dummydata")){
            datadepot.generateDummyData();
        }

        ImGui.setNextItemWidth(220);
        // Velger bruker
        if (ImGui.beginCombo("##userCombo" , chosenUserName == null ? "Choose User" : chosenUserName)) {

            for (User user : datadepot.getUsers()) {
        
                String name = user.getDisplayName();  
                boolean selected = (chosenUserID != null && chosenUserID == user.id);

                if (ImGui.selectable(name, selected)) {
                    chosenUserID = user.id;
                    chosenUserName = name;
                }

                if (selected)
                    ImGui.setItemDefaultFocus();
            }
            ImGui.endCombo();

        }
        ImGui.sameLine();


        if (chosenUserID != null) {
            ImGui.text("Logged in as: " + chosenUserName);
            ImGui.separator();


            // Ruter
            ImGui.spacing();
            ImGui.spacing();
            ImGui.spacing();
            ImGui.spacing();
            if (ImGui.collapsingHeader("Routes")) {
                ImGui.separator();
                for (Route route : datadepot.hentRuter()) {
                    if (ImGui.treeNode(route.toString())) {
                        for (int i = 0; i < route.stops.size(); i++) {
                            Stop stop = route.stops.get(i);
                            ImGui.text("idx: "+i+ " " + stop.toString());
                        }
                        ImGui.treePop();
                    }
                    ImGui.separator();
                }
            }

            // Stoppesteder
            if (ImGui.collapsingHeader("Stops")) {
                ImGui.separator();
                for (Stop stop : datadepot.hentStoppesteder()) {
                    if (ImGui.treeNode(stop.toString())) {
                        for (Route route : datadepot.hentRuter()) {
                            if (ImGui.treeNode(route.toString())) {
                                for (Departure departure : stop.hentAvganger()) {
                                    if (departure.routeID == route.id) {
                                        ImGui.text(departure.toString());
                                    }
                                }
                                ImGui.treePop();
                            }
                        }
                        ImGui.treePop();
                    }
                    ImGui.separator();
                }
            }
            // Billetter
            if (ImGui.collapsingHeader("Billetter")) {
                ImGui.separator();
            }
        }

        ImGui.end();

        // uncomment hvis du vil se mer på hva imgui kan gjøre.
        // ImGui.showDemoWindow();
    }

    // Dette er initialiseringskode, som kjøres før oppstart av programmet.
    @Override
    protected void preRun(){
        datadepot = new DataRepository(new DatabaseText());
        try{datadepot.loadFromDisk();}
            catch(Exception e){
                System.err.println("[ERROR] Can't load from disk ->" + e);
            }
        navigationservice = new NavigationService(datadepot);
        navigationservice.FindRoute(datadepot.stopCache.get(0), datadepot.stopCache.get(1));
    }
    
    // Starter bare applikasjonen. Burde kanskje ikke røres
    public static void main(String[] args) {
        launch(new Main());
    }
}