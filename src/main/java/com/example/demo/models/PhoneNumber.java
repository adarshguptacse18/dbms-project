package com.example.demo.models;

import com.sun.istack.NotNull;

public class PhoneNumber {
	private int custommer_id;
	
	@NotNull
	private String number;
	public int getCustommer_id() {
		return custommer_id;
	}
	public void setCustommer_id(int custommer_id) {
		this.custommer_id = custommer_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
