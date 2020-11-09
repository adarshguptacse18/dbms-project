package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.models.Vendor;

@Repository
public class VendorDao {	
	
	final private JdbcTemplate jt;
    final private Userdao userDao;
    
	@Autowired
	VendorDao(JdbcTemplate jt,Userdao userDao){
		this.jt = jt;
		this.userDao = userDao;
	}
	
	public void save(Vendor v) {
		User u =  v.getUser();
		u.setRole("ROLE_VENDOR");
		int user_id = userDao.save(u);
		String sql = "insert into vendor (supplier_id,company_name,pancard_no) value (?,?,?)";
		jt.update(sql,user_id,v.getCompany_name(),v.getPancard_no());
	}
	
	
	public void addToSupplied(final int product_id,final int supplier_id) {
		String sql = "insert into supplies (supplier_id,product_id) value (?,?)";
		jt.update(sql,supplier_id,product_id);	
	}
	
	public void deleteFromSupplied(final int product_id,final int supplier_id) {
		String sql = "delete from supplies where product_id = ? and supplier_id = ?";
		jt.update(sql,product_id,supplier_id);
	}
	
	public List<Product> getMyProducts(int supplier_id){
		String sql = "select P.product_id as product_id,P.name as name,P.price as price, P.category_id as category_id ,P.description description ,P.quantity quantity ,P.purchased_cnt purchased_cnt,P.hide hide from product as P right join supplies as S on S.product_id = P.product_id where S.supplier_id = " + supplier_id;
//		String sql = "select P.product_id as product_id from product as P right join supplies as S on S.product_id = P.product_id where S.supplier_id = " + supplier_id;

//		System.out.print(sql);
//		jt.update(sql);
//	   jt.execute(sql);
//		return null;
		return jt.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	
}
