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

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByEmail(String email);

    List<Appointment> findAppointmentsByAppointmentDate(Date appointmentDate);

    List<Appointment> findAppointmentsByAppointmentDateBetween(Date startDate, Date endDate);

    List<Appointment> findAppointmentByFirstNameContainsIgnoreCase(String name);

    List<Appointment> findAppointmentsByStatus(String status);

    @Query("select count(a) from Appointment a where MONTH(a.appointmentDate) = :month")
    Long countAppointmentsByMonth(@Param("month") int month);

    @Query("select count(a) as total, a.car.name from Appointment a group by a.car order by total desc limit 1")
    Object findFavorite();

    @Transactional
    @Modifying
    @Query("update Appointment a set a.status = :status where a.id = :id")
    void updateStatus(@Param(value = "status") String value, @Param(value = "id") Long id);

}
