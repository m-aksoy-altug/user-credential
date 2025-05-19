package com.user.credentail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "USERDETAILS")
public class User {
	
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "FIRSTNAME")  
	@NotBlank	
	private String firstName;
	
	@Column(name = "LASTNAME")  
	@NotBlank	
	private String lastName;
	
	@Column(name = "EMAIL")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@NotBlank
	private String email;

	@Column(name = "PHONE")  
	@NotBlank
	@Pattern(regexp = "\\d{10}", message = "Telephone must be a 10-digit number")
	private String phone;
	
	@Column(name = "PASSWORD")  
	@NotBlank	
	private String password;
	
	@Column(name = "EMAILVERIFIED") 
	private Boolean emailVerified;
	
	@Column(name = "VERIFICATION") 
	private Integer verification;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Integer getVerification() {
		return verification;
	}

	public void setVerification(Integer verification) {
		this.verification = verification;
	}

	
	
}
