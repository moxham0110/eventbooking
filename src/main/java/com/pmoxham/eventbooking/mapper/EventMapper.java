package com.pmoxham.eventbooking.mapper;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.model.Event;

public class EventMapper {
    public static EventDTO mapToEventDTO(Event event, EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setEventDate(event.getEventDate());
        eventDTO.setAvailableSeats(event.getAvailableSeats());
        return eventDTO;
    }

    public static Event mapToEvent(EventDTO eventDTO, Event event) {
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setLocation(eventDTO.getLocation());
        event.setEventDate(eventDTO.getEventDate());
        event.setAvailableSeats(eventDTO.getAvailableSeats());
        return event;
    }
}
