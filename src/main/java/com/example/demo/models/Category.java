package com.example.demo.models;

import java.util.List;

import javax.validation.constraints.Size;

public class Category {
	private int category_id;
	
	@Size(min  = 3,message="Enter atleast 3 characters")
	private String category_name;
	private List<Product> prods;
	
	
	public Category() {
		
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public List<Product> getProds() {
		return prods;
	}
	public void setProds(List<Product> prods) {
		this.prods = prods;
	}
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name;
	}
	
	
}
