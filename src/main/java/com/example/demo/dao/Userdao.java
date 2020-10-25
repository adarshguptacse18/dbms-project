package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public User findByUsername(String username) throws UsernameNotFoundException{
        String sql = "select * from user where username='" + username + "'";
        if(jt.queryForList(sql).isEmpty()) {
        	 throw new UsernameNotFoundException(username);
        }
        return jt.queryForObject(sql, new RowMapper<User>() {
            public User mapRow(ResultSet row, int rowNum) throws SQLException {
                User u = new User();
                u.setId(row.getInt("id"));
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
    
}