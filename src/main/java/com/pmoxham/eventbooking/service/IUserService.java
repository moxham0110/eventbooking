package com.pmoxham.eventbooking.service;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDto);
    Page<UserDTO> getAllUsers(Pageable pageable);
    UserDTO getUserByID(Long id);
    UserDTO updateUser(UserDTO userDTO);


    Page<RegistrationDTO> getUserRegistrations(Long userID, Pageable pageable);
    EventDTO registerUserForEvent(Long userID, Long eventID);
    void cancelRegistration(Long userID, Long registrationID);
}
