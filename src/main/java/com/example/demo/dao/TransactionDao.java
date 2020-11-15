package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Transaction;



@Transactional
@Repository
public class TransactionDao {

    @Autowired
    JdbcTemplate jt;
    
    public int save(Double amount, int order_id,String status,String payment_method) {
    	System.out.println("Transaction is being saved");
        Date dt = new Date();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfd.format(dt);
        String sql = "insert into transaction (date,amount,order_id,status,payment_method) values (?,?,?,?,?)";
        jt.update(sql, currentDate, amount, order_id,status,payment_method);
        KeyHolder holder = new GeneratedKeyHolder();

		  jt.update(new PreparedStatementCreator() {

	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1,currentDate);
	                ps.setDouble(2, amount);
	                ps.setInt(3, order_id);
	                ps.setString(4,status);
	                ps.setString(5, payment_method);
	               
	                return ps;
	                
	            }
	        }, holder);
		  int tr_id = holder.getKey().intValue();
		  return tr_id;
    }

    public Transaction getTransactionById(int id) {
        String sql = "select * from transaction where transaction_id='" + id + "'";
        return jt.queryForObject(sql, new RowMapper<Transaction>() {
            public Transaction mapRow(ResultSet row, int rowNum) throws SQLException {
                Transaction u = new Transaction(row.getInt("transaction_id"), row.getDate("date"), row.getDouble("amount"), row.getString("status"), row.getInt("order_id"), row.getString("payment_method"));
                return u;
            }
        });
    }
    
    public List<Transaction> getTransactionByOrderId(int id) {
        String sql = "select * from transaction where order_id='" + id + "'";
        return jt.query(sql, new RowMapper<Transaction>() {
            public Transaction mapRow(ResultSet row, int rowNum) throws SQLException {
                Transaction u = new Transaction(row.getInt("transaction_id"), row.getDate("date"), row.getDouble("amount"), row.getString("status"), row.getInt("order_id"), row.getString("payment_method"));
                return u;
            }
        });
    }
    
    public List<Transaction> getAllTransactions() {
        String sql = "select * from transaction";
        return jt.query(sql, new RowMapper<Transaction>() {
            public Transaction mapRow(ResultSet row, int rowNum) throws SQLException {
                Transaction u = new Transaction(row.getInt("transaction_id"), row.getDate("date"), row.getDouble("amount"), row.getString("status"), row.getInt("order_id"), row.getString("payment_method"));
                return u;
            }
        });
    }
    
}