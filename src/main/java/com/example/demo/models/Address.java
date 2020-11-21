package com.example.demo.models;

import javax.validation.constraints.NotNull;

public class Address {
	private int customer_id;
	private int address_id;
	@NotNull
	private String house_no;
	@NotNull
	private String street_no;
	@NotNull
	private String locality_and_city;
	@NotNull
	private String pincode;
	@NotNull
	private String state;
	
	public Address() {};
	public Address(int customer_id, String house_no, String street_no, String locality_and_city,
			String pincode, String state) {
		super();
		this.customer_id = customer_id;
		this.house_no = house_no;
		this.street_no = street_no;
		this.locality_and_city = locality_and_city;
		this.pincode = pincode;
		this.state = state;
	}

	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getStreet_no() {
		return street_no;
	}
	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}
	public String getLocality_and_city() {
		return locality_and_city;
	}
	public void setLocality_and_city(String locality_and_city) {
		this.locality_and_city = locality_and_city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "customer_id=" + customer_id +  ", house_no= '" + house_no
				+ "', street_no='" + street_no + "', locality_and_city='" + locality_and_city + "', pincode='" + pincode
				+ "', state='" + state + "' where " + " address_id=" + address_id + ";";
	}
	
	public String toStringForDisplay() {
		return "House No: " + house_no
		+ ", Street No:" + street_no + ", Locality And city:" + locality_and_city + ", Pincode:" + pincode
		+ ", State:" + state;
	}
			
}
