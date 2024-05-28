package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointementRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findAppointementsByEmail(String email);

   Optional<Appointment> findAppointementsByAppointmentDate(Date appointmentDate);

    Optional<Appointment> findAppointementsByAppointmentDateBetween(Date startDate, Date endDate);

   Optional<Appointment> findAppointementByFirstNameContainsIgnoreCase(String name);

   List<Appointment> findAppointementsByStatus(String status);

}
