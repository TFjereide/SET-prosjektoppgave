package com.set10.application;
import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

public class Main extends Application {

    @Override
    protected void configure(Configuration config) {
        config.setTitle("Østfold trafikk premium");
    }

    @Override
    public void process() {
        ImGui.begin("Debug meny");
        ImGui.text("Hello world!");
        if (ImGui.button("Click!")){
            System.out.println("Clicked!");
        }
        ImGui.end();

        // uncomment hvis du vil se mer på hva imgui kan gjøre.
        // ImGui.showDemoWindow();
    }
    @Override
    protected void preRun(){


    }

    public static void main(String[] args) {
        launch(new Main());
    }
}