package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Transactional
    @Modifying
    @Query("update Appointment a set a.status = :status where a.id = :id")
    void updateStatus(@Param(value = "status") String value, @Param(value = "id") Long id);

}
