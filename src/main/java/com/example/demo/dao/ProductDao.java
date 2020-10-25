package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Category;
import com.example.demo.models.Image;
import com.example.demo.models.Product;
import com.example.demo.models.Review;

@Transactional
@Repository
public class ProductDao {
	@Autowired
    JdbcTemplate jt;
	
	public int save(String name, String desc, int price, int category_id) {
		String sql="insert into product (name,description,price,category_id) values (?,?,?,?)";
//		jt.update(sql,name,desc,price,category_id);
        KeyHolder holder = new GeneratedKeyHolder();

		  jt.update(new PreparedStatementCreator() {

	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, name);
	                ps.setString(2, desc);
	                ps.setInt(3, price);
	                ps.setInt(4,category_id);
	                return ps;
	            }
	        }, holder);

	        int newProductId = holder.getKey().intValue();
	        return newProductId;

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
                u.setQuantity(row.getInt("quantity"));
                u.setRating(row.getDouble("rating"));
                u.setPurchased_cnt(row.getInt("purchased_cnt"));
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

    public void setProductquantity(int id, int quantity) {
        String sql = "update product set quantity=? where product_id=?";
        jt.update(sql, quantity, id);
    }
    public void updateProductquantity(int id, int quantity) {
        String sql = "update product set quantity = quantity + ? where product_id=?";
        jt.update(sql, quantity, id);
    }
    
    public void deleteproduct(int id) {
        String sql = "delete from product where product_id=?";
        jt.update(sql, id);
    }
    public void updateProductRating(int old_rating,int new_rating,int product_id) {
    	Product p = getproductbyId(product_id);
    	double updated_rating = p.getRating() * p.getPurchased_cnt() -old_rating + new_rating;
    	if(old_rating == 0) {
    		p.setPurchased_cnt(p.getPurchased_cnt()+1);
    	}
    	updated_rating /= p.getPurchased_cnt();
    	jt.update("update product set rating = ? , purchased_cnt = ? where product_id = ?",updated_rating,p.getPurchased_cnt(),product_id);               
     }
    
    public void addCategory(String name) {
    	jt.update("insert into category (category_name) value (?) ",name );
    }
    public List<Category> showAllCategory() {
//    	List<Category> category = jt.queryForList("select * from category",new BeanPropertyRowMapper<Category>(Category.class)) 
    	List<Category> r= jt.query("select * from category",new BeanPropertyRowMapper<Category>(Category.class));
    	return r;
    }
    
    public void saveReview(Review r) {
    	String sql = "insert into review (product_id,customer_id,message) value (?,?,?)";
    	jt.update(sql,r.getProduct_id(),r.getCustomer_id(),r.getMessage());
    }
    public void updateReview(Review r) {
    	String sql = "update review set message = ? where customer_id = ? and product_id = ?";
    	jt.update(sql,r.getMessage(),r.getCustomer_id(),r.getProduct_id());
    }
    public List<Review> getReviewsByProductId(int product_id){
		List<Review> res = jt.query("select * from review where product_id = " + product_id ,new BeanPropertyRowMapper<Review>(Review.class));
		return res;
    }
}
