package com.pmoxham.eventbooking.service.impl;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.mapper.EventMapper;
import com.pmoxham.eventbooking.mapper.RegistrationMapper;
import com.pmoxham.eventbooking.mapper.UserMapper;
import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.model.Registration;
import com.pmoxham.eventbooking.model.User;
import com.pmoxham.eventbooking.repository.EventRepository;
import com.pmoxham.eventbooking.repository.RegistrationRepository;
import com.pmoxham.eventbooking.repository.UserRepository;
import com.pmoxham.eventbooking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        //mapper
        User user = UserMapper.mapToUser(userDto);

        user = userRepository.save(user);
        //todo: validation - user already exists, incorrect email etc.

        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToUserDTO).toList();
    }

    @Override
    public UserDTO getUserByID(Long id) {
        //todo: Handle no user of that id in DB
        User user = userRepository.findById(id).orElseThrow();
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<RegistrationDTO> getUserRegistrations(Long userID) {

        User user = userRepository.findById(userID).orElseThrow();
        List<Registration> userRegistrations = registrationRepository.findByUser(user);

        return userRegistrations.stream().map(RegistrationMapper::mapToRegistrationDTO).toList();
    }

    @Override
    public EventDTO registerUserForEvent(Long userID, Long eventID) {

        //todo: Must be a user and valid event
        User user = userRepository.findById(userID).orElseThrow();
        Event event = eventRepository.findById(eventID).orElseThrow();

        boolean isRegistered = registrationRepository.existsByUserAndEvent(user, event);

        if(isRegistered){
            //todo: Exception handling
            throw new RuntimeException();
        }

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());

        registrationRepository.save(registration);

        return EventMapper.mapToEventDTO(event);
    }

    @Override
    public void cancelRegistration(Long userID, Long registrationID) {
        Registration registration = registrationRepository.findById(registrationID)
                .orElseThrow();

        // Ensure the registration belongs to the user
        if (!registration.getUser().getId().equals(userID)) {
            //todo: Exception handling
            throw new RuntimeException();
        }

        registrationRepository.delete(registration);
    }
}
