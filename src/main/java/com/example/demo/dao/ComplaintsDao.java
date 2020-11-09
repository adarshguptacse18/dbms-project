package com.example.demo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Complaints;


@Repository
public class ComplaintsDao {
    
    
    final JdbcTemplate jt;
    
    @Autowired
    public ComplaintsDao(JdbcTemplate jt){
        this.jt = jt;
    }
    
    public void save(Complaints r) {
        String sql = "insert into complaints (user_id,email_id,date,message) value (?,?,?,?)";
        Date dt = new Date();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfd.format(dt);
        jt.update(sql,r.getUser_id(),r.getEmail_id(), currentDate,r.getMessage());
    }
    
    
    public List<Complaints> getAllComplaints() {
        String sql = "select * from complaints";
        return jt.query(sql, new BeanPropertyRowMapper<>(Complaints.class));
        
    }
    
    public List<Complaints> getComplaintsByUserId(int user_id) {
        String sql = "select * from complaints where user_id = "+user_id;
        return jt.query(sql, new BeanPropertyRowMapper<>(Complaints.class));
    }

	public void resolve(int complaint_id) {
		String sql = "update complaints set is_resolved = 1 where complaint_id = ?";
		jt.update(sql,complaint_id);
	}
   
    
   
    
    
}
