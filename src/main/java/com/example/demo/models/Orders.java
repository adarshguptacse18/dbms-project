package com.example.demo.models;


import java.util.Date;
import java.util.List;

public class Orders {
    private int order_id;
    private String status;
    private double amount;
    private Date order_date;
    private boolean is_gift = false;
    private String phone_number;
    private String street_no;
    private String house_no;
    private String locality_and_city;
    private String pincode;
    private String state;
    private String transaction_id;
    private int customer_id;
    private List<Product> prods;
    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public boolean getIs_gift() {
		return is_gift;
	}

	public void setIs_gift(boolean is_gift) {
		this.is_gift = is_gift;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getStreet_no() {
		return street_no;
	}

	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}

	public String getHouse_no() {
		return house_no;
	}

	public void setHouse_no(String house_no) {
		this.house_no = house_no;
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public List<Product> getProds() {
		return prods;
	}

	public void setProds(List<Product> prods) {
		this.prods = prods;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", status=" + status + ", amount=" + amount + ", order_date="
				+ order_date + ", is_gift=" + is_gift + ", phone_number=" + phone_number + ", street_no=" + street_no
				+ ", house_no=" + house_no + ", locality_and_city=" + locality_and_city + ", pincode=" + pincode
				+ ", state=" + state + ", transaction_id=" + transaction_id + ", customer_id=" + customer_id
				+ ", prods=" + prods + "]";
	}

	
	
}