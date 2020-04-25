package golddeers.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

	@RequestMapping({ "/", "/home" })
	public String homePage(Model model) {

		return "general/home";
	}
}
