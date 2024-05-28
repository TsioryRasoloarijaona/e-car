package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Appointment;
import com.web3.ecommerceback.repository.AppointementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointementService {
    private AppointementRepository repository;
    private MailSender mailSender;

    public String appointement(Appointment appointement) {
        try {
            String subject = "Appointment request - " + appointement.getAppointmentDate() + " " + appointement.getEmail();
            String body = appointement.getMessage();
            boolean sent = mailSender.sendMail(System.getenv("ADMIN_MAIL"), subject, body);
            if (sent) {
                repository.save(appointement);
            }
            return "your request has been sent";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to appointment";
        }
    }

    public String validateAppointment(long id) {
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
                    return "appointment validated";
                }

            }

            return "failed to validate appointment";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to validate appointment";
        }
    }

    public String rejectAppointment(long id) {
        try {
            Optional<Appointment> appointment = repository.findById(id);
            if (appointment.isPresent()) {
                String subject = "Appointment request - " + appointment.get().getAppointmentDate();
                String body = "mr/mrs " + appointment.get().getFirstName() + " we are sorry to announce you that your appointment request has been rejected ";
                boolean mail = mailSender.sendMail(appointment.get().getEmail(), subject, body);
                if (mail) {
                    repository.updateStatus("rejected", id);
                    return "appointment rejected";
                }

            }
            return "failed to reject appointment";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to reject appointment";
        }
    }

    public String archiveAppointment(long id) {
        try {
            repository.updateStatus("archived", id);
            return "appointment archived";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to archive appointment";
        }
    }
}
