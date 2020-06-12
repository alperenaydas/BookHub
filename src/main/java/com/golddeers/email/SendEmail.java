
package com.golddeers.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;


public class SendEmail {

   public static void sendEmail(String emails,String receivedMessage,String receivedSubject) throws MessagingException {    
	   
	  System.out.println(emails);
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
    	
      Message message = prepareMessage(session,myAccountEmail,emails, receivedMessage, receivedSubject);
      Transport.send(message);
      System.out.println("Message Sent Successfully");
    		  
   }

   private static Message prepareMessage(Session session, String myAccountEmail,String emails,String receivedMessage,String receivedSubject) {
	   
		   Message message = new MimeMessage(session);
		   try {
			
			message.setFrom(new InternetAddress(myAccountEmail));
			String recipient = emails;
			String[] recipientList = recipient.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient1 : recipientList) {
			    recipientAddress[counter] = new InternetAddress(recipient1.trim());
			    counter++;
			}
			
			message.setRecipients(Message.RecipientType.TO, recipientAddress);
			//message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject(receivedSubject);
			message.setText(receivedMessage);
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
	  
	   
   }
}