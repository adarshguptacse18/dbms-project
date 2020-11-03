package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ImageDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.models.Message;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Product;

@Controller
@RequestMapping("/admin")

public class AdminController {

	
	@Autowired
	ProductDao productdao;
	
	@Autowired
	ImageDao imagedao;
	
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
		System.out.println(p.image_path);
		return "editProduct";
	}
	
	@PostMapping("/editProduct/{product_id}")
	public String editProduct(@PathVariable("product_id") int product_id, Product p,MultipartFile file) {
		productdao.updateproduct(product_id,p);
		return "redirect:/admin/AllProducts";
	}
	
	@PostMapping("/deleteImage")
	@ResponseBody
	public Message deleteImage(int product_id,String image_path) {
		imagedao.deleteImage(product_id, image_path);
		return new Message(true, "Image Deleted");
	}
	
	
	@PostMapping("/hideProduct")
	@ResponseBody
	public Message hideProduct(@RequestParam("product_id")int product_id) {
//		productdao.hideShow(product_id);
		return new Message(true, "Updated");
	}
}
