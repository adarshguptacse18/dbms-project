package com.example.demo.dao;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class CustomerDao {
	@Autowired
    JdbcTemplate jt;
	
	public void save(int customer_id, int gstin_number) {
		String sql="insert into customer (customer_id,gstin_number,trust_value) values (?,?,?)";
		jt.update(sql,customer_id,gstin_number,1);
    }
	public void updateproduct(int customer_id, int gstin_number) {
        String sql = "update customer gstin_number=? where customer_id=?";
        jt.update(sql, customer_id,gstin_number);
    }
	public void updateTrustValue(int customer_id,double delta_trust) {
		String sql = "update customer trust_value=trust_value + ? where customer_id=?";
        jt.update(sql, customer_id,delta_trust);
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
}
