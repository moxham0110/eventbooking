package com.pmoxham.eventbooking.mapper;

import com.pmoxham.eventbooking.dto.RegistrationDTO;
import com.pmoxham.eventbooking.model.Registration;

public class RegistrationMapper {
    public static RegistrationDTO mapToRegistrationDTO(Registration registration) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setRegistrationID(registration.getId());
        registrationDTO.setUserID(registration.getUser().getId());
        registrationDTO.setEvent(EventMapper.mapToEventDTO(registration.getEvent()));
        registrationDTO.setRegisteredAt(registration.getRegisteredAt());
        return registrationDTO;
    }

}
