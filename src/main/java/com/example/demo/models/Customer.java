package com.example.demo.models;

import java.util.List;

public class Customer {
	private int customer_id;
	private User user;
	private int GSTIN_NUMBER;
	private int cart_id;
	private double trust_value;
	private List<String> numbers;
	
	

	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getGSTIN_NUMBER() {
		return GSTIN_NUMBER;
	}
	public void setGSTIN_NUMBER(int gSTIN_NUMBER) {
		GSTIN_NUMBER = gSTIN_NUMBER;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public double getTrust_value() {
		return trust_value;
	}
	public void setTrust_value(double trust_value) {
		this.trust_value = trust_value;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", user=" + user + ", GSTIN_NUMBER=" + GSTIN_NUMBER
				+ ", cart_id=" + cart_id + ", trust_value=" + trust_value + ", Phone_numbers=" + numbers + "]";
	}
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
	
}
