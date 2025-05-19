package com.user.credentail;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UserApplication {
	
private static final Logger log= LoggerFactory.getLogger(UserApplication.class);
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void intProcess() {
		try {
			if(dataSource !=null) {
				JdbcTemplate template= new JdbcTemplate();
				template.setDataSource(dataSource);
			}else {
				throw new NullPointerException();
			}
			
		} catch (Exception e) {
	    	log.info("intProcess()"+e.getMessage());
			log.debug("intProcess()"+e.getMessage());
			log.error("intProcess()"+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class,args);
	}
	

}
