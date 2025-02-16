package com.pmoxham.eventbooking.dto;

import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.model.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private Long registrationID;
    private Long userID;
    private EventDTO event;
    private LocalDateTime registeredAt;
}
