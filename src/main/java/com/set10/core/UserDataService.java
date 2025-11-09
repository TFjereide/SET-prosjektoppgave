package com.set10.core;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.set10.core.DTO.UserDTO;

public class UserDataService {
    private DataRepository dataRepository;
    
    
    public UserDataService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public ArrayList<UserDTO> getUserList(boolean withIds){
        ArrayList<UserDTO> users = new ArrayList<>();
        
        if (withIds){
            for(User user: dataRepository.getUsers()){
                users.add(new UserDTO(user));
            }
        }else{
            for(User user: dataRepository.getUsers()){
                users.add(new UserDTO(-1, user.name));
            }
        }
        return users;
    }

}
