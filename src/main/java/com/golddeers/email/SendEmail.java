
package com.golddeers.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;



import javax.activation.*;

public class SendEmail {

   public static void send() {    
      // Recipient's email ID needs to be mentioned.
      String to = "haruntoktas@hotmail.com";

      // Sender's email ID needs to be mentioned
      String from = "harunalperentoktas@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("KonuYok!");

         // Now set the actual message
         message.setText("Bu mesaj bir java aplikasyonundan gonderilmistir.");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}