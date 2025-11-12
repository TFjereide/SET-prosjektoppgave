package com.set10.core.DTO;

import com.set10.core.Route;

public record RouteDTO(int id) {
    public RouteDTO(Route route){
        this(route.id);
    }
}
