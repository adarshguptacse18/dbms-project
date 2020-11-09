package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Customer;
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
	
	
	public List<Vendor> getAllVendors(){
		String sql = "select * from vendor as V INNER JOIN user as U on V.supplier_id = U.user_id";
		return jt.query(sql,new RowMapper<Vendor>() {
			public Vendor mapRow(ResultSet row,int rowNum) throws SQLException{
				Vendor c = (new BeanPropertyRowMapper<>(Vendor.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
				c.setUser(u);
				return c;
			};
		});
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
		return jt.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public Vendor getVendorBySupplier_Id(int supplier_id) {
		String sql = "select * from vendor as V INNER JOIN user as U on V.supplier_id = U.user_id and V.supplier_id = "+supplier_id;	
		return jt.queryForObject(sql,new RowMapper<Vendor>() {
			public Vendor mapRow(ResultSet row,int rowNum) throws SQLException{
				Vendor c = (new BeanPropertyRowMapper<>(Vendor.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
				c.setUser(u);
				return c;
			};
		});
	}

	public void update(Vendor v) {
		jt.update("update vendor set company_name=? , pancard_no = ? where supplier_id = ?",v.getCompany_name(),v.getPancard_no(),v.getSupplier_id());;
		jt.update("update user set username=?,first_name=?,last_name=?,email=? where user.user_id=?",v.getUser().getUsername(),v.getUser().getFirst_name(),v.getUser().getLast_name(),v.getUser().getEmail(),v.getSupplier_id());
	}

	public List<Vendor> getVendorByEmail(String email) {
		String sql = "select * from vendor as V INNER JOIN user as U on V.supplier_id = U.user_id and U.email>= ?";	
		return jt.query(sql,new Object[] {email},new RowMapper<Vendor>() {
			public Vendor mapRow(ResultSet row,int rowNum) throws SQLException{
				Vendor c = (new BeanPropertyRowMapper<>(Vendor.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
				c.setUser(u);
				return c;
			};
		});
	}

	public List<Vendor> getVendorByUsername(String username) {
		String sql = "select * from vendor as V INNER JOIN user as U on V.supplier_id = U.user_id and U.username>=?";	
		return jt.query(sql,new Object[] {username}, new RowMapper<Vendor>() {
			public Vendor mapRow(ResultSet row,int rowNum) throws SQLException{
				Vendor c = (new BeanPropertyRowMapper<>(Vendor.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
				c.setUser(u);
				return c;
			};
		});		
	}
	
	
}
