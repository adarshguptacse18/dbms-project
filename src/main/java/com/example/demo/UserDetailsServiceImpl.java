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
    
    
}