package com.pmoxham.eventbooking.controller;

import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.model.User;
import com.pmoxham.eventbooking.repository.UserRepository;
import com.pmoxham.eventbooking.service.IUserService;
import com.pmoxham.eventbooking.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        userDTO = service.registerUser(userDTO);
        return userDTO;
    }
}
