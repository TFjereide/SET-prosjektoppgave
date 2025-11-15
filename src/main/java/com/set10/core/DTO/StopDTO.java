package com.set10.core.DTO;

import com.set10.core.Stop;

public record StopDTO(int id, String name) {

    public StopDTO(Stop stop){
        this(stop.id, stop.name);
    }

}
