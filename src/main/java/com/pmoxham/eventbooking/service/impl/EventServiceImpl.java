package com.pmoxham.eventbooking.service.impl;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.exception.EventDateInPastException;
import com.pmoxham.eventbooking.exception.ResourceNotFoundException;
import com.pmoxham.eventbooking.mapper.EventMapper;
import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.repository.EventRepository;
import com.pmoxham.eventbooking.repository.RegistrationRepository;
import com.pmoxham.eventbooking.service.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = EventMapper.mapToEvent(eventDTO);

        // Check if event date is in the past
        if (event.getEventDate().isBefore(LocalDate.now())) {
            throw new EventDateInPastException("Cannot create an event with a past date: " + event.getEventDate());
        }

        event = eventRepository.save(event);
        return EventMapper.mapToEventDTO(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDTO).toList();
    }

    @Override
    public void deleteEvent(Long eventID) {
        if (!eventRepository.existsById(eventID)) {
            throw new ResourceNotFoundException("Event not found with ID: " + eventID);
        }
        eventRepository.deleteById(eventID);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO){
        Event updatedEvent = EventMapper.mapToEvent(eventDTO);

        if (!eventRepository.existsById(updatedEvent.getId())) {
            throw new ResourceNotFoundException("Cannot update: Event not found with ID: " + updatedEvent.getId());
        }

        return EventMapper.mapToEventDTO(eventRepository.save(updatedEvent));
    }

    @Override
    public Page<EventDTO> getEventsByLatest(Pageable pageable) {
        return eventRepository.findTop10ByOrderByEventDateDesc(pageable).map(EventMapper::mapToEventDTO);
    }

    @Override
    public Page<EventDTO> getEventsBetweenTwoDates(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return eventRepository.findByEventDateBetween(startDate, endDate, pageable)
                .map(EventMapper::mapToEventDTO);
    }
}
