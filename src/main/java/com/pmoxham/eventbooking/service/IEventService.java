package com.pmoxham.eventbooking.service;

import com.pmoxham.eventbooking.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {
    EventDTO createEvent(EventDTO eventDTO);
    List<EventDTO> getAllEvents();
    void deleteEvent(Long eventID);
    EventDTO updateEvent(EventDTO eventDTO);

    Page<EventDTO> getEventsByLatest(Pageable pageable);
    Page<EventDTO> getEventsBetweenTwoDates(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
