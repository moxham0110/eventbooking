package com.pmoxham.eventbooking.mapper;

import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.model.User;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user, UserDTO userDTO){
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        return userDTO;
    }

    public static User mapToUser(UserDTO userDTO, User user){
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        return user;
    }
}
