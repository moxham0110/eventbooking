package com.pmoxham.eventbooking.service.impl;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.exception.EventDateInPastException;
import com.pmoxham.eventbooking.exception.InvalidRegistrationException;
import com.pmoxham.eventbooking.exception.ResourceNotFoundException;
import com.pmoxham.eventbooking.exception.UserAlreadyExistsException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("A user with this email already exists: " + userDto.getEmail());
        }

        User user = UserMapper.mapToUser(userDto);
        user = userRepository.save(user);
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::mapToUserDTO);
    }

    @Override
    public UserDTO getUserByID(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public Page<RegistrationDTO> getUserRegistrations(Long userID, Pageable pageable) {

        User user = userRepository.findById(userID).orElseThrow();
        Page<Registration> userRegistrations = registrationRepository.findByUser(user, pageable);

        return userRegistrations.map(RegistrationMapper::mapToRegistrationDTO);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO){
        User updatedUser = UserMapper.mapToUser(userDTO);

        if(userRepository.existsById(updatedUser.getId())){
            return UserMapper.mapToUserDTO(userRepository.save(updatedUser));
        }
        throw new RuntimeException("No ID for that user");
    }

    @Override
    public EventDTO registerUserForEvent(Long userID, Long eventID) {
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventID));

        if (event.getEventDate().isBefore(LocalDate.now())) {
            throw new EventDateInPastException("Cannot register for an event in the past.");
        }

        if (event.getAvailableSeats() < 1){
            throw new InvalidRegistrationException("Registration failed. No seats available.");
        }

        Registration registration = new Registration();
        registration.setUser(userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userID)));
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());

        registrationRepository.save(registration);
        return EventMapper.mapToEventDTO(event);
    }

    @Override
    public void cancelRegistration(Long userID, Long registrationID) {
        Registration registration = registrationRepository.findById(registrationID)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + registrationID));

        registrationRepository.delete(registration);
    }

}
