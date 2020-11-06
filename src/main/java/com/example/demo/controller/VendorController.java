package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ProductDao;
import com.example.demo.dao.Userdao;
import com.example.demo.dao.VendorDao;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Product;
import com.example.demo.models.Vendor;

@Controller
@RequestMapping("/vendor")

public class VendorController {

	
	final ProductDao productDao;
	final VendorDao vendorDao;
	final Userdao userDao;
	
	@Autowired
	public VendorController(ProductDao productDao, VendorDao venderDao, Userdao userDao) {
		this.productDao = productDao;
		this.vendorDao = venderDao;
		this.userDao = userDao;
	}
	
	@ModelAttribute("username")
	protected String getUsername() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(p instanceof MyUserDetails) {
			return ((MyUserDetails) p).getUsername();
		}
		return null;
	}
	
	@ModelAttribute("role")
	protected String getRole() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(p instanceof MyUserDetails) {
			SimpleGrantedAuthority a =  (SimpleGrantedAuthority) ((MyUserDetails) p).getAuthorities().toArray()[0];
			return a.getAuthority();
		}
		return null;
	}
	
	
	@GetMapping({"/",""})
	public String home() {
		return "vendorHome";
	}
	
	
	@GetMapping("/register")
	public String registerPage(ModelMap model) {
		model.addAttribute("vendor", new Vendor());
		return "registerVendor";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/register")
	public String registerVendor(Vendor v) {
		vendorDao.save(v);
		return "redirect:/vendor/login";	
	}
	
	@GetMapping("/viewProducts")
	public String viewProducts(ModelMap model) {
		List<Product> p=productDao.showAllProducts(false);
		System.out.println(p);
		model.addAttribute("prods",p);
		return "allProductsForVendor";
	}
	
	@GetMapping("/viewProduct/{product_id}")
	public String viewProduct(ModelMap model, @PathVariable("product_id") int product_id) {
		Product p = productDao.getproductbyId(product_id);
		model.addAttribute("prod", p);
		return "viewProduct";
	}
	

}
