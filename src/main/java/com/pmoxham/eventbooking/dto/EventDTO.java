package com.pmoxham.eventbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pmoxham.eventbooking.controller.EventController;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO extends RepresentationModel<EventDTO> {
    private Long id;

    @Size(min = 2, message = "Event name must be at least 2 characters long.")
    @NotNull(message = "Event name is required.")
    private String name;

    @Size(min = 5, message = "Event location must be at least 5 characters long.")
    @NotNull(message = "Event location is required.")
    private String location;

    @Min(value = 0, message = "Available seats cannot be negative.")
    private Long availableSeats;

    @NotNull(message = "Event date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    public void addHateoasLinks(Long eventId, Pageable pageable) {
        this.add(linkTo(methodOn(EventController.class).getAllEvents()).withRel("all-events"));
        this.add(linkTo(methodOn(EventController.class).deleteEvent(eventId)).withRel("delete-event"));
        this.add(linkTo(methodOn(EventController.class).updateEvent(null)).withRel("update-event"));
        this.add(linkTo(methodOn(EventController.class).getEventsByLatest(pageable)).withRel("latest-events"));
    }
}
