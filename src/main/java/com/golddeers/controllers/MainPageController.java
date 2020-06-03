package com.golddeers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

	@RequestMapping({ "/", "/home" })
	public String homePage(Model model) {
		model.addAttribute("usersOnline", Session.online);
		return "/winter/index";
	}
}
