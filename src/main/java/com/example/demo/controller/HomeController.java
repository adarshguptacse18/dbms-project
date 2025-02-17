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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.EmailSendService;
import com.example.demo.dao.AddressDao;
import com.example.demo.dao.CartDao;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ComplaintsDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.Ordersdao;
import com.example.demo.dao.PhoneNumberDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.RequestDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.Userdao;
import com.example.demo.models.Address;
import com.example.demo.models.Category;
import com.example.demo.models.Complaints;
import com.example.demo.models.Customer;
import com.example.demo.models.Message;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.Request;
import com.example.demo.models.Review;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;

@Controller
public class HomeController {
    final private Userdao userDao;
	final private ProductDao productDao;
	final private CartDao cartDao;
	final private CustomerDao custDao;
	final private Ordersdao ordersDao;
	final private TransactionDao transactionDao;
    final private HttpServletRequest request;
	final private AddressDao addressDao;
	final private CategoryDao categoryDao;
	final private PhoneNumberDao phonenumberDao;
	final private ComplaintsDao complaintsDao;
	final private JdbcTemplate jt;
	final private EmailSendService emailService;
	final private RequestDao requestDao;
	@Autowired
	public HomeController(JdbcTemplate jt,Userdao userDao,ProductDao productDao,CartDao cartDao,CustomerDao custDao,Ordersdao ordresDao,TransactionDao transactionDao,
			HttpServletRequest request,AddressDao addressDao,CategoryDao categoryDao,PhoneNumberDao  phonenumberDao,ComplaintsDao complaintsDao,EmailSendService emailService,RequestDao requestDao) {
		
		this.requestDao = requestDao;
		this.jt=jt;
		this.userDao = userDao;
		this.productDao =productDao;
		this.cartDao = cartDao;
		this.custDao = custDao;
		this.ordersDao = ordresDao;
		this.transactionDao = transactionDao;
		this.request = request;
		this.addressDao = addressDao;
		this.categoryDao = categoryDao;
		this.phonenumberDao = phonenumberDao;
		this.complaintsDao = complaintsDao;
		this.emailService = emailService;
	}
	
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
	
	
	@ModelAttribute("role")
	protected String getRole() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(p instanceof MyUserDetails) {
			SimpleGrantedAuthority a =  (SimpleGrantedAuthority) ((MyUserDetails) p).getAuthorities().toArray()[0];
			return a.getAuthority();
		}
		return null;
	}
	

	public int getCustomerId() {
		return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser_id();
	}
	@GetMapping("/user")
	@ResponseBody
	public String user(Principal p) {
		return "Hi "+p.getName();
		
	}
	
	@GetMapping("/")
	public String hola() {
		String role = getRole();
		System.out.println(role);
		String res ;
		if(role==null || role.equalsIgnoreCase("ROLE_USER")) res = "redirect:/showAllProducts";
		else if(role.equalsIgnoreCase("ROLE_VENDOR")) res = "redirect:/vendor/";
		else res = "redirect:/admin/";
		return res;
	}
	


	
	
	@GetMapping("/viewProducts/{category_id}")
	public String showProducts(@PathVariable("category_id") int category_id,ModelMap model) {
		List<Product> p=productDao.showAllProducts(category_id);
		model.addAttribute("prods",p);
		return "showProducts";
	}
	@GetMapping("/showProduct")
	public String showOneProduct(@RequestParam("product_id") int id,ModelMap model) {
		Product p=productDao.getproductbyId(id);
//		List<Image> images = imagedao.getAllImagesById(id);
		List<Review> reviews = productDao.getReviewsByProductId(id);
		model.addAttribute("prod",p);
		model.addAttribute("images",p.getImage_path());
		System.out.println(p.getImage_path());
		model.addAttribute("reviews", reviews);
		return "showOneProduct";
	}
	
	@GetMapping({"/showAllProducts"})
	public String showAllProducts(ModelMap model) {
		List<Product> p=productDao.showAllProducts();
		model.addAttribute("prods",p);
		return "showProducts";
	}

	
	
	@GetMapping("/addToCart")
	@ResponseBody
	public Message addToCart(@RequestParam("product_id") int product_id,Principal p,@RequestParam("quantity") int quantity) {
		int cart_id = custDao.getCartId(getCustomerId());
		cartDao.save(cart_id, product_id, quantity);
		return new Message(true,"Product Added");
//		return "{'status':true,Product" + cart_id + "}";
	}
	
	@GetMapping("/myCart")
	public String showMyCart(ModelMap model) {
		List<Product> prods = cartDao.getProducts(custDao.getCartId(getCustomerId()));
		model.addAttribute("prods",prods);
		double price=0;
		for(Product p:prods) price+=p.getQuantity() * p.getPrice();
		model.addAttribute("price", price);
		return "myCart";
	}


	
	@GetMapping("/placeOrder")
	public String placeOrder(ModelMap model,Principal ppl) {
		System.out.println(addressDao.getAddressById(1));
        List<Address> addresses = addressDao.getAddressesByCustomerId(getCustomerId());
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
			List<Product> prods = cartDao.getProducts(custDao.getCartId(getCustomerId()));
	        Double price = 0.0;
	        for(Product p: prods) {
	                    int product_id = p.getProduct_id();
	                    int quantity = p.getQuantity();
	                    Product currproduct = productDao.getproductbyId(product_id);
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
	       Address a = addressDao.getAddressById(address_id);
	       o.setHouse_no(a.getHouse_no());
	       o.setStreet_no(a.getStreet_no());
	       o.setLocality_and_city(a.getLocality_and_city());
	       o.setPincode(a.getPincode());	
	       o.setState(a.getState());
	       o.setCustomer_id(getCustomerId());
	       o.setIs_gift(false);
	       o.setStatus("Processing");
	       o.setOrder_date(new Date());
	       int order_id = ordersDao.save(o);
	       System.out.println(order_id);
	       model.put("order_id", order_id);
	       model.put("items", prods);
	       model.put("price", price);
		return "payment";
	}
	
	@GetMapping("/vieworders")
	public String getAllOrders(ModelMap m) {
		int cust_id = getCustomerId();
		List<Orders> orders = ordersDao.getOrderByCustomer_id(cust_id);
		m.addAttribute("orders", orders);
		return "myOrders";
	}
	
	@RequestMapping(value="/processpayment")
	public String processPayment(ModelMap map,@RequestParam("payment_method") String payment_method, @RequestParam("order_id") int order_id) {
		Transaction tr= new Transaction();
		ordersDao.updateorder(order_id, "SUCCESS");
		Orders o = ordersDao.getorderbyId(order_id);
		tr.setAmount(o.getAmount());
		tr.setDate(o.getOrder_date());
		tr.setPayment_method(payment_method);
		tr.setOrder_id(o.getOrder_id());
		int tr_id = transactionDao.save(o.getAmount(), o.getOrder_id(), o.getStatus(),payment_method);
		ordersDao.updateorder(order_id, tr_id);
		List<Product> prods = ordersDao.getItemsByOrderId(order_id);
		for(Product p : prods) {
			productDao.updateProductquantity(p.getProduct_id(), -p.getQuantity());
			productDao.incrementPurchase(p.getProduct_id());
			Product t = productDao.getproductbyId(p.getProduct_id());
			if(t.getQuantity()<5) {
				try {
					emailService.sendMailToVendor(userDao.findUserByUserId(p.getSupplier_id()), p);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	

		return "redirect:/vieworders";
	}
	
	
	@GetMapping("/rateOrder/{order_id}/{product_id}")
	public String updateRatingPage(@PathVariable("order_id") int order_id,@PathVariable("product_id") int product_id,ModelMap model) {
		model.addAttribute("order_id", order_id);
		model.addAttribute("product_id",product_id);
		return "rateProduct";
	}
	@PostMapping("/rateOrder/{order_id}/{product_id}")
	public String updateRatingPage(@PathVariable("order_id") int order_id,@PathVariable("product_id") int product_id,ModelMap model,int rating) {
		ordersDao.updateRating(order_id, product_id, rating);
		return "redirect:/viewOrder/"+order_id;
		
	}
	
	
	
	
	@GetMapping("/viewOrder/{order_id}")
	public String getOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		Orders o = ordersDao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "viewOrders";
	}
	@GetMapping("/cancelOrder/{order_id}")
	public String cancelOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		ordersDao.updateorder(order_id,"CANCELLED");
		Orders o = ordersDao.getorderbyId(order_id);
		List<Product> prods = ordersDao.getItemsByOrderId(order_id);
		for(Product p:prods) {
			productDao.updateProductquantity(p.getProduct_id(),p.getQuantity());
		}
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
		productDao.saveReview(r);
		return new Message(true,"Review Added");
    }
	@PostMapping("/updateReview/{product_id}")
	@ResponseBody	
    public String updateReview(Principal principal, @PathVariable("product_id") int product_id,String message) {
		Review r = new Review();
		r.setCustomer_id(getCustomerId());
		r.setMessage(message);
		r.setProduct_id(product_id);
		productDao.updateReview(r);
		return "review updated";
    }

	@GetMapping("/getAllCategories")
	@ResponseBody
	public List<Category> getAllCategories() {
		return categoryDao.showAllCategory();
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
		try {
			Address add= new Address(getCustomerId(), house_no, street_no, locality_and_city, pincode, state);
			addressDao.addAddress(add);
		} catch (Exception e) {
			model.addAttribute("error","Something Wrong happened");
			return "addAddress";
		}
		return "redirect:/";
	}
	
	@GetMapping("/deleteAddress/{address_id}")
	public String deleteAddress(ModelMap model,@PathVariable("address_id") int address_id) {
		
		addressDao.deleteAddress(address_id,getCustomerId());
		return "redirect:/";
	}
	@GetMapping("/myProfile")
	public String myAccountPage(ModelMap model) {
		Customer c = custDao.getCustomerByCustomerId(getCustomerId());
		final MyUserDetails p =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User u= new User(p.getUsername(), p.getPassword(), p.getEmail(), p.getFirst_name(), p.getLast_name(), p.getUser_id());
		c.setUser(u);
		model.addAttribute("customer", c);
		return "myProfile";
	}
	
	@GetMapping("/editProfile")
	public String editProfilePage(ModelMap model) {
		Customer c = custDao.getCustomerByCustomerId(getCustomerId());
		final MyUserDetails p =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User u= new User(p.getUsername(), p.getPassword(), p.getEmail(), p.getFirst_name(), p.getLast_name(), p.getUser_id());
		c.setUser(u);
		System.out.println(c);
		model.addAttribute("customer", c);
		return "editProfile";
	}
	
	@PostMapping("/editProfile")
	public String editProfilePage(Customer customer) {
		int user_id = getCustomerId();
		customer.setCustomer_id(user_id);
		System.out.println("asfdljasl;kdjf;lsajdfl;kjasd;lkfjsad");
		 System.out.println(customer.getUser());
		customer.setUser(new User( customer.getUser().getUsername(),  customer.getUser().getPassword(),  customer.getUser().getEmail(),  customer.getUser().getFirst_name(),  customer.getUser().getLast_name(), customer.getUser().getUser_id()));
		custDao.update(customer);
		final MyUserDetails p =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		p.setEmail(customer.getUser().getEmail());
		p.setFirst_name(customer.getUser().getFirst_name());
		p.setLast_name(customer.getUser().getLast_name());
		return "redirect:/myProfile";
	}
	
	
	@GetMapping("/myPhoneNumbers")
	public String myPhoneNumbersPage(ModelMap model) {
		model.addAttribute("numbers", phonenumberDao.getAllPhoneNumbersByCustomerId(getCustomerId()));
		return "myPhoneNumbers";
	}
	
	@PostMapping("/myPhoneNumbers")
	@ResponseBody
	public Message getPhoneNumber(@RequestParam("phone_number") String phone_number) {
		
		phonenumberDao.addPhoneNumber(phone_number, getCustomerId());
		return new Message(true,"Phone Number Added Successfully");
	}
	@GetMapping("/delete_number/{phone_number}")
	public String deletePhoneNumber(@PathVariable String phone_number) {
		phonenumberDao.deletePhoneNumber(getCustomerId(),phone_number);
		return "redirect:/myPhoneNumbers";
	}
		
	@GetMapping("/viewAddresses")
	public String showAddresses(ModelMap model) {
        List<Address> addresses = addressDao.getAddressesByCustomerId(getCustomerId());
        model.addAttribute("addresses",addresses);
        return "viewAddresses";
	}
	
	@GetMapping("/addComplaint")
	public String addComplaintPage(ModelMap model) {
		try {
			getCustomerId();
			model.addAttribute("complaint", new Complaints());
		} catch(Exception e) {
			return "redirect:/login";
		}
        return "addComplaint";
	}
	@PostMapping("/addComplaint")
	public String addComplaint(ModelMap model,Complaints c) {
		c.setUser_id(getCustomerId());
		complaintsDao.save(c);
		return "redirect:/";
	}
	
	@GetMapping("/viewComplaints")
	public String allComplaints(ModelMap model) {
		model.addAttribute("complaints", complaintsDao.getComplaintsByUserId(getCustomerId()));
		return "complaintsPage";
	}

	@GetMapping("/categories")
	public String AllCategoriesPage(ModelMap model) {
			model.addAttribute("cat", getAllCategories());
			return "allCategories";
	}
	
	@GetMapping("/filters")
	public String filterPage() {
		return "filters";
	}
	
	@GetMapping("/filter/price")
	public String filterByPrice(ModelMap model,@RequestParam("min_price")int min_price,@RequestParam("max_price") int max_price) {
		List<Product> p=productDao.filterByPrice(min_price,max_price);
		model.addAttribute("prods",p);
		return "showProducts";	
	}
	
	
	@GetMapping("/filter/rating")
	public String filterByRating(ModelMap model,@RequestParam("min_rating")int min_rating,@RequestParam("max_rating") int max_rating) {
		List<Product> p=productDao.filterByRating(min_rating,max_rating);
		model.addAttribute("prods",p);
		return "showProducts";	
	}
	
	@GetMapping("/contactUs")
	public String contactUsPage(Model model,String error,String success) {
		if(error!=null)
			model.addAttribute("error", "Please Fill all the details");
//		System.out.println(success!=null);
		if(success!=null) {
			model.addAttribute("successmessage","Thank you!!! We will Contact you shortly");
		}
	
		return "contactUsPage";
	}
	
	@PostMapping("/submitRequest")
	public String addRequest(ModelMap model,@RequestParam("name") String name,@RequestParam("email_id") String email_id,@RequestParam("subject") String subject,@RequestParam("message") String message) {
		Request r = new Request();

		if(name.equals("") || email_id.equals("") || subject.equals("") || message.equals("")) {
//			model.addAttribute("error", "Please Fill all the details");
			return "redirect:/contactUs?error";
		}
		r.setEmail_id(email_id);
		r.setName(name);
		r.setMessage(message);
		r.setSubject(subject);
		requestDao.save(r);
//		model.addAttribute("success	","Thank you!!! We will Contact you shortly");
		return "redirect:/contactUs?success";
	}
	
}
