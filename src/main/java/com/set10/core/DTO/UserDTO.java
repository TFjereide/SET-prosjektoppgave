package com.set10.core.DTO;

import com.set10.core.User;

public record UserDTO(int id, String name) {

    public UserDTO(User user){
        this(user.id, user.name);
    }
}
