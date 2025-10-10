package com.set10.application;
import java.util.ArrayList;

import com.set10.core.Datadepot;
import com.set10.core.Navigasjonstjeneste;
import com.set10.core.Rute;
import com.set10.core.Stoppested;
import com.set10.database.DatabaseText;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

public class Main extends Application {

    Datadepot datadepot;

    @Override
    protected void configure(Configuration config) {
        config.setTitle("Østfold trafikk premium");
    }

    // Denne kjøres (forhåpentligvis) 60 ganger i sekundet, og er hvor logikk for gui og lignende legges inn
    @Override
    public void process() {
        ImGui.begin("Debug meny");
    
        // Kan lage enkle listefunksjoner Relativt enkelt
        if(ImGui.collapsingHeader("Stoppesteder")){
            ImGui.separator();
            for (Stoppested stoppested : datadepot.stoppestedCache) {
                ImGui.text(stoppested.toString());   
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
        
        // for(int i = 0; i < 5; i+=1){
        //     datadepot.opprettStoppested(
        //         new Stoppested(i, "tilfeldig addresse")
        //     );
        // }
        // Rute rute = new Rute(0, datadepot.stoppestedCache);
        // datadepot.opprettRute(rute);
        
        // datadepot.lagreTilDisk();
        datadepot.lasteFraDisk();
    }
    
    // Starter bare applikasjonen. Burde kanskje ikke røres
    public static void main(String[] args) {
        launch(new Main());
    }
}