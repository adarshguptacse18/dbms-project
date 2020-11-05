package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

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

import com.example.demo.dao.CategoryDao;import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.ImageDao;
import com.example.demo.dao.Ordersdao;
import com.example.demo.dao.PhoneNumberDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.Userdao;
import com.example.demo.models.Category;
import com.example.demo.models.Customer;
import com.example.demo.models.Message;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.User;

@Controller
@RequestMapping("/admin")

public class AdminController {

	
	@Autowired
	ProductDao productdao;
	
	@Autowired 
	CategoryDao categorydao;
	
	@Autowired
	ImageDao imagedao;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	TransactionDao transactiondao;
	
	@Autowired
	Ordersdao ordersdao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired 
	PhoneNumberDao phoneNumberDao;
	
	@Autowired
	Userdao userDao;
	
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
		List<Product> p=productdao.showAllProducts(true);
		System.out.println(p);
		model.addAttribute("prods",p);
		return "AllProducts";
	}
	
	@GetMapping("/viewProducts/{category_id}")
	public String viewProductByCategory(@PathVariable("category_id") int category_id,ModelMap model) {
		List<Product> p=productdao.showAllProducts(category_id);
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
		if(file!=null) {
			imagedao.save(file, request,product_id);
		}
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
		productdao.hideShow(product_id);
		return new Message(true, "Updated");
	}
	
	
	
	@GetMapping("/addProduct")
	public String addProductPage(ModelMap model) {
		model.addAttribute("prod", new Product());
		return "addProduct";
	}
	
	
	
	@PostMapping("/addProduct")
	public String addProduct(ModelMap model, Product prod,MultipartFile file) {
		int product_id = productdao.save(prod.getName(), prod.getDescription(), prod.getPrice(), prod.getCategory_id());
		imagedao.save(file, request, product_id);
		return "redirect:/admin";
	}
	
	
	@GetMapping("/getAllCategories")
	@ResponseBody
	public List<Category> getAllCategories() {
		return categorydao.showAllCategory();
	}
	
	@GetMapping("/AllCategories")
	public String AllCategoriesPage(ModelMap model) {
			model.addAttribute("cat", getAllCategories());
			return "allCategories";
	}
	
	@GetMapping("/editCategory/{category_id}")
//	@ResponseBody
	public String editCategoryPage(ModelMap model,@PathVariable("category_id") int category_id) {
		Category cat = categorydao.getCategoyById(category_id);
		model.addAttribute("cat", cat);
		return "editCategory";
	}
	@PostMapping("/editCategory/{category_id}")
	public String editCategory(ModelMap model, @PathVariable("category_id") int category_id,Category cat) {
		categorydao.updateCategoryName(category_id, cat.getCategory_name());
		return "redirect:/admin/AllCategories";
	}
//	
	@GetMapping("/addCategory")
	public String addCategoryPage(ModelMap model) {
		return "addCategory";
	}
	
	
	@PostMapping("/addCategory")
	public String addCategory(ModelMap model, String category_name) {
		categorydao.addCategory(category_name);
		return "redirect:/";
	}
	
	@GetMapping("/Orders")
	public String ordersPage(ModelMap model) {
		List<Orders> ord = ordersdao.getAllOrders(); 		
		model.addAttribute("orders", ord);
		return "allOrders";
	}
	
	@GetMapping("/Orders/{customer_id}")
	public String customerOrders(ModelMap model,@PathVariable("customer_id") int customer_id) {
		model.addAttribute("orders", ordersdao.getOrderByCustomer_id(customer_id));
		return "allOrders";
	}
	
	@GetMapping("/viewOrder/{order_id}")
	public String getOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		Orders o = ordersdao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "orderDetails";
	}
	
	@GetMapping("/viewOrder/cancelOrder/{order_id}")
	public String cancelOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		ordersdao.updateorder(order_id,"CANCELLED");
		Orders o = ordersdao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "redirect:/admin/viewOrder/"+order_id;
	}
	
	@GetMapping("/viewOrder/refundOrder/{order_id}")
	public String refundOrder(@PathVariable("order_id") int order_id, ModelMap model){
		ordersdao.updateorder(order_id, "REFUNDED");
		return "redirect:/admin/viewOrder/" + order_id;
	}
	
	@GetMapping("/Transactions")
	public String transactionsPage(ModelMap model) {
		model.addAttribute("transactions", transactiondao.getAllTransactions());
		return "allTransactions";
	}
	
	@GetMapping("/allCustomers")
	public String allCustomers(ModelMap model) {
		model.addAttribute("customers", customerDao.getAllCustomers());
		return "allCustomers";
	}
	
	@GetMapping("/editProfile/{customer_id}")
	public String editProfilePage(ModelMap model,@PathVariable("customer_id") int customer_id) {
		Customer c = customerDao.getCustomerByCustomerId(customer_id);
		System.out.println(c);
		model.addAttribute("customer", c);
		return "editProfile";
	}
	@PostMapping("/editProfile/{customer_id}")
	public String editProfile(ModelMap model, @PathVariable("customer_id") int customer_id,Customer customer) {
		customer.setCustomer_id(customer_id);
		System.out.println(customer.getUser());
		customerDao.update(customer);
		return "redirect:/admin/allCustomers";
	}
	
	@PostMapping("editProfile/disableEnableUser")
	@ResponseBody
	public Message enableDisableUser(int user_id) {
		userDao.disableEnableUser(user_id);
		return new Message(true, "User status changed");
	}
	
	@GetMapping("/viewEditPhoneNumber/{customer_id}")
	public String viewEditPhoneNumberPage(@PathVariable("customer_id") int customer_id, ModelMap model) {
		model.addAttribute("numbers", phoneNumberDao.getAllPhoneNumbersByCustomerId(customer_id));
		return "myPhoneNumbers";
	}
	
	@PostMapping("/viewEditPhoneNumber/{customer_id}")
	@ResponseBody
	public Message viewEditPhoneNumber(@PathVariable("customer_id") int customer_id, ModelMap model,String phone_number) {
		customerDao.addPhoneNumber(phone_number, customer_id);
		return new Message(true,"Phone number added");
	}
	
	

}
