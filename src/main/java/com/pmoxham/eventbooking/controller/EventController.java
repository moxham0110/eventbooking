package com.pmoxham.eventbooking.controller;

import com.pmoxham.eventbooking.constants.RegistrationConstants;
import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.dto.ResponseDto;
import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.repository.EventRepository;
import com.pmoxham.eventbooking.service.IEventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(RegistrationConstants.STATUS_201, RegistrationConstants.MESSAGE_201_EVENT));
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateEvent(@Valid @RequestBody EventDTO eventDTO) {
        EventDTO updatedEvent = eventService.updateEvent(eventDTO);
        return ResponseEntity.ok(new ResponseDto(RegistrationConstants.STATUS_200, RegistrationConstants.MESSAGE_200_EVENT_UPDATE));
    }

    @DeleteMapping("/{eventID}")
    public ResponseEntity<ResponseDto> deleteEvent(@PathVariable Long eventID) {
        eventService.deleteEvent(eventID);
        return ResponseEntity.ok(new ResponseDto(RegistrationConstants.STATUS_200, RegistrationConstants.MESSAGE_200_EVENT_DELETE));
    }

    @GetMapping("/latest")
    public ResponseEntity<Page<EventDTO>> getEventsByLatest(Pageable pageable) {
        Page<EventDTO> events = eventService.getEventsByLatest(pageable);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/between")
    public ResponseEntity<Page<EventDTO>> getEventsBetweenTwoDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Pageable pageable) {
        Page<EventDTO> events = eventService.getEventsBetweenTwoDates(startDate, endDate, pageable);
        return ResponseEntity.ok(events);
    }
}
