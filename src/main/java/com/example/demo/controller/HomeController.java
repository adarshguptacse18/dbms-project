package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CartDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.Ordersdao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.Userdao;
import com.example.demo.models.Address;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.Transaction;
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
	
	@Autowired
	CustomerDao custdao;
	
	@Autowired
	Ordersdao ordersdao;
	
	@Autowired
	TransactionDao transactiondao;
	
	@GetMapping("/")
	public String helloWorld() {
		System.out.println(productdao.showAllProducts()	);
		return "home";
	}
	
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
		int cart_id = customerdao.getCartId(getCustomerId());
		cartdao.save(cart_id, product_id, quantity);
		return "Product Added" + cart_id;
	}
	
//	@GetMapping("/myCart")
//	public String showMyCart(ModelMap map) {
//		List<Product> prods = cartdao.getProducts(customerdao.getCartId(((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
//		map.addAttribute("prods",prods);
//		return "myCart";
//	}
	@GetMapping("/myCart")
	@ResponseBody
	public List<Product> showMyCart(ModelMap map) {
		List<Product> prods = cartdao.getProducts(customerdao.getCartId(((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
//		map.addAttribute("prods",prods);
//		return "myCart";
		return prods;
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
			List<Product> prods = cartdao.getProducts(customerdao.getCartId(((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
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
	            model.put("error", err_products);
	            model.put("products", prods);
	            return "errorOrder";
	        }
	      
	       Orders o = new Orders();
	       o.setProds(prods);
	       o.setAmount(price);
	       Address a = customerdao.getAddressById(address_id);
	       o.setHouse_no(a.getHouse_no());
	       o.setStreet_no(a.getStreet_no());
	       o.setLocality_and_city(a.getLocality_and_city());
	       o.setPincode(a.getPincode());	
	       o.setState(a.getState());
	       o.setCustomer_id(getCustomerId());
	       o.setIs_gift(false);
	       o.setStatus("TNI");
	       int order_id = ordersdao.save(o);
	       System.out.println("a;lsdjf;lkasjdflkjasldkfjlask;jdf;lajsd");
	       System.out.println(order_id);
	       model.put("order_id", order_id);
	       model.put("items", prods);
	       model.put("price", price);
		return "payment";
	}
	
	@RequestMapping(value="/processpayment")
	public String processPayment(ModelMap map,@RequestParam("payment_method") String payment_method, @RequestParam("order_id") int order_id) {
		Transaction tr= new Transaction();
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
		return "redirect:/user/vieworders";
	}
	
	@GetMapping("/vieworders")
	public String getAllOrders(ModelMap m) {
		int cust_id = getCustomerId();
		List<Orders> orders = ordersdao.getOrderByCustomer_id(cust_id);
		m.addAttribute("orders", orders);
		return "viewOrders";
	}
	
	@GetMapping("/vieworder/{order_id}")
	@ResponseBody
	public String getOrder(@PathVariable("order_id") int order_id) {
		Orders o = ordersdao.getorderbyId(order_id);
		return o.toString();
	}
		
	@RequestMapping(value = "/addfeedback/{order_id}", method = RequestMethod.GET)
    public ModelAndView addfeedback(Principal principal, @PathVariable("order_id") int order_id) {
        ModelAndView model = new ModelAndView("feedback");
//        Feedback feedback = new Feedback();
//        feedback.setOrder_id(order_id);
//        model.addObject("feedback", feedback);
        return model;
    }
	
}
