package com.example.demo.controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.example.demo.dao.CartDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.ImageDao;
import com.example.demo.dao.Ordersdao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.Userdao;
import com.example.demo.models.Address;
import com.example.demo.models.Category;
import com.example.demo.models.Image;
import com.example.demo.models.Message;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.Review;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;

@Controller
public class HomeController {
	@Autowired
    Userdao userdao;
	
	@Autowired
	ProductDao productdao;
	
	@Autowired
	CartDao cartdao;
	
	@Autowired
	CustomerDao custdao;
	
	@Autowired
	Ordersdao ordersdao;
	
	@Autowired
	TransactionDao transactiondao;
	
	@Autowired
    private HttpServletRequest request;

	@Autowired
	private ImageDao imagedao;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	
	
	public String imageUpload(MultipartFile file) {
		if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads/";
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
                System.out.println("realPathtoUploads ="+ realPathtoUploads);
                String orgName = file.getOriginalFilename();
                String filePath = realPathtoUploads + orgName ;
                File dest = new File(filePath);
                file.transferTo(dest);
            } catch (Exception e) {
            	return e.toString();
				// TODO: handle exception
			} 
		}
		return "uploadView";
	}
	@ModelAttribute("username")
	protected String getUsername() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(p instanceof MyUserDetails) {
			return ((MyUserDetails) p).getUsername();
		}
		return null;
	}
	
