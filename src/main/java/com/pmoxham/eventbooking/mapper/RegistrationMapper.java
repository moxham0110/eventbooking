package com.pmoxham.eventbooking.mapper;

import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.model.Registration;
import com.pmoxham.eventbooking.model.User;

public class RegistrationMapper {
    public static RegistrationDTO mapToRegistrationDTO(Registration registration, RegistrationDTO registrationDTO) {
        registrationDTO.setId(registration.getId());
        registrationDTO.setUserId(registration.getUser().getId());
        registrationDTO.setEventId(registration.getEvent().getId());
        registrationDTO.setRegisteredAt(registration.getRegisteredAt());
        return registrationDTO;
    }

    public static Registration mapToRegistration(RegistrationDTO registrationDTO, User user, Event event, Registration registration) {
        registration.setId(registrationDTO.getId());
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegisteredAt(registrationDTO.getRegisteredAt());
        return registration;
    }
}
