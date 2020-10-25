package com.example.demo.dao;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Image;

import java.util.List;
import java.util.Random; 

@Transactional
@Repository
public class ImageDao {
	
	
	@Autowired
    JdbcTemplate jt;
	
	public boolean save(MultipartFile file, HttpServletRequest request,int product_id) {
		String imagePath = imageUpload(file,request);
		if(imagePath==null) return false;
		jt.update("insert into images values (?,?)",product_id, imagePath);
		return true;
	}	
	public Image getImageById(int product_id) {
		Image res = jt.queryForObject("select * from images where product_id = " + product_id + " LIMIT 1",new BeanPropertyRowMapper<Image>(Image.class));
		return res;
	}
	public List<Image> getAllImagesById(int product_id){
		List<Image> res = jt.query("select * from images where product_id = " + product_id ,new BeanPropertyRowMapper<Image>(Image.class));
		return res;
	}
	public String imageUpload(MultipartFile file, HttpServletRequest request) {
		
		System.out.println(file);
		if (!file.isEmpty()) {
            try {
                String uploadsDir = "static/uploads/";
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists()){
                    new File(realPathtoUploads).mkdir();
                }
                System.out.println("realPathtoUploads ="+ realPathtoUploads);
                Random rand = new Random();
                String orgName = rand.nextInt(1000) + file.getOriginalFilename();
                String filePath = realPathtoUploads +  orgName ;
                File dest = new File(filePath);
                file.transferTo(dest);
                System.out.println(orgName);
                return uploadsDir + orgName;
            } catch (Exception e) {
            	return e.toString();
				// TODO: handle exception
			} 
		}
		return null;
	}
}
