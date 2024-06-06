package com.web3.ecommerceback.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailSender {
    private JavaMailSender emailSender;
    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);


    public boolean sendMail (String to , String subject , String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(System.getenv("GMAIL_USERNAME"));
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            logger.info("Email sent successfully to {}", to);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }
}
