package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneNumberDao {	
	@Autowired
	JdbcTemplate jt;
	
	public List<String> getAllPhoneNumbersByCustomerId(int customer_id) {
		List<String> numbers = jt.query("select number from phone_numbers where user_id = "+customer_id,new RowMapper<String>() {
			public String mapRow(ResultSet row,int rowNum) throws SQLException{
				return row.getString("number");
			}
		});
		System.out.println(numbers);
		return numbers;
	}

	public void deletePhoneNumber(int user_id, String phone_number) {
		jt.update("delete from phone_numbers where user_id=? and number=?",user_id,phone_number);
	}
	
	public void addPhoneNumber(String number,int user_id) {
		jt.update("insert into phone_numbers value (?,?)",user_id,number);
	}
	
}
