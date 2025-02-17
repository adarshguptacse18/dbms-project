package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Address;

@Repository
public class AddressDao {
	
	
	@Autowired
    JdbcTemplate jt;

	
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
	
	public void deleteAddress(int address_id,int customer_id) {
		jt.update("delete from address where address_id = ? and customer_id=?",address_id,customer_id);
	}
}
