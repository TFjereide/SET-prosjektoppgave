package com.set10.application;

import com.set10.database.DatabaseText;

import com.set10.core.NavigationService;
import com.set10.core.Stop;
import com.set10.core.Route;
import com.set10.core.Departure;
import com.set10.core.DummyDataRepository;
import com.set10.core.Ticket;
import com.set10.core.User;
import com.set10.core.UserDataService;
import com.set10.core.DTO.DepartureDTO;
import com.set10.core.DTO.RouteDTO;
import com.set10.core.DTO.StopDTO;
import com.set10.core.DTO.UserDTO;
import com.set10.core.PathFinder.NodeGraph;
import com.set10.core.PathFinder.Node;
import com.set10.database.DatabaseText;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.extension.imnodes.ImNodes;
import imgui.extension.imnodes.ImNodesContext;
import imgui.extension.imnodes.ImNodesEditorContext;

public class DebugView extends Application {

    NavigationService navigationService;
    UserDataService userDataService;
    DummyDataRepository datarepository;
    private Integer chosenUserID = null;
    private String chosenUserName = null;

    private static ImNodesEditorContext editorContext = null;
    static {
        ImNodes.createContext();
        editorContext = ImNodes.editorContextCreate();
    }

    public DebugView(NavigationService navigationService, UserDataService userDataService, DummyDataRepository datarepository){
        this.navigationService = navigationService;
        this.userDataService = userDataService;
        this.datarepository = datarepository;
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
            //navigationService.FindRoute(datarepository.stopCache.get(0), datarepository.stopCache.get(1));
        }

        ImGui.setNextItemWidth(220);
        // Velger bruker
        if (ImGui.beginCombo("##userCombo" , chosenUserName == null ? "Choose User" : chosenUserName)) {

            for (UserDTO user : userDataService.getUserList(true)) {
        
                String name = user.name();  
                boolean selected = (chosenUserID != null && chosenUserID == user.id());

                if (ImGui.selectable(name, selected)) {
                    chosenUserID = user.id();
                    chosenUserName = name;
                }

                if (selected)
                    ImGui.setItemDefaultFocus();
            }
            ImGui.endCombo();

        }
        ImGui.sameLine();
        ImGui.text(chosenUserID != null ? "Logged in as: " + chosenUserName : "Not logged in");


        ImGui.beginChild("DataView", 500, 500);
        if (chosenUserID != null) {
            
            ImGui.separator();


            // Ruter
            ImGui.spacing();
            ImGui.spacing();
            ImGui.spacing();
            ImGui.spacing();
            if (ImGui.collapsingHeader("Routes")) {
                ImGui.separator();
                for (Route route : datarepository.getAllRoutes()) {
                    if (ImGui.treeNode(route.toString() + " - " + route.stops.size() + " stop(s)")) {
                        for (int i = 0; i < route.stops.size(); i++) {
                            Stop stop = route.stops.get(i);
                            String idText = "#"+ i ;
                            ImGui.text(idText);
                            ImGui.sameLine(Math.max(ImGui.calcTextSizeX(idText),55.0f));
                            ImGui.text(stop.toString());
                        }
                        ImGui.treePop();
                    }
                    ImGui.separator();
                }
            }

            // Stoppesteder
            if (ImGui.collapsingHeader("Stops")) {
                ImGui.separator();
                for (Stop stop : datarepository.getAllStops()) {
                    if (ImGui.treeNode(stop.toString() + " - " + stop.routes.size() + " stop(s)")) {
                        for (int routeID : stop.routes) {     
                            Route route = datarepository.getRoute(routeID);
                            if (ImGui.treeNode("Route: " + route.id +" - "+ stop.departures.size() + " departure(s)")){
                                for (Departure departure : stop.departures) {
                                    if (departure.routeID == route.id) {
                                        ImGui.text("Departure: " + departure.time);
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
        ImGui.endChild();
        ImGui.sameLine();

        ImGui.beginChild("##NodeGraph");
        {
            ImNodes.editorContextSet(editorContext);
            ImNodes.beginNodeEditor();
            for (Node node: navigationService.nodeGraph.nodes){
                ImNodes.beginNode(node.stop.id);
                // ImNodes.beginNodeTitleBar();
                ImGui.text("id:" + node.stop.id + " name: " + node.stop.name);
                // ImNodes.endNodeTitleBar();
                ImNodes.endNode();
            }

            ImNodes.endNodeEditor();
        }
        ImGui.endChild();
        ImGui.end();
        // uncomment hvis du vil se mer på hva imgui kan gjøre.
        // ImGui.showDemoWindow();
    }

    @Override
    protected void preRun(){
        
    }
    
    // // Starter bare applikasjonen. Burde kanskje ikke røres
    // public static void run(DebugView debugView) {
    //     launch(debugView);
    // }
}
