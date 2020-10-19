package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

@Transactional
@Repository
public class ProductDao {
	@Autowired
    JdbcTemplate jt;
	
	public void save(String name, String desc, int price, int category_id) {
		String sql="insert into product (name,description,price,category_id) values (?,?,?,?)";
		jt.update(sql,name,desc,price,category_id);
    }
	public Product getproductbyId(int id) {
        String sql = "select * from product where product_id='" + id + "'";
        return jt.queryForObject(sql, new RowMapper<Product>() {

            public Product mapRow(ResultSet row, int rowNum) throws SQLException {
                Product u = new Product();
                u.setProduct_id(row.getInt("product_id"));
                u.setName(row.getString("name"));
                u.setCategory_id(row.getInt("category_id"));
                u.setDescription(row.getString("description"));
                u.setPrice(row.getInt("price"));
                return u;
            }
        });
    }
	
	 public List<Product> showAllProducts() {
	        String sql = "select * from product";
	        return jt.query(sql, new RowMapper<Product>() {

	            public Product mapRow(ResultSet row, int rowNum) throws SQLException {
	                 Product u = new Product();
	            	 u.setProduct_id(row.getInt("product_id"));
	                 u.setName(row.getString("name"));
	                 u.setCategory_id(row.getInt("category_id"));
	                 u.setDescription(row.getString("description"));
	                 u.setPrice(row.getInt("price"));
	                 return u;
	            }
	        });
	    }
	 public List<Product> showAllProducts(int category) {
	        String sql = "select * from product where category_id=" + category;
	        return jt.query(sql, new RowMapper<Product>() {

	            public Product mapRow(ResultSet row, int rowNum) throws SQLException {
	                 Product u = new Product();
	            	 u.setProduct_id(row.getInt("product_id"));
	                 u.setName(row.getString("name"));
	                 u.setCategory_id(row.getInt("category_id"));
	                 u.setDescription(row.getString("description"));
	                 u.setPrice(row.getInt("price"));
	                 return u;
	            }
	        });
	    }
	public void updateproduct(int id, String name, int price, int quantity, int category_id) {
        String sql = "update product set name=?,price=?,quantity=?,category_id=? where product_id=?";
        jt.update(sql, name, price, quantity, category_id, id);
    }

    public void updateProductquantity(int id, int quantity) {
        String sql = "update product set quantity=? where product_id=?";
        jt.update(sql, quantity, id);
    }

    public void deleteproduct(int id) {
        String sql = "delete from product where product_id=?";
        jt.update(sql, id);
    }
   

}
