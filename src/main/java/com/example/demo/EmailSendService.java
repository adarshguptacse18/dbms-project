package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;

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
    public String getHostAddressAndPort() {
        String hostname = System.getenv("SERVER_HOSTNAME");
        if (hostname == null) {
            return "http://127.0.0.1:8080";
        } else {
            return hostname;
        }
    }
    
    public void sendVerificationEmail(User user) {
        String email = user.getEmail();
        String subject = "Account Creation Successful | Verify Email";
        String message = "";
        String token =user.getToken();
        message += "Hello " + user.getFirst_name() + " " + user.getLast_name() + ",\n\n";
        message += "Please go to " + getHostAddressAndPort() + "/verify-email?token=" + token;
        message += " to verify your email. Thereafter, you can login using your username '" + user.getUsername() + "'";
        message += " by going to " + getHostAddressAndPort() + "/login" + "\n\nThank you!";
        sendEmail(email, subject, message);
    }
    
    public void sendPasswordResetEmail(User user) {
        String email = user.getEmail();
        String subject = "Password Reset Email";
        String message = "";
        String token = user.getToken();
        message += "Hello " + user.getFirst_name() + " " + user.getLast_name() + ",\n\n";
        message += "Please go to " + getHostAddressAndPort() + "/reset-password?token=" + token;
        message += " to reset your password. Thereafter, you can login using your username '" + user.getUsername()
                + "'";
        message += " by going to " + getHostAddressAndPort() + "/user/login" + "\n\nThank you!";
        sendEmail(email, subject, message);
    }

}


