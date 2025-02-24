package com.pmoxham.eventbooking.dto;

import com.pmoxham.eventbooking.controller.UserController;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO extends RepresentationModel<RegistrationDTO> {
    private Long registrationID;
    private Long userID;
    private EventDTO event;
    private LocalDateTime registeredAt;

    public void addHateoasLinks(Long userID, Long registrationID, Long eventID, Pageable pageable) {
        this.add(linkTo(methodOn(UserController.class).getUserRegistrations(userID, pageable)).withRel("user-registrations"));
        this.add(linkTo(methodOn(UserController.class).registerUserForEvent(userID, eventID)).withRel("register-user-event"));
        this.add(linkTo(methodOn(UserController.class).cancelRegistration(userID, registrationID)).withRel("cancel-registration"));
    }
}
