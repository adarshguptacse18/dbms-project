package com.example.demo.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Customer;
import com.example.demo.models.User;

@Transactional
@Repository
public class CustomerDao {
	@Autowired
    JdbcTemplate jt;
	
	public void save(int customer_id, int gstin_number) {
		String sql="insert into customer (customer_id,gstin_number,trust_value) values (?,?,?)";
		jt.update(sql,customer_id,gstin_number,1);
    }
	public void updateGSTINNumber(int customer_id, int gstin_number) {
        String sql = "update customer gstin_number=? where customer_id=?";
        jt.update(sql, customer_id,gstin_number);
    }
	public void updateTrustValue(int customer_id,double delta_trust) {
		String sql = "update customer trust_value=trust_value + ? where customer_id=?";
        jt.update(sql, customer_id,delta_trust);
	}
	public Customer getCustomerByCustomerId(int id) {
		System.out.println(id);
		Customer c= jt.queryForObject("select GSTIN_NUMBER, customer_id,group_concat(number) as numbers  from customer LEFT JOIN phone_numbers on customer_id = user_id where customer_id = ? group by customer.customer_id ;", new Object[]{id}, new BeanPropertyRowMapper<Customer>(Customer.class));
		User u = jt.queryForObject("select * from user where user_id = "+id, new BeanPropertyRowMapper<User>(User.class));
		c.setUser(u);
		return c;
	}
	
	
	
	
	public int getCartId(int customer_id) {
//		return 1;
//		System.out.println(customer_id);
		String sql= "select cart_id from customer where customer_id = " + customer_id;
//		System.out.println(jt.queryForObject(sql, Integer.class));
		int p =jt.queryForObject(sql, Integer.class);
//		return 1;
		return p;
	}
	
	public void update(Customer customer) {
		jt.update("update customer set GSTIN_NUMBER=? where customer_id = ?",customer.getGSTIN_NUMBER(),customer.getCustomer_id());
		jt.update("update user set username=?,first_name=?,last_name=?,email=? where user.user_id=?",customer.getUser().getUsername(),customer.getUser().getFirst_name(),customer.getUser().getLast_name(),customer.getUser().getEmail(),customer.getCustomer_id());
	}
	public List<Customer> getAllCustomers() {
		String sql = "select * from customer as C INNER JOIN user as U on C.customer_id = U.user_id";
		return jt.query(sql,new RowMapper<Customer>() {
			public Customer mapRow(ResultSet row,int rowNum) throws SQLException{
				Customer c = (new BeanPropertyRowMapper<>(Customer.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
//			
				c.setUser(u);
				return c;
			};
		});
		// TODO Auto-generated method stub
	}
	public List<Customer> getCustomerByCustomerEmail(String email) {
		String sql = "select * from customer as C INNER JOIN user as U on C.customer_id = U.user_id where U.email>= ?";
		return jt.query(sql, new Object[] {email}, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet row,int rowNum) throws SQLException{
				Customer c = (new BeanPropertyRowMapper<>(Customer.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
//			
				c.setUser(u);
				return c;
			}
		});
	}
	public List<Customer> getCustomerByUsername(String username) {
		String sql = "select * from customer as C INNER JOIN user as U on C.customer_id = U.user_id where U.username>= ?";
		return jt.query(sql, new Object[] {username}, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet row,int rowNum) throws SQLException{
				Customer c = (new BeanPropertyRowMapper<>(Customer.class)).mapRow(row, rowNum);
				User u = (new BeanPropertyRowMapper<>(User.class)).mapRow(row, rowNum);
//			
				c.setUser(u);
				return c;
			}
		});	
	}
	
	
	
}
