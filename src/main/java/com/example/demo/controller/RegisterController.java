package com.example.demo.controller;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
		if(getUsername()!=null) return "redirect:/";
		Integer user_id =userTokenDao.getUserIdByToken(token);
		if(user_id==null) {
			return "redirect:/login?error";
		}
		userDao.disableEnableUser(user_id);
		userTokenDao.delete(user_id);
		return "redirect:/login?verified";
		
	}
	
	@GetMapping("/login")
	public String login(ModelMap model,String error,String logout, String verificationEmailSent,String verified,String resetMailSent,String passwordChanged) {
		if(getUsername()!=null) return "redirect:/";
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
		if(resetMailSent!=null) {
			model.addAttribute("successmessage","Reset Link has been sent if the account exists");
		}
		if(passwordChanged!=null)
			model.addAttribute("successmessage", "Your password has been successfully changed");
		return "login";
	}
	
	
	@GetMapping("/forget_password")
    public String forget_passwordPage() {
		if(getUsername()!=null) return "redirect:/";
//        Integer userId = userTokenDao.getUserIdByToken(token);
//        if (userId == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token");
//        }
//        
        return "forgetPassword";
    }
	
	@PostMapping("/forget_password")
    public String forget_password(String email) {
		if(getUsername()!=null) return "redirect:/";
		User user = userDao.findUserByEmail(email);
		UserToken u = new UserToken();
		u.setUser_id(user.getUser_id());
		u.setToken(UUID.randomUUID().toString());
		user.setToken(u.getToken());
		userTokenDao.delete(user.getUser_id());
		userTokenDao.save(u);
		userService.sendPasswordResetEmail(user);
        return "redirect:/login?resetMailSent";
    }

	@GetMapping("/reset-password")
	public String reset_password(ModelMap model,String token) {
		if(getUsername()!=null) return "redirect:/";
		Integer user_id =userTokenDao.getUserIdByToken(token);
		if(user_id==null) {
			return "redirect:/login?error";
		}
		model.addAttribute("submit_url","/reset-password?token="+token);
		return "reset-password";
	}
	
	@PostMapping("/reset-password")
	public String reset_password(@RequestParam("password") String password,
            @RequestParam("confirm_password") String confirmPassword, Model model, String token) {
		if(getUsername()!=null) return "redirect:/";
		Integer user_id =userTokenDao.getUserIdByToken(token);
		if(user_id==null) {
			return "redirect:/login?error";
		}
		if(password==null || password.equals(confirmPassword)==false) {
			model.addAttribute("error", "Password and Confirm Password Must match");
			return "reset-password";
		}
		userDao.updatePassword(user_id, password);
		return "redirect:/login?passwordChanged";
	}
}
