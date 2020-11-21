package com.example.demo.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private int user_id;
	
	
	@NotNull
	@Size(min=3)
    private String username;
    private String password;
	@NotNull
    private String email;
	@NotNull
    private String first_name;
    private boolean is_enabled;
    private String token;
	@NotNull
	private String last_name;
    private String role;

    @Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", first_name=" + first_name + ", is_enabled=" + is_enabled + ", last_name=" + last_name + ", role="
				+ role + "]";
	}
	public User() {
    	
    }
    public User(String username,String password,String email,String first_name,String last_name,int user_id) {
    	this.username=username;
    	this.password=password;
    	this.email=email;
    	this.last_name=last_name;
    	this.first_name = first_name;
    	this.user_id=user_id;
    }
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

  
	//    private String passwordConfirm;
//    private String name;
//    private String contact;
//    private String email;
//    private String house_no;
//    private String street_name;
//    private String city;
//    private String account_no;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  

//    public String getPasswordConfirm() {
//        return this.passwordConfirm;
//    }
//
//    public void setPasswordConfirm(String passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getContact() {
//        return this.contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getHouse_no() {
//        return this.house_no;
//    }
//
//    public void setHouse_no(String house_no) {
//        this.house_no = house_no;
//    }
//
//    public String getStreet_name() {
//        return this.street_name;
//    }
//
//    public void setStreet_name(String street_name) {
//        this.street_name = street_name;
//    }
//
//    public String getCity() {
//        return this.city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getAccount_no() {
//        return this.account_no;
//    }
//
//    public void setAccount_no(String account_no) {
//        this.account_no = account_no;
//    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }


	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public boolean isIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}