//	@GetMapping("/")
//	public String helloWorld() {
//		System.out.println(productdao.showAllProducts()	);
//		return "home";
//	}
//	
	public int getCustomerId() {
		return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
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
		custdao.save(user.getId(), 1234);
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
	public String addProduct(ModelMap model, Product prod,MultipartFile file) {
		int product_id = productdao.save(prod.getName(), prod.getDescription(), prod.getPrice(), prod.getCategory_id());
		imagedao.save(file, request, product_id);
		return "redirect:/";
	}
	
	@GetMapping("/addCategory")
	public String addCategoryPage(ModelMap model) {
		return "addCategory";
	}
	@PostMapping("/addCategory")
	public String addCategoryssssss(ModelMap model, String category_name) {
		productdao.addCategory(category_name);
		return "redirect:/";
	}
	
	@GetMapping("/showProducts")
	public String showProducts(@RequestParam("category_id") int category,ModelMap model) {
		List<Product> p=productdao.showAllProducts(category);
		model.addAttribute("prods",p);
		return "showProducts";
	}
	@GetMapping("/showOneProduct")
	public String showOneProduct(@RequestParam("product_id") int id,ModelMap model) {
		Product p=productdao.getproductbyId(id);
		List<Image> images = imagedao.getAllImagesById(id);
		List<Review> reviews = productdao.getReviewsByProductId(id);
		model.addAttribute("prod",p);
		model.addAttribute("images",images);
		model.addAttribute("reviews", reviews);
		return "showOneProduct";
	}
	
	@GetMapping({"/showAllProducts","/"})
	public String showAllProducts(ModelMap model) {
		List<Product> p=productdao.showAllProducts();
		model.addAttribute("prods",p);
		return "showProducts";
	}

	
	
	@GetMapping("/addToCart")
	@ResponseBody
	public Message addToCart(@RequestParam("product_id") int product_id,Principal p,@RequestParam("quantity") int quantity) {
		int cart_id = custdao.getCartId(getCustomerId());
		cartdao.save(cart_id, product_id, quantity);
		return new Message(true,"Product Added");
//		return "{'status':true,Product" + cart_id + "}";
	}
	
	@GetMapping("/myCart")
	public String showMyCart(ModelMap model) {
		List<Product> prods = cartdao.getProducts(custdao.getCartId(((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		model.addAttribute("prods",prods);
		double price=0;
		for(Product p:prods) price+=p.getQuantity() * p.getPrice();
		model.addAttribute("price", price);
		return "myCart";
	}


	
	@GetMapping("/placeOrder")
	public String placeOrder(ModelMap model,Principal ppl) {
		System.out.println(custdao.getAddressById(1));
        List<Address> addresses = custdao.getAddressesByCustomerId(getCustomerId());
        Map<Integer,String> addrs= new HashMap<Integer, String>();
		for (Address p : addresses) {
            addrs.put(p.getAddress_id(), p.toStringForDisplay());
        }
        model.put("addresses", addrs);
        return "placeOrder";
	}
	
	@GetMapping("/payment")
	public String payment(ModelMap model,int address_id, Principal principal) {
		 List<Product> err_products = new ArrayList<Product>();
			List<Product> prods = cartdao.getProducts(custdao.getCartId(((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
	        Double price = 0.0;
	        for(Product p: prods) {
	                    int product_id = p.getProduct_id();
	                    int quantity = p.getQuantity();
	                    Product currproduct = productdao.getproductbyId(product_id);
	                    int available = currproduct.getQuantity();
	                    if (available < quantity) {
	                        err_products.add(currproduct);
	                    }
	                    price += p.getPrice() * p.getQuantity();
	        }
	        if (err_products.size() != 0) {
	            model.put("errorProds", err_products);
	            model.put("products", prods);
	            return "errorOrder";
	        }
	        if(prods.size()==0) {
	        	return "redirect:/";
	        }
	      
	       Orders o = new Orders();
	       o.setProds(prods);
	       o.setAmount(price);
	       Address a = custdao.getAddressById(address_id);
	       o.setHouse_no(a.getHouse_no());
	       o.setStreet_no(a.getStreet_no());
	       o.setLocality_and_city(a.getLocality_and_city());
	       o.setPincode(a.getPincode());	
	       o.setState(a.getState());
	       o.setCustomer_id(getCustomerId());
	       o.setIs_gift(false);
	       o.setStatus("Processing");
	       o.setDate(new Date());
	       int order_id = ordersdao.save(o);
	       System.out.println(order_id);
	       model.put("order_id", order_id);
	       model.put("items", prods);
	       model.put("price", price);
		return "payment";
	}
	
	@RequestMapping(value="/processpayment")
	public String processPayment(ModelMap map,@RequestParam("payment_method") String payment_method, @RequestParam("order_id") int order_id) {
		Transaction tr= new Transaction();
		ordersdao.updateorder(order_id, "SUCCESS");
		Orders o = ordersdao.getorderbyId(order_id);
		tr.setAmount(o.getAmount());
		tr.setDate(o.getDate());
		tr.setPayment_method(payment_method);
		tr.setOrder_id(o.getOrder_id());
		int tr_id = transactiondao.save(o.getAmount(), o.getOrder_id(), o.getStatus(),payment_method);
		ordersdao.updateorder(order_id, tr_id);
		List<Product> prods = ordersdao.getItemsByOrderId(order_id);
		for(Product p : prods) {
			productdao.updateProductquantity(p.getProduct_id(), -p.getQuantity());
		}
		return "redirect:/vieworders";
	}
	
	@GetMapping("/vieworders")
	public String getAllOrders(ModelMap m) {
		int cust_id = getCustomerId();
		List<Orders> orders = ordersdao.getOrderByCustomer_id(cust_id);
		m.addAttribute("orders", orders);
		return "myOrders";
	}
	
	
	
	@GetMapping("/viewOrder/{order_id}")
	public String getOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		Orders o = ordersdao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "viewOrders";
	}
	@GetMapping("/cancelOrder/{order_id}")
	public String cancelOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		ordersdao.updateorder(order_id,"CANCELLED");
		Orders o = ordersdao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "viewOrders";
	}
		
		
	@PostMapping("/addReview/{product_id}")
	@ResponseBody	
    public Message addReview(Principal principal, @PathVariable("product_id") int product_id,String message) {
		Review r = new Review();
		r.setCustomer_id(getCustomerId());
		r.setMessage(message);
		r.setProduct_id(product_id);
		productdao.saveReview(r);
		return new Message(true,"Review Added");
    }
	@PostMapping("/updateReview/{product_id}")
	@ResponseBody	
    public String updateReview(Principal principal, @PathVariable("product_id") int product_id,String message) {
		Review r = new Review();
		r.setCustomer_id(getCustomerId());
		r.setMessage(message);
		r.setProduct_id(product_id);
		productdao.updateReview(r);
		return "review updated";
    }
	
	@PostMapping("/rateOrder/{order_id}/{product_id}")
	@ResponseBody
	public String rateProduct(@PathVariable("order_id") int order_id, @PathVariable("product_id") int product_id,int rating) {
		ordersdao.updateRating(order_id, product_id, rating);
		return "updated";
	}
	
	@PostMapping("/rateProduct")
	@ResponseBody
	public String rateProduct(@RequestParam("category_name")String category_name) {
		productdao.addCategory(category_name);
		return "Category Added";	
	}
	
	@GetMapping("/getAllCategories")
	@ResponseBody
	public List<Category> getAllCategories() {
		return productdao.showAllCategory();
	}
	
	@GetMapping("/login")
	public String login(ModelMap model,String error,String logout) {
		if(error!=null) {
			model.addAttribute("error", "Invalid Credentials");
		}
		if(logout!=null) {
			model.addAttribute("logout", "You have been logged out successfully");
		}
		return "login";
	}
	
	 
	@GetMapping("/upload")
	public String imageUpload() {
		return "uploadView";
	}
	
	@GetMapping("/addAddress")
	public String addAddressPage(ModelMap model) {
		return "addAddress";
	}
	@PostMapping("/addAddress")
	public String addAddress(ModelMap model,String house_no,String street_no,String locality_and_city,String pincode,String state) {
		Address add= new Address(getCustomerId(), house_no, street_no, locality_and_city, pincode, state);
		custdao.addAddress(add);
		return "redirect:/placeOrder";
	}
	
//  	@PostMapping("/upload")
//  	@ResponseBody
//  	public Boolean UploadImageProduct(@RequestParam("file") MultipartFile file) {
//  		return imagedao.save(file,request,1);
//  	}
//  	
//  	@GetMapping("/getImageById")
//  	@ResponseBody
//  	public String getImageById(int product_id) {
//  		return imagedao.getByProductId(product_id).getImage_path();
//  	}
//  	
//	
}
