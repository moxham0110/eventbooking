package com.pmoxham.eventbooking.service;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.model.Event;

import java.util.List;

public interface IEventService {
    EventDTO createEvent(EventDTO eventDTO);
    List<EventDTO> getAllEvents();
    void deleteEvent(Long eventID);
}
