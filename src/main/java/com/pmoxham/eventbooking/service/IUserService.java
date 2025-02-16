package com.pmoxham.eventbooking.service;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.model.User;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDto);
    List<UserDTO> getAllUsers();
    UserDTO getUserByID(Long id);

    List<RegistrationDTO> getUserRegistrations(Long userID);
    EventDTO registerUserForEvent(Long userID, Long eventID);
    void cancelRegistration(Long userID, Long registrationID);
}
