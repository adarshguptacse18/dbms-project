package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmailSendService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email, String subject, String message) {
    	System.out.println("Sending mail");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("adarshgupta5644@gmail.com");
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(message);
        javaMailSender.send(msg);
        System.out.println("Mail Sent");
    }

}


