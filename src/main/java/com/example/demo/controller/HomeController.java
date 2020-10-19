package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.CartDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.Userdao;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Product;
import com.example.demo.models.User;

@Controller
public class HomeController {
	@Autowired
    Userdao userdao;
	
	@Autowired
	ProductDao productdao;
	
	@Autowired
	CustomerDao customerdao;
	
	@Autowired
	CartDao cartdao;
	
	
	@GetMapping("/")
	public String helloWorld() {
		System.out.println(productdao.showAllProducts()	);
		return "home";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String user(Principal p) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth.getAuthorities()));
//		System.out.println(auth.getPrincipal());
//		MyUserDetails z=  (MyUserDetails) auth.getPrincipal();
//		System.out.println(z);
//		System.out.println(p.getClass());
//		auth.getDetails();
//		auth.getDetails()
		return "Hi "+p.getName();
		
	}
	@GetMapping("/vendor")
	@ResponseBody
	public String vendor() {
		return "Hi vendor";
	}
	
	
	@GetMapping("/admin")
	public String admin() {
		return "Hey Admin";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("user", new User());
		return "UserRegister";
	}
	
	@GetMapping("/registerVendor")
	public String registerVendor(ModelMap model,Principal p) {
		User t=new User();
		model.addAttribute("user", t);
		return "UserRegister";
	}	
	@PostMapping("/register")
	public String userRegister(ModelMap model, User user) {
		user.setRole("ROLE_USER");
		userdao.save(user.getUsername(), user.getPassword(), user.getRole());
		user= userdao.findByUsername(user.getUsername());
		customerdao.save(user.getId(), 1234);
		return "redirect:/";
	}
	@PostMapping("/registerVendor")
	public String vendorRegister(ModelMap model, User user) {
		user.setRole("ROLE_VENDOR");
		userdao.save(user.getUsername(), user.getPassword(), user.getRole());
		return "redirect:/";
	}
	
	@GetMapping("/addProduct")
	public String addProductPage(ModelMap model) {
		model.addAttribute("prod", new Product());
		return "addProduct";
	}
	@PostMapping("/addProduct")
	public String addProduct(ModelMap model, Product prod) {
		productdao.save(prod.getName(), prod.getDescription(), prod.getPrice(), prod.getCategory_id());
		return "redirect:/";
	}
	@GetMapping("/showProducts")
	public String showProducts(@RequestParam("category_id") int category,ModelMap model) {
		List<Product> p=productdao.showAllProducts(category);
		model.addAttribute("prods",p);
		return "showProducts";
	}
	
	@GetMapping("/showAllProducts")
	public String showAllProducts(ModelMap model) {
		List<Product> p=productdao.showAllProducts();
		model.addAttribute("prods",p);
		return "showProducts";
	}

	
	
	@GetMapping("/addToCart")
	@ResponseBody
	public String addToCart(@RequestParam("product_id") int product_id,Principal p,@RequestParam("quantity") int quantity) {
		int cart_id = customerdao.getCartId(((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
		cartdao.save(cart_id, product_id, quantity);
		return "Product Added" + cart_id;
	}
}
