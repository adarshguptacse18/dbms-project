package com.example.demo.controller;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.EmailSendService;
import com.example.demo.UserDetailsServiceImpl;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.UserTokenDao;
import com.example.demo.dao.Userdao;
import com.example.demo.models.Customer;
import com.example.demo.models.MyUserDetails;
import com.example.demo.models.User;
import com.example.demo.models.UserToken;

@Transactional
@Controller
public class RegisterController {
	
	final Userdao userDao;
	final CustomerDao customerDao;
	final EmailSendService emailSendService;
	final UserDetailsServiceImpl userService;
	final UserTokenDao userTokenDao;
	@Autowired
	public RegisterController(Userdao userDao,CustomerDao customerDao,EmailSendService emailSendService,UserDetailsServiceImpl userDetailsServiceImpl,UserTokenDao userTokenDao){
		this.userDao = userDao;
		this.customerDao = customerDao;
		this.emailSendService = emailSendService;
		this.userService = userDetailsServiceImpl;
		this.userTokenDao = userTokenDao;
	}
	
	
	protected String getUsername() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(p instanceof MyUserDetails) {
			return ((MyUserDetails) p).getUsername();
		}
		return null;
	}

	public int getUserId() {
		return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser_id();
	}
	
	@GetMapping({"/register"})
	public String userRegister(ModelMap model) {
		if(getUsername()!=null) {
			return "redirect:/";
		}
		model.addAttribute("customer", new Customer());
		return "UserRegister";
	}
	
	@PostMapping("/register")
    public String register(Customer cust, ModelMap model) {
		if(getUsername()!=null) {
			return "redirect:/";
		}
		cust.getUser().setRole("ROLE_USER");
		userDao.save(cust.getUser());
		
		User user= userDao.findByUsername(cust.getUser().getUsername());
		customerDao.save(user.getUser_id(), cust.getGSTIN_NUMBER());
		UserToken u = new UserToken();
		u.setUser_id(user.getUser_id());
//        UUID token = UUID.fromString(u.getUser_id() + user.getUsername()); 
        u.setToken(UUID.randomUUID().toString());
		userTokenDao.save(u);
		user.setToken(u.getToken());
		userService.sendVerificationEmail(user);

        return "redirect:/login?verificationEmailSent";
    }
	
	
	
	@GetMapping("/verify-email")
	public String verfyEmail(@RequestParam("token") String token) {
		if(userTokenDao.getUserIdByToken(token)==null) {
			return "redirect:/login?error";
		}
		int user_id = userTokenDao.getUserIdByToken(token);
		userDao.disableEnableUser(user_id);
		userTokenDao.delete(user_id);
		return "redirect:/login?verified";
		
	}
	
	@GetMapping("/login")
	public String login(ModelMap model,String error,String logout, String verificationEmailSent,String verified) {
		if(error!=null) {
			model.addAttribute("error", "Invalid Credentials");
		}
		if(logout!=null) {
			model.addAttribute("successmessage", "You have been logged out successfully");
		}
		if (verificationEmailSent != null)
	            model.addAttribute("successmessage", "Verification email has been sent on your email address");
		if (verified != null)
            model.addAttribute("successmessage", "Your email has been verified successfully.");
		return "login";
	}
	
}
