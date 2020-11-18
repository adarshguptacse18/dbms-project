package com.example.demo.models;

public class Request {
	@Override
	public String toString() {
		return "Request [request_id=" + request_id + ", email_id=" + email_id + ", name=" + name + ", subject="
				+ subject + ", message=" + message + "]";
	}
	private int request_id;
	private String email_id;
	private String name;
	private String subject;
	private String message;

	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
