package com.set10.application;

import com.set10.database.DatabaseText;

import com.set10.core.NavigationService;
import com.set10.core.Stop;
import com.set10.core.Route;
import com.set10.core.Departure;
import com.set10.core.DummyDataRepository;
import com.set10.core.Ticket;
import com.set10.core.Trip;
import com.set10.core.User;
import com.set10.core.UserDataService;
import com.set10.core.DTO.DepartureDTO;
import com.set10.core.DTO.RouteDTO;
import com.set10.core.DTO.StopDTO;
import com.set10.core.DTO.UserDTO;
import com.set10.core.PathFinder.NodeGraph;
import com.set10.core.PathFinder.Node;

import imgui.ImGui;
import imgui.ImVec4;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiCol;
import imgui.type.ImString;

public class DebugView extends Application {

    NavigationService navigationService;
    UserDataService userDataService;
    DummyDataRepository datarepository;
    
    // interaction data
    private Integer selectedUserID = null;
    private String selectedUserName = null;

    private ImString imFromStop = new ImString();
    private String fromStop = "";
    private ImString imToStop = new ImString();
    private String toStop = "";

    private Trip tempTrip = null;

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
        
        
        ImGui.beginChild("DataView", 500, 500);
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
        if(ImGui.button("Generate dummydata")){
            datarepository.generateDummyData();
        }

        ImGui.separatorText("Data");
        // Ruter
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
            if (datarepository.getallTickets().size() == 0){
                ImGui.text("No tickets.");
            }
            else{
                for(Ticket ticket : datarepository.getallTickets()){
                    ImGui.text(ticket.toString());
                }
            }
        }
        
        ImGui.endChild();
        ImGui.sameLine();

        ImGui.beginChild("##UserActions");
        ImGui.setNextItemWidth(220);
        // Velger bruker
        if (ImGui.beginCombo("##userCombo" , selectedUserName == null ? "Select User" : selectedUserName)) {

            for (UserDTO user : userDataService.getUserList(true)) {
        
                String name = user.name();  
                boolean selected = (selectedUserID != null && selectedUserID == user.id());

                if (ImGui.selectable(name, selected)) {
                    selectedUserID = user.id();
                    selectedUserName = name;
                }

                if (selected)
                    ImGui.setItemDefaultFocus();
            }
            ImGui.endCombo();

        }
        ImGui.sameLine();
        ImGui.text(selectedUserID != null ? "Logged in as: " + selectedUserName : "Not logged in");

        if (selectedUserID != null) {
            
            // Finne en reise med to strenger. Kan bare velge mellom stoppene kanskje
            ImGui.separatorText("Trip");
            {
                if(ImGui.inputText("From", imFromStop)){
                    fromStop = imFromStop.toString();
                    tripCallback();
                }
    
                ImGui.sameLine();
                if(navigationService.stopExists(fromStop)){
                    ImGui.textColored(0,255,0,255,"Exists!");
                }else{
                    ImGui.textColored(255,0,0,255,"X");
                }
            }
            {
                if(ImGui.inputText("To", imToStop)){
                    toStop = imToStop.toString();
                    tripCallback();
                }

                ImGui.sameLine();
                if(navigationService.stopExists(toStop)){
                    ImGui.textColored(0,255,0,255,"Exists!");
                }else{
                    ImGui.textColored(255,0,0,255,"X");
                }
            }

            if(tempTrip != null){
                ImGui.textColored(0, 255, 0, 255,"Found trip!");
                
                if(ImGui.button("Activate trip")){
                    userDataService.setUserActiveTrip(tempTrip, selectedUserID);
                }
                

            }else{
                ImGui.textDisabled("No trip");
            }
            Trip userTrip = userDataService.getUserActiveTrip(selectedUserID);
            if (userTrip != null){
                if(ImGui.collapsingHeader("Current trip: Show stops")){
                    int i = 1;
                    for(Stop stop : userTrip.stops){
                        ImGui.bulletText(i + ". " + stop.name);
                        i++;
                    }
                }
            }
            
            // Gi en billett som gjelder sonene for reisen
            ImGui.separatorText("Ticket");
            if(tempTrip != null){
                if(ImGui.button("Give user valid ticket for trip.")){

                }

            }else{
                ImGui.textDisabled("Give user valid ticket for trip.");
            }
            User user = datarepository.getUser(selectedUserID);
            for(Ticket ticket : user.activeTickets){
                ImGui.bullet();
                ImGui.sameLine();
                ImGui.textColored(0,255,0,255, ticket.toString());
            }
            for(Ticket ticket : user.oldTickets){
                ImGui.bullet();
                ImGui.sameLine();
                ImGui.textColored(255,0,0,255, ticket.toString());
            }

        }
        ImGui.endChild();

        
        ImGui.end();
    }

    void tripCallback(){
        tempTrip = navigationService.FindTrip(fromStop, toStop);
    }

    @Override
    protected void preRun(){
        String teststr = "test";
        // ImString test = new ImString("test");   
        // if(test.)
    }
    
    // // Starter bare applikasjonen. Burde kanskje ikke røres
    // public static void run(DebugView debugView) {
    //     launch(debugView);
    // }
}
