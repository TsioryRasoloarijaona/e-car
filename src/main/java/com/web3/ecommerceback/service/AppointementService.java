package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Appointement;
import com.web3.ecommerceback.repository.AppointementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointementService {
    private AppointementRepository repository;
    private MailSender mailSender;

    public String appointement(Appointement appointement) {
        try{
            String subject = "Appointment request - "+appointement.getAppointmentDate()+" "+appointement.getEmail();
            String body = appointement.getMessage();
            boolean sent = mailSender.sendMail(System.getenv("ADMIN_MAIL"),subject,body);
            if(sent){
                repository.save(appointement);
            }
            return "your request has been sent";
        }catch (Exception e){
            e.printStackTrace();
            return "failed to appointment";
        }
    }
}
