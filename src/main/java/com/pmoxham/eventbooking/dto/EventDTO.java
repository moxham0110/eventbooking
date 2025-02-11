package com.pmoxham.eventbooking.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private String location;
    private LocalDate eventDate;
    private int availableSeats;
}
