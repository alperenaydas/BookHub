
package com.golddeers.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.jboss.logging.Logger;

import javax.activation.*;

public class SendEmail {

   public static void sendEmail(String recepient) throws MessagingException {    
	   System.out.println("Prepare Message");
	   Properties properties = new Properties();
      
      properties.put("mail.smtp.auth","true");
      properties.put("mail.smtp.starttls.enable","true");
      properties.put("mail.smtp.host","smtp.gmail.com");
      properties.put("mail.smtp.port","587");
      
      String myAccountEmail = "mihaelmaleo@gmail.com";
      String password = "Bookhub.217";
      
      Session session = Session.getInstance(properties, new Authenticator() {
    	  @Override
    	  protected PasswordAuthentication getPasswordAuthentication() {
    		  return new PasswordAuthentication(myAccountEmail,password);
    	  }
      });
    	
      Message message = prepareMessage(session,myAccountEmail,recepient);
      Transport.send(message);
      System.out.println("Message Sent Successfully");
    		  
   }

   private static Message prepareMessage(Session session,String myAccountEmail,String recepient ) {
	   
		   Message message = new MimeMessage(session);
		   try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("BookHub bilgilendirme Maili");
			message.setText("Bu mesaj admin tarafindan,alayiniza gönderilmistir.");
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
	  
	   
   }
}