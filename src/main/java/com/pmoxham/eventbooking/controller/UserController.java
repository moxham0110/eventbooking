package com.pmoxham.eventbooking.controller;

import com.pmoxham.eventbooking.constants.RegistrationConstants;
import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.dto.ResponseDto;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> getUserByID(@PathVariable Long userID) {
        UserDTO user = service.getUserByID(userID);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = service.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = service.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(RegistrationConstants.STATUS_201, RegistrationConstants.MESSAGE_201_USER));
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = service.updateUser(userDTO);
        return ResponseEntity.ok(new ResponseDto(RegistrationConstants.STATUS_200, RegistrationConstants.MESSAGE_200_UPDATE));
    }

    @GetMapping("/{userID}/registrations")
    public ResponseEntity<Page<RegistrationDTO>> getUserRegistrations(@PathVariable Long userID, Pageable pageable) {
        Page<RegistrationDTO> registrations = service.getUserRegistrations(userID, pageable);
        return ResponseEntity.ok(registrations);
    }

    @PostMapping("/{userID}/registrations/{eventID}")
    public ResponseEntity<ResponseDto> registerUserForEvent(@PathVariable Long userID, @PathVariable Long eventID) {
        service.registerUserForEvent(userID, eventID);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(RegistrationConstants.STATUS_201, RegistrationConstants.MESSAGE_201_REGISTRATION));

    }

    @DeleteMapping("/{userID}/registrations/{registrationID}")
    public ResponseEntity<ResponseDto> cancelRegistration(@PathVariable Long userID, @PathVariable Long registrationID) {
        service.cancelRegistration(userID, registrationID);
        return ResponseEntity.ok(new ResponseDto(RegistrationConstants.STATUS_200, RegistrationConstants.MESSAGE_200_DELETE));
    }
}