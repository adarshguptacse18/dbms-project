package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AddressDao;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ComplaintsDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.ImageDao;
import com.example.demo.dao.Ordersdao;
import com.example.demo.dao.PhoneNumberDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.RequestDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.Userdao;
import com.example.demo.dao.VendorDao;
import com.example.demo.models.Address;
import com.example.demo.models.Category;
import com.example.demo.models.Customer;
import com.example.demo.models.Message;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.Request;
import com.example.demo.models.Vendor;

@Controller
@RequestMapping("/admin")

public class AdminController {

	
	final private ProductDao productDao;
	final private CategoryDao categoryDao;
	final private ImageDao imageDao;
	final private HttpServletRequest request;
	final private TransactionDao transactionDao;
	final private Ordersdao ordersDao;
	final private CustomerDao customerDao;
	final private PhoneNumberDao phoneNumberDao;
	final private Userdao userDao;
	final private AddressDao addressDao;
	final private VendorDao vendorDao;
	final private ComplaintsDao complaintsDao;
	final private RequestDao requestDao;
	
	@Autowired
	public AdminController(ProductDao productDao,CategoryDao categroyDao,ImageDao imageDao,HttpServletRequest request, TransactionDao transactionDao, Ordersdao ordersDao,CustomerDao customerDao,PhoneNumberDao phonenumberDao, Userdao userDao,AddressDao addressDao, VendorDao vendorDao, ComplaintsDao complaintsDao,RequestDao requestDao) {
		this.productDao = productDao;
		this.categoryDao = categroyDao;
		this.imageDao = imageDao;
		this.request = request;
		this.transactionDao = transactionDao;
		this.ordersDao = ordersDao;
		this.customerDao = customerDao;
		this.phoneNumberDao = phonenumberDao;
		this.userDao = userDao;
		this.addressDao = addressDao;
		this.vendorDao = vendorDao;
		this.requestDao = requestDao;
		this.complaintsDao = complaintsDao;
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
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/AllProducts")
	public String inventory(ModelMap model) {
		List<Product> p=productDao.showAllProducts(true);
		System.out.println(p);
		model.addAttribute("prods",p);
		return "AllProducts";
	}
	
	@GetMapping("/viewProducts/{category_id}")
	public String viewProductByCategory(@PathVariable("category_id") int category_id,ModelMap model) {
		List<Product> p=productDao.showAllProducts(category_id);
		model.addAttribute("prods",p);
		return "AllProducts";
	}
	@GetMapping("/editProduct/{product_id}")
	public String editProductPage(ModelMap model,@PathVariable("product_id") int product_id) {
		Product p = productDao.getproductbyId(product_id);
		model.addAttribute("prod", p);
		model.addAttribute("categories", getAllCategories());

		System.out.println(p.image_path);
		return "editProduct";
	}
	
	@GetMapping("/chooseVendor/{product_id}")
	public String chooseVendorPage(ModelMap model,@PathVariable("product_id") int product_id) {
		model.addAttribute("vendors",vendorDao.getActiveVendors());
		return "chooseVendor";
	}
	@GetMapping("/chooseVendor/{product_id}/{supplier_id}")
	public String chooseVendor(ModelMap model,@PathVariable("product_id") int product_id,@PathVariable("supplier_id") int supplier_id) {
		productDao.setVendor(product_id, supplier_id);
		return "redirect:/admin/AllProducts";
	}
	
	@PostMapping("/editProduct/{product_id}")
	public String editProduct(@PathVariable("product_id") int product_id, Product p,MultipartFile file) {
		productDao.updateproduct(product_id,p);
		if(file!=null) {
			imageDao.save(file, request,product_id);
		}
		return "redirect:/admin/AllProducts";
	}
	
	@PostMapping("/deleteImage")
	@ResponseBody
	public Message deleteImage(int product_id,String image_path) {
		imageDao.deleteImage(product_id, image_path);
		return new Message(true, "Image Deleted");
	}
	
	
	@PostMapping("/hideProduct")
	@ResponseBody
	public Message hideProduct(@RequestParam("product_id")int product_id) {
		productDao.hideShow(product_id);
		return new Message(true, "Updated");
	}
	
	
	
	@GetMapping("/addProduct")
	public String addProductPage(ModelMap model) {
		model.addAttribute("prod", new Product());
		model.addAttribute("categories", getAllCategories());
		return "addProduct";
	}
	
	
	
	@PostMapping("/addProduct")
	public String addProduct(ModelMap model,@Valid @ModelAttribute("prod") Product prod,BindingResult result, MultipartFile file) {
		if(result.hasErrors()) {
			return "addProduct";
		}
		int product_id = productDao.save(prod.getName(), prod.getDescription(), prod.getPrice(), prod.getCategory_id());
		imageDao.save(file, request, product_id);
		return "redirect:/admin";
	}
	
	
	@GetMapping("/getAllCategories")
	@ResponseBody
	public List<Category> getAllCategories() {
		return categoryDao.showAllCategory();
	}
	
	@GetMapping("/AllCategories")
	public String AllCategoriesPage(ModelMap model) {
			model.addAttribute("cat", getAllCategories());
			return "allCategories";
	}
	
	@GetMapping("/editCategory/{category_id}")
//	@ResponseBody
	public String editCategoryPage(ModelMap model,@PathVariable("category_id") int category_id) {
		Category cat = categoryDao.getCategoyById(category_id);
		model.addAttribute("cat", cat);
		return "editCategory";
	}
	@PostMapping("/editCategory/{category_id}")
	public String editCategory(ModelMap model, @PathVariable("category_id") int category_id,@Valid @ModelAttribute("cat") Category cat, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute(cat);
			model.addAttribute(result);
			return "editCategory";
		}
		categoryDao.updateCategoryName(category_id, cat.getCategory_name());
		return "redirect:/admin/AllCategories";
	}
//	
	@GetMapping("/addCategory")
	public String addCategoryPage(ModelMap model) {
		return "addCategory";
	}
	
	
	@PostMapping("/addCategory")
	public String addCategory(ModelMap model, String category_name) {
		categoryDao.addCategory(category_name);
		return "redirect:/";
	}
	
