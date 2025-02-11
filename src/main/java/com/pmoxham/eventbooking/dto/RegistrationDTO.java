package com.pmoxham.eventbooking.dto;

import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.model.User;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private Long id;
    private Long userId;
    private Long eventId;
    private LocalDateTime registeredAt;
}
