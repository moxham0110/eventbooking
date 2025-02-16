package com.pmoxham.eventbooking.controller;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping("/{userID}")
    public UserDTO getUserByID(@PathVariable Long userID){
        return service.getUserByID(userID);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        userDTO = service.createUser(userDTO);
        return userDTO;
    }

    @GetMapping("/{userID}/registrations")
    public List<RegistrationDTO> getUserRegistrations(@PathVariable Long userID) {
        return service.getUserRegistrations(userID);
    }

    @PostMapping("/{userID}/registrations/{eventID}")
    public EventDTO registerUserForEvent(@PathVariable Long userID, @PathVariable Long eventID) {
        return service.registerUserForEvent(userID, eventID);
    }

    @DeleteMapping("/{userID}/registrations/{registrationID}")
    public void cancelRegistration(@PathVariable Long userID, @PathVariable Long registrationID) {
        service.cancelRegistration(userID, registrationID);
    }

}
