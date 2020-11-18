package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.MyUserDetails;
import com.example.demo.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    final Userdao userDao;
	final private JdbcTemplate jt;
    final private EmailSendService emailSendService;
    
    @Autowired
    public UserDetailsServiceImpl(JdbcTemplate jt,EmailSendService emailSendService) {
//		this.userDao = userDao;
    	this.jt = jt;
		this.emailSendService = emailSendService;
		
	}

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userDao.findByUsername(userName);
    	String sql = "select * from user where username='" + userName + "'";
        if(jt.queryForList(sql).isEmpty()) {
        	 throw new UsernameNotFoundException(userName);
        }
        User user =(User) jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class));
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        grantList.add(authority);
        System.out.println(user.getPassword());
        return new MyUserDetails(user.getUser_id(), user.getUsername(), user.getPassword(), grantList,user.getEmail(),user.getFirst_name(),user.getLast_name(),user.isIs_enabled());
    }
    
    
}