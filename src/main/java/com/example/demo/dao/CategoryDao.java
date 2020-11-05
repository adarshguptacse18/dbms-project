package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Category;

@Repository
public class CategoryDao {
	
	@Autowired 
	JdbcTemplate jt;
	
	public void addCategory(String name) {
    	jt.update("insert into category (category_name) value (?) ",name );
    }
	
	 public List<Category> showAllCategory() {
//	    	List<Category> category = jt.queryForList("select * from category",new BeanPropertyRowMapper<Category>(Category.class)) 
	    	List<Category> r= jt.query("select * from category",new BeanPropertyRowMapper<Category>(Category.class));
	    	return r;
	    }
	 public Category getCategoyById(int id) {
		 Category cat = jt.queryForObject("select *  from category where category_id = " + id, new BeanPropertyRowMapper<Category>(Category.class));
		 return cat;
	 }
	 
	 public void updateCategoryName(int id,String name) {
		 jt.update("update category set category_name=? where category_id = ?",name,id);
		 
	 }
	
}
