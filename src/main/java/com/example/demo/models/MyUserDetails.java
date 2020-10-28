package com.example.demo.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	private int user_id;
	private String username;
	private String password;
	private final List<GrantedAuthority> authorities;
	private String email;
	private String first_name;
	private String last_name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MyUserDetails(int user_id,String username,String password,List<GrantedAuthority> auth,String email,String first_name,String last_name) {
		this.user_id=user_id;
		this.username=username;
		this.password=password;
		this.authorities=auth;
		this.first_name=first_name;
		this.last_name= last_name;
		this.email = email;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String toString() {
		return "MyUserDetails [user_id=" + user_id + ", username=" + username + ", authorities=" + authorities
				+ ", email=" + email + ", first_name=" + first_name + ", last_name=" + last_name + "]";
	}
	
	
}
