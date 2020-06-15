package com.golddeers.controllers;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.golddeers.commands.EmailForm;
import com.golddeers.model.User;
import com.golddeers.services.UserService;

@Controller
public class MailController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping({ "/email" })
	public String sendEmailNavigate(Model model) {

		if (Session.online.isEmpty() == false) {

			if (Session.online.containsValue("admin")) {

				model.addAttribute("admin", true);
				model.addAttribute("user_type", "admin");
			} else {
				model.addAttribute("admin", false);
				model.addAttribute("user_type", "registered");
			}
			model.addAttribute("usersOnline", Session.online);
			model.addAttribute("username", Session.online.keySet().toArray()[0]);

		}
		model.addAttribute("emailForm", new EmailForm());
		return "winter/email";
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String sendEmail(Model model, EmailForm emailForm) throws MessagingException {

		if (Session.online.isEmpty() == false) {

			if (Session.online.containsValue("admin")) {

				model.addAttribute("admin", true);
				model.addAttribute("user_type", "admin");
			} else {
				model.addAttribute("admin", false);
				model.addAttribute("user_type", "registered");
			}
			model.addAttribute("usersOnline", Session.online);
			model.addAttribute("username", Session.online.keySet().toArray()[0]);

		}
		String emails = "";
		String message = emailForm.getMessage();
		String subject = emailForm.getSubject();
		List<User> Users = userService.listAll();
		for (int i = 0; i < Users.size(); i++) {

			emails = emails + Users.get(i).getEmail();
			if (i != Users.size() - 1) {
				emails += ",";
			}
		}

		com.golddeers.email.SendEmail.sendEmail(emails, message, subject);

		return "winter/email";
	}

}