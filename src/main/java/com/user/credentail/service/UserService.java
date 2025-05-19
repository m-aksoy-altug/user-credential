package com.user.credentail.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.user.credentail.component.SendEmail;
import com.user.credentail.entity.User;
import com.user.credentail.repo.UserRepo;


@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SendEmail sendEmail;
//	https://dzone.com/articles/spring-oauth2-resource-servers	
// https://www.baeldung.com/spring-boot-keycloak
// https://howtodoinjava.com/spring-boot2/oauth2-auth-server/	
//	https://www.baeldung.com/spring-dynamic-client-registration
// https://medium.com/@dkaluza19/login-page-as-spa-in-spring-authorization-server-22b87e9ca1fa
		
	@Transactional
	public JsonNode saveCredentials(JsonNode requestBody) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode responseNode = objectMapper.createObjectNode();
		String email = requestBody.get("email").asText();
		User  userexist= userRepo.findByEmail(email);
		log.info("userexist:: "+userexist);
		if(userexist==null) {
			User userSave= new User();
			userSave.setEmail(email);
			// implement salted hash
			// SRP Verifier Secure Remote Password Protocol
			userSave.setPassword(requestBody.get("password1").asText());
			userSave.setPhone(requestBody.get("phone").asText());
			userSave.setEmailVerified(false);
			userSave.setFirstName(requestBody.get("firstName").asText());
			userSave.setLastName(requestBody.get("lastName").asText());
//			userRepo.save(userSave);
			log.info("Sending email TO :: "+email.trim());
			sendEmail.sendEmailRegister(email.trim());			
			return requestBody;
		}
		return (JsonNode) responseNode;
	}
	
	
	
}
