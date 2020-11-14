package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Userdao;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final Userdao userDao;
    final EmailSendService emailSendService;
    
    @Autowired
    public UserDetailsServiceImpl(Userdao userDao,EmailSendService emailSendService) {
		this.userDao = userDao;
		this.emailSendService = emailSendService;
		
	}

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        grantList.add(authority);
        System.out.println(user.getPassword());
        return new MyUserDetails(user.getUser_id(), user.getUsername(), user.getPassword(), grantList,user.getEmail(),user.getFirst_name(),user.getLast_name(),user.isIs_enabled());
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
        emailSendService.sendEmail(email, subject, message);
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
        emailSendService.sendEmail(email, subject, message);
    }
}