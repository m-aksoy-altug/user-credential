package com.user.credentail.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.user.credentail.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/register",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectNode> saveCredentials(@RequestBody JsonNode requestBody) {
		log.info("request body:: "+requestBody);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode responseNode = objectMapper.createObjectNode();
		try {
			headers.set("response", "success");
			headers.set("Content-Type", "application/json");
			JsonNode response =userService.saveCredentials(requestBody);
			responseNode.set("Response", response);
			return new ResponseEntity<>((ObjectNode) responseNode, headers, HttpStatus.OK);
		} catch (Exception e) {
			log.info("error:: "+e.getMessage());
			log.error("error:: "+e.getMessage());
			return new ResponseEntity<>((ObjectNode) responseNode, headers, HttpStatus.BAD_REQUEST);
		}
	}
	


}
