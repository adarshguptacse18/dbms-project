package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserToken;


@Transactional
@Repository
public class UserTokenDao {
	
	final JdbcTemplate jt;
	
	@Autowired
	public UserTokenDao(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	public void save(UserToken userToken) {
		String sql = "INSERT INTO user_token (user_id,token) value (?,?)";
		jt.update(sql,userToken.getUser_id(),userToken.getToken());
	}
	
	public Integer getUserIdByToken(String token) {
		 try {
	            String sql = "SELECT user_id FROM user_token WHERE token = ?";
	            int userId = jt.queryForObject(sql, new Object[] { token }, Integer.class);
	            return userId;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	}
    public String getTokenByUserId(int userId) {
        try {
            String sql = "SELECT token FROM user_token WHERE user_id = ?";
            String token = jt.queryForObject(sql, new Object[] { userId }, String.class);
            return token;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public void update(UserToken userToken) {
        String sql = "UPDATE user_token SET token = ? WHERE user_id = ?";
        jt.update(sql, userToken.getToken(), userToken.getUser_id());
    }
    
    public void delete(int user_id) {
        String sql = "delete from user_token WHERE user_id = ?";
        jt.update(sql, user_id);
    }
	
}
