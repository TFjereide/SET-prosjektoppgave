package com.set10.application;


import com.set10.core.Datadepot;

import com.set10.database.DatabaseText;


import com.set10.core.Navigasjonstjeneste;
import com.set10.core.Stoppested;
import com.set10.core.Rute;
import com.set10.core.Avgang;
import com.set10.database.DatabaseText;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

public class Main extends Application {

    Navigasjonstjeneste navigasjonstjeneste;
    Datadepot datadepot;

    @Override
    protected void configure(Configuration config) {
        config.setTitle("Østfold trafikk premium");
    }

    // Denne kjøres (forhåpentligvis) 60 ganger i sekundet, og er hvor logikk for gui og lignende legges inn
    @Override
    public void process() {
        ImGui.begin("Debug meny");
    
        // Denne skal vise faktiske stoppesteder med avganger
        if(ImGui.collapsingHeader("Stoppesteder")){
            ImGui.separator();
            for (Stoppested stoppested : datadepot.hentStoppesteder()){
                if(ImGui.treeNode(stoppested.toString())){
                    for (Avgang a : stoppested.hentAvganger()){
                        ImGui.text(a.toString());
                    }
                    ImGui.treePop();
                }
                ImGui.separator();
            }
        }
        if(ImGui.collapsingHeader("Ruter")){
            ImGui.separator();
            for (Rute rute : datadepot.ruteCache) {
                if(ImGui.treeNode(rute.toString())){
                    for (Stoppested stopp : rute.stopp){
                        ImGui.text(stopp.toString());
                    }
                    ImGui.treePop();
                }   
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
        
        datadepot = new Datadepot(new DatabaseText());
        datadepot.opprettDummydata();
        
        // try{datadepot.lagreTilDisk();}
        // catch(Exception e){
        //     System.err.println("[ERROR] Kan ikke lagre til fil ->" + e);
        // }

        try{datadepot.lasteFraDisk();}
        catch(Exception e){
            System.err.println("[ERROR] Kan ikke laste inn fra fil ->" + e);
        }
    }
    
    // Starter bare applikasjonen. Burde kanskje ikke røres
    public static void main(String[] args) {
        launch(new Main());
    }
}