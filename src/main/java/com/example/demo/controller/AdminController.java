package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ProductDao;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Product;

@Controller
@RequestMapping("/admin")

public class AdminController {

	
	@Autowired
	ProductDao productdao;
	
	@ModelAttribute("username")
	protected String getUsername() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(p instanceof MyUserDetails) {
			return ((MyUserDetails) p).getUsername();
		}
		return null;
	}
	
	@GetMapping({"/",""})
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/AllProducts")
	public String inventory(ModelMap model) {
		List<Product> p=productdao.showAllProducts();
		System.out.println(p);
		model.addAttribute("prods",p);
		return "AllProducts";
	}
	
	@GetMapping("/editProduct/{product_id}")
	public String editProductPage(ModelMap model,@PathVariable("product_id") int product_id) {
		Product p = productdao.getproductbyId(product_id);
		model.addAttribute("prod", p);
		return "editProduct";
	}
}
