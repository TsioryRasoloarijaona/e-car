package com.web3.ecommerceback.controller;

import com.web3.ecommerceback.entities.Appointment;
import com.web3.ecommerceback.entities.Message;
import com.web3.ecommerceback.repository.AppointmentRepository;
import com.web3.ecommerceback.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/rdv")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private AppointmentService service;
    private AppointmentRepository repository;

    @PostMapping("/take")
    public Message takeAppointement(@RequestBody Appointment appointement) {
        return service.appointment(appointement);
    }

    @GetMapping("/allRdv")
    public List<Appointment> getAllRdv() {
        return repository.ordered();
    }

    @GetMapping("/byMail/{mail}")
    public List<Appointment> getByMail(@PathVariable String mail) {
       return repository.findAppointmentsByEmail(mail);
    }

    @GetMapping("/date/{date}")
   public List<Appointment> byDate(@PathVariable Date date) {
        return repository.findAppointmentsByAppointmentDate(date);
    }

    @GetMapping("/byDateBetween")
    public List<Appointment> byDateBetween(@RequestParam Date date1, @RequestParam Date date2) {
        return repository.findAppointmentsByAppointmentDateBetween(date1, date2);
    }

    @GetMapping("/byName/{name}")
    public List<Appointment> byName(@PathVariable String name) {
        return repository.findAppointmentByFirstNameContainsIgnoreCase(name);
    }

    @GetMapping("/byStatus/{status}")
    public List<Appointment> getByStatus(@PathVariable String status) {
        return repository.findAppointmentsByStatus(status);
    }

    @PutMapping("/validate/{id}")
    public Message validateAppointment(@PathVariable long id) {
        return service.validateAppointment(id);
    }

    @PutMapping("/reject/{id}")
    public Message rejectAppointment(@PathVariable long id) {
        return service.rejectAppointment(id);
    }

    @PutMapping("/archive/{id}")
    public Message archiveAppointment(@PathVariable long id) {
        return service.archiveAppointment(id);
    }

    @GetMapping("/total/thisMonth")
    public Long totalThisMonth() {
        return service.countAppointmentsByMonth();
    }

    @GetMapping("/favorite/car")
    public Object favoriteCar(){
        return repository.findFavoriteCar();
    }

    @GetMapping("/favorite/brand")
    public Object favoriteBrand(){
        return repository.findFavoriteBrand();
    }

    @GetMapping("/count/byCar/month/{month}")
    public List<Object> getCountByMonth(@PathVariable int month) {
        return repository.appointmentCountByCar(month);
    }

    @GetMapping("/count/byStatus/{status}")
    public int getCountByStatus(@PathVariable String status) {
        return repository.countAppointmentsByStatus(status);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody String message) {
        return appointmentService.sendToAdmin(message);
    }

}
