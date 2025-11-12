package com.set10.core;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.set10.core.DTO.UserDTO;
import com.set10.core.interfaces.IDataRepository;

public class UserDataService {
    private IDataRepository dataRepository;
    
    
    public UserDataService(IDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public boolean setUserActiveTrip(Trip trip, int userId){
        // TODO: Validate
        User user = dataRepository.getUser(userId);
        user.activeTrip = trip;
        return true;
    }
    
    public Trip getUserActiveTrip(int userId){
        return dataRepository.getUser(userId).activeTrip;
    }

    // TODO: use DTO
    public User getUser(int userId){
        return dataRepository.getUser(userId);
    }

    public ArrayList<UserDTO> getUserList(boolean withIds){
        ArrayList<UserDTO> users = new ArrayList<>();
        
        if (withIds){
            for(User user: dataRepository.getAllUsers()){
                users.add(new UserDTO(user));
            }
        }else{
            for(User user: dataRepository.getAllUsers()){
                users.add(new UserDTO(-1, user.name));
            }
        }
        return users;
    }

}
