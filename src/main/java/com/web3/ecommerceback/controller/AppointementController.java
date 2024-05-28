package com.web3.ecommerceback.controller;

import com.web3.ecommerceback.entities.Appointment;
import com.web3.ecommerceback.repository.AppointementRepository;
import com.web3.ecommerceback.service.AppointementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rdv")
@AllArgsConstructor
public class AppointementController {
    private AppointementService service;
    private AppointementRepository repository;

    @PostMapping("/take")
    public String takeAppointement(@RequestBody Appointment appointement) {
        return service.appointement(appointement);
    }

    @GetMapping("/allRdv")
    public List<Appointment> getAllRdv() {
        return repository.findAll();
    }

    @GetMapping("/byMail/{mail}")
    public Optional<Appointment> getByMail(@PathVariable String mail) {
       return repository.findAppointementsByEmail(mail);
    }

    @GetMapping("/date/{date}")
   public Optional<Appointment> byDate(@PathVariable Date date) {
        return repository.findAppointementsByAppointmentDate(date);
    }

    @GetMapping("/byDateBetween")
    public Optional<Appointment> byDateBetween(@RequestParam Date date1, @RequestParam Date date2) {
        return repository.findAppointementsByAppointmentDateBetween(date1, date2);
    }

    @GetMapping("/byName/{name}")
    public Optional<Appointment> byName(@PathVariable String name) {
        return repository.findAppointementByFirstNameContainsIgnoreCase(name);
    }

    @GetMapping("/byStatus/{status}")
    public List<Appointment> getByStatus(@PathVariable String status) {
        return repository.findAppointementsByStatus(status);
    }




}
