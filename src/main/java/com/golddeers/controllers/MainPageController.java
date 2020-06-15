package com.golddeers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

	@RequestMapping({ "/", "/home" })
	public String homePage(Model model) {
		model.addAttribute("usersOnline", Session.online);
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
		return "/winter/index";
	}
}
