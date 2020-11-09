package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

		http.authorizeRequests()
			.antMatchers("/vendor/register","/vendor/login").permitAll()
			.antMatchers("/admin","/admin/**").hasRole("ADMIN")
			.antMatchers("/user","/addToCart","/addPhoneNumber","/addComplaints","/viewComplaints").hasAnyRole("ADMIN","USER","VENDOR")
			.antMatchers("/vendor").hasAnyRole("ADMIN","VENDOR")
			.antMatchers("/").permitAll()
			.and()
			.formLogin()
			.loginPage("/login").defaultSuccessUrl("/", true);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
