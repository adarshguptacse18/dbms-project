package com.example.demo.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Address;

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
	public List<Address> getAddressesByCustomerId(int customer_id){
		String sql = "select * from address where customer_id="+customer_id;
		 return jt.query(sql, new RowMapper<Address>() {
	            public Address mapRow(ResultSet row, int rowNum) throws SQLException {
	            	Address a=new Address();
	            	a.setAddress_id(row.getInt("address_id"));
	            	a.setCustomer_id(customer_id);
	            	a.setHouse_no(row.getString("house_no"));
	            	a.setStreet_no(row.getString("street_no"));
	            	a.setLocality_and_city(row.getString("locality_and_city"));
	            	a.setPincode(row.getString("pincode"));
	            	a.setState(row.getString("state"));
	                 return a;
	            }
	        });
	}
	public void UpdateAddress(Address add) {
		String sql = "update address set " + add.toString();
		System.out.println(sql);
		jt.update(sql);
	}
	
	public void addAddress(Address add) {
		String sql = "insert into address (customer_id,house_no,street_no,locality_and_city, pincode,state) value (?,?,?,?,?,?)";
		jt.update(sql,add.getCustomer_id(),add.getHouse_no(),add.getStreet_no(),add.getLocality_and_city(),add.getPincode(),add.getState());
	}
	
	
	public Address getAddressById(int address_id) {
		String sql = "select * from address where address_id="+address_id;
        return jt.queryForObject(sql, new RowMapper<Address>() {
            public Address mapRow(ResultSet row, int rowNum) throws SQLException {
            	Address a=new Address();
            	a.setAddress_id(row.getInt("address_id"));
            	a.setCustomer_id(row.getInt("customer_id"));
            	a.setHouse_no(row.getString("house_no"));
            	a.setStreet_no(row.getString("street_no"));
            	a.setLocality_and_city(row.getString("locality_and_city"));
            	a.setPincode(row.getString("pincode"));
            	a.setState(row.getString("state"));
                return a;
            }
        });
    }
	
}
