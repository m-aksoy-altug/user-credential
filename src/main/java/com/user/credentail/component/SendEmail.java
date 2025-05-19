package com.user.credentail.component;

import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Component
public class SendEmail {

	private static final Logger log = LoggerFactory.getLogger(SendEmail.class);
	
	@Value("${email.details.from}")
	private String from;
	
	@Value("${email.details.password}")
	private String userP;
	
	
	public void sendEmailRegister(String to) {
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    String verify= String.format("%06d", number);

	    log.info("sendEmailTo:: "+to);
	    log.info("verify:: "+verify);

	    String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, userP);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Your Verification Code");

//			Multipart multipart = new MimeMultipart();
//			BodyPart messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setText("Verification code");
			  // Send the actual HTML message, as big as you like
			   message.setContent(
		              "<h2>Verification code: <b>"+verify+"</b></h2>"
		    + "<h3>The authentication code will be valid for 10 minutes. Please do not share this code and advise your systems administrator immediately if you did not initiate this request.</h3>",
		             "text/html");
			   
//			   https://gaganchordia.medium.com/spring-boot-mail-thymeleaf-362fda6000cf
//			multipart.addBodyPart(messageBodyPart);
//			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException mex) {
			log.info("error:: "+mex.getMessage());
			log.error("error:: "+mex.getMessage());
			mex.printStackTrace();
		}

	}
	


}
