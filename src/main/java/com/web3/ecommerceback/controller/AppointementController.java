package com.web3.ecommerceback.controller;

import com.web3.ecommerceback.entities.Appointement;
import com.web3.ecommerceback.repository.AppointementRepository;
import com.web3.ecommerceback.service.AppointementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rdv")
@AllArgsConstructor
public class AppointementController {
    private AppointementService service;
    private AppointementRepository repository;

    @PostMapping("/take")
    public String takeAppointement(@RequestBody Appointement appointement) {
        return service.appointement(appointement);
    }


}
