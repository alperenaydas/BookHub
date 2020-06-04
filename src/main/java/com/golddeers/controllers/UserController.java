package com.golddeers.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.golddeers.commands.LoginForm;
import com.golddeers.commands.UserForm;
import com.golddeers.converters.UserToUserForm;
import com.golddeers.model.Role;
import com.golddeers.model.User;
import com.golddeers.services.UserService;

@Controller
public class UserController {

	private UserService userService;

	private UserToUserForm userToUserForm;

	@Autowired
	public void setUserToUserForm(UserToUserForm userToUserForm) {
		this.userToUserForm = userToUserForm;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String loginReq(Model model) {
		if (!Session.online.isEmpty()) {
			return "winter/index";
		}
		model.addAttribute("loginForm", new LoginForm());
		return "general/login";
	}

	@RequestMapping("/user/new")
	public String newBook(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "users/userform";
	}

	@RequestMapping("/logout")
	public String logout() {
		if (!Session.online.isEmpty()) {
			Session.online.clear();
		}
		return "winter/index";
	}
	@RequestMapping("/cart")
	public String myCart () {
		
		return "winter/index";
	}
	
	
	@RequestMapping("/user/show/{username}")
	public String getUser(@PathVariable String username, Model model) {
		model.addAttribute("user", userService.getById(username));
		return "users/single-user";
	}


	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveOrUpdateBook(@Valid UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "users/userform";
		}

		User savedUser = userService.saveOrUpdateUserForm(userForm);

		return "redirect:/user/show/" + savedUser.getUsername();
	}

	/*@RequestMapping("/user/show/{id}")
	public String getBook(@PathVariable String id, Model model) {
		model.addAttribute("user", userService.getById(id));
		return "users/show";
	}*/

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid LoginForm loginForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/general/home";
		}

		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		User userFetched = userService.getById(username);
		String userType = userFetched.getUserType();
		System.err.println(username);
		System.err.println(password);
		if (userFetched != null && userFetched.getPassword().equals(password)) {
			Session.login(username, userType);
			System.out.println("login success");
			if (Role.Admin == Role.getByName(userFetched.getUserType())) {

				return "redirect:/book";

			} else if (Role.Registered == Role.getByName(userFetched.getUserType())) {

				model.addAttribute("user_type", userFetched.getUserType());
				model.addAttribute("usersOnline", Session.online);
				return "/winter/index";
			} else {
				return "/general/home";
			}

		} else {
			model.addAttribute("loginerror", true);
			return "/general/login";
		}
	}

}