	@GetMapping("/Orders")
	public String ordersPage(ModelMap model) {
		List<Orders> ord = ordersDao.getAllOrders(); 		
		model.addAttribute("orders", ord);
		return "allOrders";
	}
	
	@GetMapping("/Orders/{customer_id}")
	public String customerOrders(ModelMap model,@PathVariable("customer_id") int customer_id) {
		model.addAttribute("orders", ordersDao.getOrderByCustomer_id(customer_id));
		return "allOrders";
	}
	
	@GetMapping("/viewOrder/{order_id}")
	public String getOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		Orders o = ordersDao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "orderDetails";
	}
	
	@GetMapping("/viewOrder/cancelOrder/{order_id}")
	public String cancelOrder(@PathVariable("order_id") int order_id,ModelMap model) {
		ordersDao.updateorder(order_id,"CANCELLED");
		Orders o = ordersDao.getorderbyId(order_id);
		model.addAttribute("order",o);
		return "redirect:/admin/viewOrder/"+order_id;
	}
	
	@GetMapping("/viewOrder/refundOrder/{order_id}")
	public String refundOrder(@PathVariable("order_id") int order_id, ModelMap model){
		ordersDao.updateorder(order_id, "REFUNDED");
		return "redirect:/admin/viewOrder/" + order_id;
	}
	
	@GetMapping("/Transactions")
	public String transactionsPage(ModelMap model) {
		model.addAttribute("transactions", transactionDao.getAllTransactions());
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
	public String editProfile(ModelMap model, @PathVariable("customer_id") int customer_id,@Valid @ModelAttribute("customer") Customer customer,BindingResult result) {
		System.out.println(result.getAllErrors());
		if(result.hasErrors()) {
			return "editProfile";
		}
		customer.setCustomer_id(customer_id);
//		System.out.println(customer.getUser());
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
		userDao.addPhoneNumber(phone_number, customer_id);
		return new Message(true,"Phone number added");
	}
	
	@GetMapping("/viewAllAddresses/{customer_id}/")
	public String viewAllAddresses(@PathVariable("customer_id") int customer_id,ModelMap model) {
		  List<Address> addresses = addressDao.getAddressesByCustomerId(customer_id);
	        model.addAttribute("addresses",addresses);
	        return "viewAddresses";
	}
	@GetMapping("/viewAllAddresses/{customer_id}/deleteAddress/{address_id}")
	public String deleteAddress(@PathVariable("address_id") int address_id,@PathVariable("customer_id") int customer_id) {
	    addressDao.deleteAddress(address_id,customer_id);
		return "redirect:/admin";
	}
	
	@GetMapping("/viewAllAddresses/{customer_id}/addAddress")
	public String addAddressPage(@PathVariable("customer_id") int customer_id) {
		return "addAddress";
	}

	
	@PostMapping("/viewAllAddresses/{customer_id}/addAddress")
	public String addAddress(ModelMap model,@PathVariable("customer_id") int customer_id,String house_no,String street_no,String locality_and_city,String pincode,String state) {
		try {
		Address add= new Address(customer_id, house_no, street_no, locality_and_city, pincode, state);
		addressDao.addAddress(add);
		} catch (Exception e) {
			model.addAttribute("error", "Please Fill All Values");
			return "addAddress";
			// TODO: handle exception
		}
		return "redirect:/admin";
	}
	
	@GetMapping("/allVendors")
	public String allVendors(ModelMap model) {
		model.addAttribute("vendors",vendorDao.getAllVendors());
		return "allVendors";
	}
	
	@GetMapping("/editVendorProfile/{supplier_id}")
	public String editVendorProfilePage(ModelMap model,@PathVariable("supplier_id") int supplier_id) {
		Vendor v = vendorDao.getVendorBySupplier_Id(supplier_id);
		model.addAttribute("vendor", v);
		return "editVendorProfile";
	}
	@PostMapping("/editVendorProfile/{supplier_id}")
	public String editVendorProfile(ModelMap model, @PathVariable("supplier_id") int supplier_id,Vendor v) {
		v.setSupplier_id(supplier_id);
		vendorDao.update(v);
		return "redirect:/admin/allVendors";
	}
	
	@GetMapping("/productsByVendor/{supplier_id}")
	public String myProducts(ModelMap model,@PathVariable("supplier_id") int supplier_id) {
		List<Product> prods = vendorDao.getMyProducts(supplier_id);
		model.addAttribute("prods", prods);
		return "myProductForVendor";
	}
	
	
	@GetMapping("/allComplaints")
	public String allComplaints(ModelMap model) {
		model.addAttribute("complaints", complaintsDao.getAllComplaints());
		return "complaintsPage";
	}
	
	@GetMapping("/complaints/{user_id}")
	public String complaints(ModelMap model,@PathVariable("user_id") int user_id) {
		model.addAttribute("complaints", complaintsDao.getComplaintsByUserId(user_id));
		return "complaintsPage";
	}
	
	@GetMapping("/resolve/{complaint_id}")
	public String resolveComplaint(ModelMap model,@PathVariable("complaint_id") int complaint_id) {
		complaintsDao.resolve(complaint_id);
		return "redirect:/admin/allComplaints";
	}
	
	@GetMapping("/findCustomer")
	public String findCustomerPage() {
		return "findCustomer";
	}
	@GetMapping("/findCustomerById")
	public String findCustomerById(ModelMap model,@RequestParam("user_id") int user_id) {
		try {
			Customer c = customerDao.getCustomerByCustomerId(user_id);
			List<Customer> temp = new ArrayList<>();
			if(c!=null)
				temp.add(c);
			model.addAttribute("customers",temp);
			return "allCustomers";
		} catch(Exception e) {
			return "allCustomers";
		}
	}
	
	@GetMapping("/findCustomerByEmail")
	public String findCustomerByEmail(ModelMap model,@RequestParam("email") String email) {
		List<Customer> c= customerDao.getCustomerByCustomerEmail(email);
			model.addAttribute("customers",c);
			return "allCustomers";
		
	}
	
	@GetMapping("/findCustomerByUsername")
	public String findCustomerByUsername(ModelMap model,@RequestParam("username") String username) {
		List<Customer> c= customerDao.getCustomerByUsername(username);
			model.addAttribute("customers",c);
			return "allCustomers";
		
	}
	
	@GetMapping("/findVendors")
	public String findVendorsPage() {
		return "findVendor";
	}
	
	@GetMapping("/findVendorById")
    public String findVendorById(ModelMap model,@RequestParam("user_id") int user_id) {
        try {
            Vendor c = vendorDao.getVendorBySupplier_Id(user_id);
            List<Vendor> temp = new ArrayList<>();
            if(c!=null)
                temp.add(c);
            model.addAttribute("vendors",temp);
            return "allVendors";
        } catch(Exception e) {
            return "allVendors";
        }
    }
    
    @GetMapping("/findVendorByEmail")
    public String findVendorByEmail(ModelMap model,@RequestParam("email") String email) {
        List<Vendor> c= vendorDao.getVendorByEmail(email);
            model.addAttribute("vendors",c);
            return "allVendors";
        
    }
    
    @GetMapping("/findVendorByUsername")
    public String findVendorByUsername(ModelMap model,@RequestParam("username") String username) {
        List<Vendor> c= vendorDao.getVendorByUsername(username);
            model.addAttribute("vendors",c);
            return "allVendors";
        
    }
    
    @GetMapping("/allRequests")
	public String allRequests(ModelMap model) {
    	List<Request> requests = requestDao.getAllRequests();
    	model.addAttribute("requests",requests);
		return "allRequests";
	}
    
    @PostMapping("/deleteRequest")
    @ResponseBody
    public Message deleteRequest(ModelMap model,@RequestParam("request_id") int request_id) {
    	Message m = new Message();
    	try {
    		requestDao.deleteRequest(request_id);
    		m.setStatus(true);
    		m.setMessage("Request Successfully Deleted");
    	} catch (Exception e) {
    		m.setStatus(false);
    		m.setMessage(e.getMessage());
		}
    	return m;
    }
    
    @GetMapping("/findRequests")
	public String findRequestsPage() {
		return "findRequest";
	}
    
    @GetMapping("/findRequestByEmailId")
    public String findRequestByEmailId(ModelMap model,@RequestParam("email_id")String email) {
    	List<Request> requests = requestDao.findRequestByEmailId(email);
    	model.addAttribute("requests", requests);
    	return "allRequests";
    }
    @GetMapping("/findRequestByRequestId")
    public String findRequestByRequestId(ModelMap model,@RequestParam("request_id") int request_id) {
    	List<Request> requests = requestDao.findRequestById(request_id);
    	System.out.println(requests);
    	model.addAttribute("requests", requests);

    	return "allRequests";
    }
    
    @GetMapping("/temp")
    public String temp() {
    	return "temp";
    }
    
    
    
    
	
	
	
	

}
