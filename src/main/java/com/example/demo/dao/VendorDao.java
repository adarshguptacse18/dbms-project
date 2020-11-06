package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;
import com.example.demo.models.Vendor;

@Repository
public class VendorDao {	
	
	final private JdbcTemplate jt;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    final private Userdao userDao;
    
	@Autowired
	VendorDao(JdbcTemplate jt,BCryptPasswordEncoder bp,Userdao userDao){
		this.jt = jt;
		this.bCryptPasswordEncoder = bp;
		this.userDao = userDao;
	}
	
	public void save(Vendor v) {
		User u =  v.getUser();
		u.setRole("ROLE_VENDOR");
		int user_id = userDao.save(u);
		String sql = "insert into vendor (supplier_id,company_name,pancard_no) value (?,?,?)";
		jt.update(sql,user_id,v.getCompany_name(),v.getPancard_no());
	}
	
	
}
