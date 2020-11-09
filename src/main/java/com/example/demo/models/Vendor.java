package com.example.demo.models;

import java.util.List;

public class Vendor {
	private int supplier_id;
	private  String company_name;
	private int pancard_no;
	User user;
	private List<String> numbers;

	public int getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public int getPancard_no() {
		return pancard_no;
	}
	public void setPancard_no(int pancard_no) {
		this.pancard_no = pancard_no;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
}
