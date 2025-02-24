package com.pmoxham.eventbooking.dto;

import com.pmoxham.eventbooking.controller.UserController;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
    private Long id;

    @NotBlank(message = "User name is required.")
    @Size(min = 3, message = "User name must be at least 3 characters long.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;


    public void addHateoasLinks(Long userID, Pageable pageable) {
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserByID(userID)).withSelfRel());
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAllUsers(pageable)).withRel("all-users"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserRegistrations(userID, pageable)).withRel("user-registrations"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).updateUser(null)).withRel("update-user"));
    }
}

