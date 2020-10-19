package com.example.demo.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	private int id;
	private String username;
	private String password;
	private final List<GrantedAuthority> authorities;

	public MyUserDetails(int id,String username,String password,List<GrantedAuthority> auth) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.authorities=auth;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "MyUserDetails [id=" + id + ", username=" + username + ", password=" + password + ", authorities="
				+ authorities + "]";
	}
	
}
