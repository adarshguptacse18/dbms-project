package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
	private int product_id;
	
	@NotNull
    private String name;
	
	@NotNull
	@Size(min=5)
    private String description;
	
	@NotNull
    private int price;
	
	@NotNull
    private int category_id;
    private int quantity=0;
    
    private Double rating;
    private int purchased_cnt;
    public List<String> image_path = new ArrayList<>();
	private boolean hide;
	private Integer supplier_id;
	
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
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
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
	public Integer getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}
	
	
    
}
