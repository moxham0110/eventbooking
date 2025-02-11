package com.pmoxham.eventbooking.service;

import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.model.User;

import java.util.List;

public interface IUserService {
    UserDTO registerUser(UserDTO userDto);
    List<User> getAllUsers();
}
