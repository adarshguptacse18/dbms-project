package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;

@Transactional
@Repository
public class Userdao {

    @Autowired
    JdbcTemplate jt;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public void save(String username, String password, String name, String contact, String email, String house,
            String street, String city, String bank) {
        String sql = "insert into user (username, password,name,contact,email,house_no,street_name,city,account_no,role) values (?,?,?,?,?,?,?,?,?,?)";
        jt.update(sql, username, password, name, contact, email, house, street, city, bank, "customer");
    }
    public void save(String username, String password,String role) {
    	password = bCryptPasswordEncoder.encode(password);
    	String sql="insert into user (username, password, role) values(?,?,?)";
    	jt.update(sql,username,password,role);
    }
    public int save(User u) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		String sql = "insert into user (username,role,password,email,first_name,last_name) values (?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
		  jt.update(new PreparedStatementCreator() {

	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, u.getUsername());
	                ps.setString(2, u.getRole());
	                ps.setString(3, u.getPassword());
	                ps.setString(4, u.getEmail());
	                ps.setString(5, u.getFirst_name());
	                ps.setString(6, u.getLast_name());
	                return ps;
	                
	            }
	        }, holder);
		  int user_id = holder.getKey().intValue();
		  return user_id;
	}
//    public void save(User user) {
//    	String password = bCryptPasswordEncoder.encode(user.getPassword());
//    	user.setPassword(password);
//    	user.setRole("ROLE_USER");
//    	jt.update("insert into user (username,role,password,email,first_name,last_name) value(?,?,?,?,?,?)",user.getUsername(),user.getRole(),user.getPassword(),user.getEmail(),user.getFirst_name(),user.getLast_name());
//    }
    public User findByUsername(String username) throws UsernameNotFoundException{
        String sql = "select * from user where username='" + username + "'";
        if(jt.queryForList(sql).isEmpty()) {
        	 throw new UsernameNotFoundException(username);
        }
        User u =(User) jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class));
//        if(u==null) {
//        	throw new UsernameNotFoundException("Username not found");
//        }
        return u;
//        return jt.queryForObject(sql, new RowMapper<User>() {
//            public User mapRow(ResultSet row, int rowNum) throws SQLException {
//                User u = new User();
//                u.setId(row.getInt("user_id"));
//                u.setUsername(row.getString("username"));
//                u.setPassword(row.getString("password"));
////                u.setName(row.getString("name"));
////                u.setContact(row.getString("contact"));
////                u.setEmail(row.getString("email"));
////                u.setHouse_no(row.getString("house_no"));
////                u.setStreet_name(row.getString("street_name"));
////                u.setCity(row.getString("city"));
////                u.setAccount_no(row.getString("account_no"));
//                u.setRole(row.getString("role"));
//                return u;
//            }
//        });
    }
    public User findUserByUserId(int user_id) throws UsernameNotFoundException{
        String sql = "select * from user where user_id='" + user_id + "'";
        if(jt.queryForList(sql).isEmpty()) {
        	 throw new UsernameNotFoundException("user_id");
        }
        User u =(User) jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class));
        return u;
    }

    public User findUserByEmail(String email) {
        String sql = "select * from user where email='" + email + "'";
        if(jt.queryForList(sql).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        User u =(User) jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class));
        return u;
    }


    public List<User> getAllusers() {
        String sql = "select * from user";
        return jt.query(sql, new RowMapper<User>() {

            public User mapRow(ResultSet row, int rowNum) throws SQLException {
                User u = new User();
                u.setUsername(row.getString("username"));
                u.setPassword(row.getString("password"));
//                u.setName(row.getString("name"));
//                u.setContact(row.getString("contact"));
//                u.setEmail(row.getString("email"));
//                u.setHouse_no(row.getString("house_no"));
//                u.setStreet_name(row.getString("street_name"));
//                u.setCity(row.getString("city"));
//                u.setAccount_no(row.getString("account_no"));
                u.setRole(row.getString("role"));
                return u;
            }
        });
    }

    public boolean userExist(String username) {
        String sql = "select count(*) from user where username=?";
        int p = jt.queryForObject(sql, Integer.class, username);
        if (p == 0)
            return false;
        else
            return true;
    }
    public void disableEnableUser(int user_id) {
		jt.update("update user set is_enabled = !is_enabled where user_id =?",user_id);
	}

    public void addPhoneNumber(String phone_number, int customer_id) {
		jt.update("insert into phone_numbers value (?,?)",customer_id,phone_number);
	}	
    
    public void updatePassword(int user_id,String password) {
    	String encrypted = bCryptPasswordEncoder.encode(password);
    	String sql = "update user set password = ? where user_id = ? and is_enabled=true";
//    	System.out.println(z		);
    	jt.update(sql,encrypted,user_id);
    }
}