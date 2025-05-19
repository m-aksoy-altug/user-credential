package com.user.credentail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import com.user.credentail.entity.User;


public interface UserRepo extends Repository<User, Integer> {
	
	User findByEmail(String email);

	void save(User userSave);
	
	
}
