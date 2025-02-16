package com.pmoxham.eventbooking.controller;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.repository.EventRepository;
import com.pmoxham.eventbooking.service.IEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @DeleteMapping("/{eventID}") void deleteEvent(@PathVariable Long eventID){
        eventService.deleteEvent(eventID);
    }
}
