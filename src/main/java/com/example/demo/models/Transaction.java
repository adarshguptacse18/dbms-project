package com.example.demo.models;

import java.util.Date;

public class Transaction {
	private int transaction_id;
	private Date date;
	private double amount;
	private String status; 
	private int order_id;
	private String payment_method;
	public Transaction() {
		
	}
	public Transaction(int transaction_id,Date date,double amount,String status,int order_id,String payment_method) {
		this.transaction_id=transaction_id;
		this.date=date;
		this.amount = amount;
		this.status = status;
		this.order_id = order_id;
		this.payment_method = payment_method;
	}
	//	private String message;
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", date=" + date + ", amount=" + amount + ", status="
				+ status + ", order_id=" + order_id + ", payment_method=" + payment_method + "]";
	}
	
	
}
