package com.example.demo.models;

import java.util.Map;

public class Cart {
	private int cart_id;
    private Map<String,Integer> products;
	public int getCart_id() {	
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public Map<String, Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<String, Integer> products) {
		this.products = products;
	}
    
    
}
