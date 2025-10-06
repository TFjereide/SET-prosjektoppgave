package com.set10.application;
import java.util.ArrayList;

import com.set10.core.Navigasjonstjeneste;
import com.set10.core.Stoppested;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

public class Main extends Application {

    // Slettes senere. Ikke bruk denne noesteds
    private ArrayList<Stoppested> testStoppesteder = new ArrayList<>();

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
            for (Stoppested stoppested : testStoppesteder) {
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
        
        for(int i = 0; i < 5; i+=1){
            testStoppesteder.add(
                new Stoppested(i, "tilfeldig addresse")
            );
        }
    }
    
    // Starter bare applikasjonen. Burde kanskje ikke røres
    public static void main(String[] args) {
        launch(new Main());
    }
}