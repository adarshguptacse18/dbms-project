package com.example.demo.models;

import java.util.Date;

public class Complaints {
	private int complaint_id;
	private int user_id;
	private String email_id;
	private String message;
	private Boolean is_resolved;
	private Date date;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getIs_resolved() {
		return is_resolved;
	}
	public void setIs_resolved(Boolean is_resolved) {
		this.is_resolved = is_resolved;
	}
	public int getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}
	
}
