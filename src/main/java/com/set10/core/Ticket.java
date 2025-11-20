package com.set10.core;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Ticket {  

    public enum Type{
        Single,
        Period
    }

    public int id;
    public Type type;
    
    public LocalDateTime validFrom;
    public LocalDateTime validTo;
    public List<Integer> validForZones = new ArrayList<>(); 


    public Ticket(Type type, LocalDateTime validFrom) {
        this.type = type;
        this.validFrom = validFrom;

        switch(type){
            case Single:
                this.validTo = validFrom.plusMinutes(90);
                break;
            case Period:
                this.validTo = validFrom.plusDays(30);
                break;
            default:
                this.validTo = validFrom;
        }

    }

    public void addZone(int zone) {
        if (!validForZones.contains(zone)) {
            validForZones.add(zone);
        }
    }

    @Override
    public boolean equals(Object object){
        if (object == this){return true;}

        if(!(object instanceof Ticket)){return false;}
        
        Ticket other = (Ticket) object;

        if( other.id != id){return false;}

        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return  "id:" + id +
                " type: " + type +
                " valid From: " + validFrom.format(formatter) +
                " valid To: " + validTo.format(formatter) +
                " zones: " + validForZones.toString();
    }
}
