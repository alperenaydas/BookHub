package com.golddeers.controllers;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.golddeers.email.SendEmail;

@Controller
public class FeedbackController {

	@RequestMapping(value = "/contact")
	public String sendFeedbackForm(Model model) {

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
		return "winter/contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String leaveFeedback(@RequestParam(name = "message") String message,
			@RequestParam(name = "email") String email, @RequestParam(name = "subject") String subject,
			@RequestParam(name = "name") String name, Model model) {

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

		try {

			SendEmail.sendEmail("necatiberkozgur@gmail.com", name + " said: \n" + message, subject);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("isSent", true);
		return "winter/contact";
	}
}
