package com.web3.ecommerceback.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailSender {
    private JavaMailSender emailSender;

    public boolean sendMail (String to , String subject , String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(System.getProperty("GMAIL_USERNAME"));
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
