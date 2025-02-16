package com.pmoxham.eventbooking.service.impl;

import com.pmoxham.eventbooking.dto.EventDTO;
import com.pmoxham.eventbooking.mapper.EventMapper;
import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.repository.EventRepository;
import com.pmoxham.eventbooking.service.IEventService;
import org.springframework.stereotype.Service;

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
        event = eventRepository.save(event);
        return EventMapper.mapToEventDTO(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDTO).toList();
    }

    @Override
    public void deleteEvent(Long eventID) {
        eventRepository.deleteById(eventID);
    }



}
