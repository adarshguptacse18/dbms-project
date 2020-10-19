package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class CartDao {
	@Autowired
    JdbcTemplate jt;
	
	public void save(int cart_id,int product_id,int quantity) {
		if(quantity==0) return;
		try {
			String sql="insert into cart (cart_id,product_id,quantity) values (?,?,?)";
			jt.update(sql,cart_id,product_id,quantity);
		} catch(Exception e) {
			update(cart_id,product_id,quantity);
		}
    }
	
	public void update(int cart_id,int product_id,int quantity) {
		String sql = "update cart quantity = quantity + ? where cart_id=? and product_id=?";
        jt.update(sql, quantity,cart_id,product_id);
        delete_zero_quantity();
	}
	
	public void delete_zero_quantity() {
		String sql = "delete from cart where quantity = 0";
		jt.update(sql);
	}
}
