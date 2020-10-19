package com.example.demo.models;

public class User {
    private String username;
    private String password;
    private int id;
//    private String passwordConfirm;
//    private String name;
//    private String contact;
//    private String email;
//    private String house_no;
//    private String street_name;
//    private String city;
//    private String account_no;
    private String role;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}