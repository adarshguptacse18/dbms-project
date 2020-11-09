package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ImageDao;
import com.example.demo.dao.PhoneNumberDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.Userdao;
import com.example.demo.dao.VendorDao;
import com.example.demo.models.Category;
import com.example.demo.models.Message;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.models.Vendor;

@Controller
@RequestMapping("/vendor")

public class VendorController {

	
	final ProductDao productDao;
	final VendorDao vendorDao;
	final Userdao userDao;
	final CategoryDao categoryDao; 
	final ImageDao imageDao;
	final HttpServletRequest request;
	final PhoneNumberDao phoneNumberDao;
	@Autowired
	public VendorController(ProductDao productDao, VendorDao venderDao, Userdao userDao,CategoryDao categoryDao,ImageDao imageDao, HttpServletRequest request,PhoneNumberDao phoneNumberDao) {
		this.productDao = productDao;
		this.vendorDao = venderDao;
		this.userDao = userDao;
		this.categoryDao = categoryDao;
		this.imageDao = imageDao;
		this.request = request;
		this.phoneNumberDao = phoneNumberDao;
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
	
	public int getSupplierId() {
		return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser_id();
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
	
	
	@GetMapping("/viewProducts/{category_id}")
	public String viewProductByCategory(@PathVariable("category_id") int category_id,ModelMap model) {
		List<Product> p=productDao.showAllProducts(category_id);
		model.addAttribute("prods",p);
		return "allProductsForVendor";
	}
	
	public List<Category> getAllCategories() {
		return categoryDao.showAllCategory();
	}
	
	
	
	@GetMapping("/addProduct")
	public String addProductPage(ModelMap model) {
		model.addAttribute("prod", new Product());
		model.addAttribute("categories", getAllCategories());
		return "addProduct";
	}
	
	
	
	@PostMapping("/addProduct")
	public String addProduct(ModelMap model, Product prod,MultipartFile file) {
		int product_id = productDao.save(prod.getName(), prod.getDescription(), prod.getPrice(), prod.getCategory_id());
		imageDao.save(file, request, product_id);
		return "redirect:/admin";
	}
	
	
	@GetMapping("/viewProduct/{product_id}")
	public String viewProduct(ModelMap model, @PathVariable("product_id") int product_id) {
		Product p = productDao.getproductbyId(product_id);
		System.out.println(p.getImage_path());
		model.addAttribute("prod", p);
		return "viewProduct";
	}
	
	
	@GetMapping("/myProducts")
//	@ResponseBody
	public String myProducts(ModelMap model) {
//		System.out.println(getSupplierId());
//		vendorDao.getMyProducts(17);
		List<Product> prods = vendorDao.getMyProducts(getSupplierId());
		model.addAttribute("prods", prods);
		return "myProductForVendor";
	}
	
	
	@PostMapping("/supply")
	@ResponseBody
	public Message addToSupply(@RequestParam("product_id") int product_id) {
		vendorDao.addToSupplied(product_id, getSupplierId());
		return new Message(true,"added to the supplied");
	}
	
	
	@PostMapping("/delete")
	@ResponseBody
	public Message deleteFromSupply(@RequestParam("product_id") int product_id) {
		vendorDao.deleteFromSupplied(product_id, getSupplierId());
		return new Message(true,"added to the supplied");
	}
	
	@GetMapping("/myProfile")
	public String myProfile(ModelMap model) {
		Vendor v = vendorDao.getVendorBySupplier_Id(getSupplierId());
		List<String> ph = phoneNumberDao.getAllPhoneNumbersByCustomerId(getSupplierId());
		model.addAttribute("vendor", v);
		model.addAttribute("phone_number", ph);
		return "vendorProfile";
	}
	
	@GetMapping("/editProfile")
	public String editProfilePage(ModelMap model) {
		model.addAttribute("vendor", vendorDao.getVendorBySupplier_Id(getSupplierId()));
		return "vendorEditProfile";
	}
	
	@PostMapping("/editProfile")
	public String editProfile(ModelMap model,Vendor v) {
		v.setSupplier_id(getSupplierId());
		vendorDao.update(v);
		return "redirect:/vendor/myProfile";
	}
	
	@GetMapping("/AllCategories")
	public String AllCategoriesPage(ModelMap model) {
			model.addAttribute("cat", categoryDao.showAllCategory());
			return "allCategories";
	}
	
	
	
	

}
