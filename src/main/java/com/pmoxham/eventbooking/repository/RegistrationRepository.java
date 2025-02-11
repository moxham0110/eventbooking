package com.pmoxham.eventbooking.repository;

import com.pmoxham.eventbooking.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
