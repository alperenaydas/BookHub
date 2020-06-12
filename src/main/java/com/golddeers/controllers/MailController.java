package com.golddeers.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.golddeers.services.UserService;
import com.golddeers.commands.EmailForm;

import com.golddeers.model.User;




@Controller
public class MailController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping({ "/email" })
	public String sendEmailNavigate(Model model) {

		model.addAttribute("emailForm", new EmailForm());
		return "winter/email";
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String sendEmail (Model model,EmailForm emailForm) throws MessagingException {
		
		
		String emails = "";
		String message = emailForm.getMessage();
		String subject = emailForm.getSubject();
		List<User> Users = userService.listAll();
		for (int i = 0; i < Users.size(); i++) {
            
            emails  = emails + Users.get(i).getEmail();
            if(i != Users.size() - 1) {
            	emails+= ",";
            }
		}
		
		com.golddeers.email.SendEmail.sendEmail(emails,message,subject);

		return "winter/email";
	}
	
	
}