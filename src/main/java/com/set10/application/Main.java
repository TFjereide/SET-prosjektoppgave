package com.set10.application;

import com.set10.core.DataRepository;
import com.set10.database.DatabaseText;

import com.set10.core.NavigationService;
import com.set10.core.Stop;
import com.set10.core.Route;
import com.set10.core.Departure;
import com.set10.core.Ticket;
import com.set10.core.User;
import com.set10.core.PathFinder.NodeGraph;
import com.set10.core.PathFinder.Node;
import com.set10.database.DatabaseText;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.extension.imnodes.ImNodes;
import imgui.extension.imnodes.ImNodesContext;
import imgui.extension.imnodes.ImNodesEditorContext;

public class Main extends Application {

    NavigationService navigationservice;
    DataRepository datarepository;
    private Integer chosenUserID = null;
    private String chosenUserName = null;
    private static ImNodesEditorContext editorContext = null;
    static {
        ImNodes.createContext();
        editorContext = ImNodes.editorContextCreate();
    }

    @Override
    protected void configure(Configuration config) {
        config.setTitle("Østfold trafikk premium");
    }

    // Denne kjøres (forhåpentligvis) 60 ganger i sekundet, og er hvor logikk for gui og lignende legges inn
    @Override
    public void process() {
        ImGui.begin("Debug menu");
        
        if(ImGui.button("save Data")){
            try{datarepository.saveToDisk();}
            catch(Exception e){
                System.err.println("[ERROR] Can't save to disk ->" + e);
            }
        }
        ImGui.sameLine();
        if(ImGui.button("Load Data")){
            try{datarepository.loadFromDisk();}
            catch(Exception e){
                System.err.println("[ERROR] Can't load from disk ->" + e);
            }
        }
        ImGui.sameLine();
        if(ImGui.button("generate dummydata")){
            datarepository.generateDummyData();
        }

        ImGui.sameLine();
        if(ImGui.button("test pathfinding")){
            navigationservice.FindRoute(datarepository.stopCache.get(0), datarepository.stopCache.get(1));
        }

        ImGui.setNextItemWidth(220);
        // Velger bruker
        if (ImGui.beginCombo("##userCombo" , chosenUserName == null ? "Choose User" : chosenUserName)) {

            for (User user : datarepository.getUsers()) {
        
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
        ImGui.text(chosenUserID != null ? "Logged in as: " + chosenUserName : "Not logged in");


        if (chosenUserID != null) {
            ImGui.beginChild("DataView", 500, 500);
            
            ImGui.separator();


            // Ruter
            ImGui.spacing();
            ImGui.spacing();
            ImGui.spacing();
            ImGui.spacing();
            if (ImGui.collapsingHeader("Routes")) {
                ImGui.separator();
                for (Route route : datarepository.hentRuter()) {
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
                for (Stop stop : datarepository.hentStoppesteder()) {
                    if (ImGui.treeNode(stop.toString())) {
                        for (Route route : datarepository.hentRuter()) {
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
            ImGui.endChild();
            ImGui.sameLine();
        }

        ImGui.beginChild("##NodeGraph");
        {
            ImNodes.editorContextSet(editorContext);
            NodeGraph graph = navigationservice.pathFinder.buildNodeGraph(datarepository);
            ImNodes.beginNodeEditor();
            for (Node node: graph.nodes){
                ImNodes.beginNode(node.id);
                ImNodes.beginNodeTitleBar();
                ImGui.text("id:"+node.id);
                ImNodes.endNodeTitleBar();
                ImNodes.endNode();
            }

            ImNodes.endNodeEditor();
        }
        ImGui.endChild();
        ImGui.end();
        // uncomment hvis du vil se mer på hva imgui kan gjøre.
        // ImGui.showDemoWindow();
    }

    // Dette er initialiseringskode, som kjøres før oppstart av programmet.
    @Override
    protected void preRun(){
        datarepository = new DataRepository(new DatabaseText());
    
        navigationservice = new NavigationService(datarepository);
        
    }
    
    // Starter bare applikasjonen. Burde kanskje ikke røres
    public static void main(String[] args) {
        launch(new Main());
    }
}