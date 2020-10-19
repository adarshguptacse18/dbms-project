package com.example.demo.models;

public class Customer {
	private int customer_id;
	private int GSTIN_NUMBER;
	private int cart_id;
	private double trust_value;
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	
}
