package com.golddeers.controllers;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;







@Controller
public class MailController {

	@RequestMapping({ "/email" })
	public String sendEmailNavigate() {

		//model.addAttribute("contactForm", new FeedbackForm());
		return "winter/email";
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String sendEmail () {
		
		com.golddeers.email.SendEmail.send();

		return "winter/email";
	}
	
}