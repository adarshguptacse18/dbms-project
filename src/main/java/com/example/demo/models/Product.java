package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private int product_id;
    private String name;
    private String description;
    private int price;
    private int category_id;
    private int quantity=0;
    private double rating;
    private int purchased_cnt;
    public List<String> image_path = new ArrayList<>();
	private boolean hide;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", description=" + description + ", price="
				+ price + ", category_id=" + category_id + ", quantity=" + quantity + ", rating=" + rating
				+ ", purchased_cnt=" + purchased_cnt + ", image_path=" + image_path + ", hide=" + hide + "]";
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getPurchased_cnt() {
		return purchased_cnt;
	}
	public void setPurchased_cnt(int purchased_cnt) {
		this.purchased_cnt = purchased_cnt;
	}
	public List<String> getImage_path() {
		return image_path;
	}
	public void setImage_path(List<String> image_path) {
		this.image_path = image_path;
	}
	public boolean getHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	
    
}
