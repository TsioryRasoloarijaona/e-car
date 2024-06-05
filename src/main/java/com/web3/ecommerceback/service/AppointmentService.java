package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Appointment;
import com.web3.ecommerceback.entities.Message;
import com.web3.ecommerceback.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {
    private AppointmentRepository repository;
    private MailSender mailSender;

    public Message appointment(Appointment appointment) {
        try {
            String subject = "Appointment request - " + appointment.getAppointmentDate() + " " + appointment.getEmail();
            String body = appointment.getMessage();
            boolean sent = mailSender.sendMail(System.getenv("ADMIN_MAIL"), subject, body);
            if (sent) {
                repository.save(appointment);
                return new Message("your request has been sent",null);
            }
            return new Message(null, "invalid email");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(null,"failed to appointment");
        }
    }

    public Message validateAppointment(long id) {
        try {
            Optional<Appointment> appointment = repository.findById(id);
            if (appointment.isPresent() &&
                    appointment.get()
                            .getAppointmentDate()
                            .toLocalDate().isAfter(LocalDate.now())) {
                String subject = "Appointment request - " + appointment.get().getAppointmentDate();
                String body = "mr/mrs " + appointment.get().getFirstName() + " we are please to announce you that your appointment request has been validated " +
                        "see you on " + appointment.get().getAppointmentDate();
                boolean mail = mailSender.sendMail(appointment.get().getEmail(), subject, body);
                if (mail) {
                    repository.updateStatus("validated", id);
                    return new Message("appointment validated",null);
                }

            }

            return new Message(null,    "failed to validate appointment");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(null,    "failed to validate appointment");
        }
    }

    public Message rejectAppointment(long id) {
        try {
            Optional<Appointment> appointment = repository.findById(id);
            if (appointment.isPresent()) {
                String subject = "Appointment request - " + appointment.get().getAppointmentDate();
                String body = "mr/mrs " + appointment.get().getFirstName() + " we are sorry to announce you that your appointment request has been rejected ";
                boolean mail = mailSender.sendMail(appointment.get().getEmail(), subject, body);
                if (mail) {
                    repository.updateStatus("rejected", id);
                    return new Message("appointment rejected",null);
                }

            }
            return new Message(null,"failed to reject appointment");
        } catch (Exception e) {
            e.printStackTrace();
            return  new Message(null,"failed to reject appointment");
        }
    }

    public Message archiveAppointment(long id) {
        try {
            repository.updateStatus("archived", id);
            return new Message("archived",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(null,"failed to archive appointment");
        }
    }
}
