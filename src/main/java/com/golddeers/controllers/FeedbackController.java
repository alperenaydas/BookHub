package com.golddeers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golddeers.commands.FeedbackForm;

@Controller
public class FeedbackController {

	@RequestMapping({ "/contact" })
	public String leaveFeedback(Model model) {

		model.addAttribute("contactForm", new FeedbackForm());
		return "winter/contact";
	}
}
