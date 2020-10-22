package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.Review;

@Transactional
@Repository
public class Ordersdao {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    ProductDao productdao;
    public int save(Orders order) {
        Date dt = new Date();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfd.format(dt);
        String sql = "insert into orders (amount,house_no,street_no,locality_and_city,pincode,state,customer_id,is_gift,order_date,status) values (?,?,?,?,?,?,?,?,?,?)";
        jt.update(sql,order.getAmount(),order.getHouse_no(),order.getStreet_no(),order.getLocality_and_city(),order.getPincode(),order.getState(),order.getCustomer_id(),order.getIs_gift(),currentDate,order.getStatus());
        int order_id = jt.queryForObject("select order_id from orders where customer_id = ? and status = ?",Integer.class,3,"TNI");
        jt.update("update orders set status = ? where order_id = ?","Processing",order_id);
        for(Product p: order.getProds()) {
        	jt.update("insert into order_items value(?,?,?)",order_id,p.getProduct_id(),p.getQuantity());
        }
        return order_id;
    }

    public Orders getorderbyId(int id) {
        String sql = "select * from orders where order_id='" + id + "'";
        return jt.queryForObject(sql, new RowMapper<Orders>() {

            public Orders mapRow(ResultSet row, int rowNum) throws SQLException {
                Orders u = new Orders();
                u.setOrder_id(row.getInt("order_id"));
                u.setStatus(row.getString("status"));
                u.setAmount(row.getInt("amount"));
                u.setCustomer_id(row.getInt("customer_id"));  
                u.setProds(getItemsByOrderId(row.getInt("order_id")));
                return u;
            }
        });
    }

    public List<Orders> getOrderByCustomer_id(int cust_id) {
        String sql = "select * from orders where customer_id='" + cust_id + "'";
        return jt.query(sql, new RowMapper<Orders>() {

            public Orders mapRow(ResultSet row, int rowNum) throws SQLException {
                Orders u = new Orders();
                u.setOrder_id(row.getInt("order_id"));
                u.setStatus(row.getString("status"));
                u.setAmount(row.getInt("amount"));
                u.setCustomer_id(row.getInt("customer_id"));                
                u.setDate(row.getDate("order_date"));
                return u;
            }
        });
    }

    public void updateorder(int order_id,int tr_id) {
        String sql = "update orders set status='placed', transaction_id = ? where order_id=?";
        jt.update(sql, order_id,tr_id);
    }

    public void deleteorder(int order_id) {
        String sql = "delete from orders where order_id=?";
        jt.update(sql, order_id);
    }

    public void check() {
        String sql = "delete from orders where order_id not in (select cid from (select distinct order_item.order_id as cid from order_item natural join orders) as c)";
        jt.update(sql);
    }

    public void createOrderItem(int order_id, int product_id, int quantity) {
        String sql = "insert into order_item (order_id,product_id,quantity) values (?,?,?)";
        jt.update(sql, order_id, product_id, quantity);
    }

    public List<Product> getItemsByOrderId(int order_id) {
        String sql = "select * from order_items where order_id='" + order_id + "'";
        return jt.query(sql, new RowMapper<Product>() {

            public Product mapRow(ResultSet row, int rowNum) throws SQLException {
                Product u = productdao.getproductbyId(row.getInt("product_id"));
                u.setQuantity(row.getInt("quantity"));
                return u;
            }
        });
    }
    
    public void saveReview(Review r) {
    	String sql = "insert into review (product_id,customer_id,message) value (?,?,?)";
    	jt.update(sql,r.getProduct_id(),r.getCustomer_id(),r.getMessage());
    }
    public void updateReview(Review r) {
    	String sql = "update review set message = ? where customer_id = ? and product_id = ?";
    	jt.update(sql,r.getMessage(),r.getCustomer_id(),r.getProduct_id());
    }
    public void updateRating(int order_id,int product_id,int rating) {
    	Review r= jt.queryForObject("select * from order_items where product_id = ? and order_id = ?", new Object[]{product_id,order_id},new BeanPropertyRowMapper<Review>(Review.class));
    	System.out.println(r);
    	jt.update("update order_items set rating = ? where order_id = ? and product_id = ?",rating,order_id,product_id);
    	productdao.updateProductRating(r.getRating(), rating, product_id);
    	
    }

}