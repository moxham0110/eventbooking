package com.pmoxham.eventbooking.repository;

import com.pmoxham.eventbooking.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findTop10ByOrderByEventDateDesc(Pageable pageable);
    Page<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}