package com.set10.application;

import com.set10.core.NavigationService;
import com.set10.core.Stop;
import com.set10.core.Route;
import com.set10.core.Departure;
import com.set10.core.DummyDataRepository;
import com.set10.core.Ticket;
import com.set10.core.Trip;
import com.set10.core.User;
import com.set10.core.UserDataService;
import com.set10.core.DTO.UserDTO;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.type.ImString;


/**
 * As it says on the tin. A debug view/gui for the application using the ImGui library.
 * A way to quickly get an application up and running - disposable and unopinionated on core logic implementation.
 */
public class DebugView extends Application {

    NavigationService navigationService;
    UserDataService userDataService;
    DummyDataRepository datarepository;
    
    // interaction data
    private Integer selectedUserId = null;
    private String selectedUserName = null;

    private String fromStop = "halden bussterminal";
    private ImString imFromStop = new ImString(fromStop);
    private String toStop = "kommandantveien";
    private ImString imToStop = new ImString(toStop);

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

    // Called every rendered frame, runs at aproximately 60hz
    @Override
    public void process() {
        ImGui.begin("Debug menu");
        
        { // Menu for data loading/saving/generating
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
        }

        ImGui.separatorText("Data");
        
        // Overview of Routes in repository
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

        // Overview of Stops in repository
        if (ImGui.collapsingHeader("Stops")) {
            ImGui.separator();
            for (Stop stop : datarepository.getAllStops()) {
                if (ImGui.treeNode(stop.toString() + " - " + stop.routes.size() + " stop(s)")) {
                    for (int routeID : stop.routes) {     
                        Route route = datarepository.getRoute(routeID);
                        if (ImGui.treeNode("Route: " + route.id +" - "+ stop.departures.size() + " departure(s)")){
                            for (Departure departure : stop.departures) {
                                if (departure.routeId == route.id) {
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

        // Overview of Tickets in repository
        if (ImGui.collapsingHeader("Tickets")) {
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

        // Segment dealing with actions similar to what users should be able to take
        // Tickets, finding trips/routes
        ImGui.beginChild("##UserActions");
        ImGui.setNextItemWidth(220);

        // Button for selecting Users to interact with
        if (ImGui.beginCombo("##userCombo" , selectedUserName == null ? "Select User" : selectedUserName)) {

            for (UserDTO user : userDataService.getUserList(true)) {
        
                String name = user.name();  
                boolean selected = (selectedUserId != null && selectedUserId == user.id());

                if (ImGui.selectable(name, selected)) {
                    selectedUserId = user.id();
                    selectedUserName = name;
                }

                if (selected)
                    ImGui.setItemDefaultFocus();
            }
            ImGui.endCombo();

        }
        ImGui.sameLine();
        ImGui.text(selectedUserId != null ? "Logged in as: " + selectedUserName : "Not logged in");

        if (selectedUserId != null) {
            // Find route by typing in stop names.
            // TODO: should probably use fuzzy-search
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
                    userDataService.setUserActiveTrip(tempTrip, selectedUserId);
                }
            }else{
                ImGui.textDisabled("No trip");
            }

            
            Trip userTrip = userDataService.getUserActiveTrip(selectedUserId);
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
            if(userTrip != null){
                if(ImGui.button("Give user valid ticket for trip.")){
                    userDataService.giveUserTicketForTrip(selectedUserId,Ticket.Type.Single, tempTrip);

                }

            }else{
                ImGui.textDisabled("Cannot give user valid ticket. No active trip.");
            }

            User user = datarepository.getUser(selectedUserId);
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
        tripCallback();
    }
    
}
