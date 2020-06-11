
package com.golddeers.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.jboss.logging.Logger;

import javax.activation.*;

public class SendEmail {

   public static void sendEmail(ArrayList<String> recepients) throws MessagingException {    
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
    	
      Message message = prepareMessage(session,myAccountEmail,recepients);
      Transport.send(message);
      System.out.println("Message Sent Successfully");
    		  
   }

   private static Message prepareMessage(Session session,String myAccountEmail,ArrayList<String> recepients ) {
	   
		   Message message = new MimeMessage(session);
		   try {
			
			message.setFrom(new InternetAddress(myAccountEmail));
			String recipient = "harunalperentoktas@gmail.com,haruntoktas@hotmail.com";
			String[] recipientList = recipient.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient1 : recipientList) {
			    recipientAddress[counter] = new InternetAddress(recipient1.trim());
			    counter++;
			}
			
			message.setRecipients(Message.RecipientType.TO, recipientAddress);
			//message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
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