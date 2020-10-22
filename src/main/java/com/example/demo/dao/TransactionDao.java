package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Transaction;



@Transactional
@Repository
public class TransactionDao {

    @Autowired
    JdbcTemplate jt;
    
    public int save(Double amount, int order_id,String status,String payment_method) {
        Date dt = new Date();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfd.format(dt);
        String sql = "insert into transaction (date,amount,order_id,status,payment_method) values (?,?,?,?,?)";
        jt.update(sql, currentDate, amount, order_id,status,payment_method);
        return jt.queryForObject("select transaction_id from transaction where order_id = " + order_id,Integer.class);
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
}