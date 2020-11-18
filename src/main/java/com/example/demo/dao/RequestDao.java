package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Request;

@Transactional	
@Repository
public class RequestDao {

	final private JdbcTemplate jt;
	
	@Autowired
	public RequestDao(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	public void save(Request r) {
		String sql = "insert into request (email_id,name,subject,message) values (?,?,?,?)";
		jt.update(sql,r.getEmail_id(),r.getName(),r.getSubject(),r.getMessage());
	}
	
	public List<Request> getAllRequests() {
		String sql = "select * from request";
		return jt.query(sql,new BeanPropertyRowMapper<Request>(Request.class));
	}	
	
	public List<Request> findRequestById(int request_id) {
		String sql = "select * from request where request_id =" + request_id;
		return jt.query(sql,new BeanPropertyRowMapper<Request>(Request.class));
	}
	
	public List<Request> findRequestByEmailId(String email) {
		String sql = "select * from request where email_id = ?";;
		return jt.query(sql, new Object[] {email}, new BeanPropertyRowMapper<Request>(Request.class));
	}
	
	public void deleteRequest(int request_id) {
		String sql = "delete from request where request_id = ?";
		jt.update(sql,request_id);
	}
	
}
