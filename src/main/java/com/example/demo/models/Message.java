package com.example.demo.models;

public class Message {
	private boolean status;
	private String message;
	public Message() {
		
	}
	public Message(boolean s,String m) {
		this.status = s;
		this.message = m;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
