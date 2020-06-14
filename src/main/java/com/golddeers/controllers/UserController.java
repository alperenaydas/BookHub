package com.golddeers.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.golddeers.commands.BookForm;
import com.golddeers.commands.CategoryForm;
import com.golddeers.commands.LoginForm;
import com.golddeers.commands.UserForm;
import com.golddeers.converters.UserToUserForm;
import com.golddeers.model.Book;
import com.golddeers.model.Category;
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
			if(Session.online.containsValue("admin")) {
				return "/admin/panel";
			}
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
	public String myCart() {

		return "winter/cart";
	}

	@RequestMapping("/user/show/{username}")
	public String getUser(@PathVariable String username, Model model) {
		model.addAttribute("user", userService.getById(username));
		return "users/single-user";
	}
	@RequestMapping("user/edit/{username}")
	public String edit(@PathVariable String username, Model model) {
		
		
		
		User user = userService.getById(username);
		UserForm userForm = userToUserForm.convert(user);
		

		model.addAttribute("userForm", userForm);
		return "users/admin-edit-user";
	}

	/*
	 * @RequestMapping(value = "/user", method = RequestMethod.POST) public String
	 * saveOrUpdateBook(@Valid UserForm userForm, BindingResult bindingResult) {
	 * 
	 * if (bindingResult.hasErrors()) { return "users/userform"; } User savedUser =
	 * userService.saveOrUpdateUserForm(userForm);
	 * 
	 * return "redirect:/user/show/" + savedUser.getUsername(); }
	 */
	@RequestMapping(value = "/useredit", method = RequestMethod.POST)
	public String edit2(@Valid UserForm userForm, Model model) {

		userService.delete(userForm.getUsername());
		User savedUser = userService.saveOrUpdateUserForm(userForm);
		
		
		return "/admin/panel";
	}
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveOrUpdateUser(@Valid UserForm userForm, Model model) {
		String username = userForm.getUsername();
		String password = userForm.getPassword();
		String confirmPassword = userForm.getConfirmPassword();
		userForm.setUserType("registered");
		User userFetched = userService.getById(username);
		if (userFetched == null) { // User name not taken before
			if (password.equals(confirmPassword)) {
				String email = userForm.getEmail();
				int indexof_at =  email.indexOf('@');
				if(indexof_at == -1 || indexof_at== email.length()-1 || indexof_at == 0) { //Check email correctness
					model.addAttribute("emailError", true);
					return "users/userform";
				}else {
					Session.login(username, "registered");
					model.addAttribute("usersOnline", Session.online);
					model.addAttribute("user_type", "registered");
					User savedUser = userService.saveOrUpdateUserForm(userForm);
					return "/winter/index";
				}

			}else {
				model.addAttribute("passworderror", true);		
				return "users/userform";
			}
		}else {
			model.addAttribute("usernameError", true);		
			return "users/userform";
		}
	}
	
	
	
	
	

	/*
	 * @RequestMapping("/user/show/{id}") public String getBook(@PathVariable String
	 * id, Model model) { model.addAttribute("user", userService.getById(id));
	 * return "users/show"; }
	 */

	@RequestMapping("/userlist")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.listAll());
		if (Session.online.containsValue("admin") || Session.online.containsValue("Admin")) {
			model.addAttribute("users", userService.listAll());
			return "/admin/userlist";
		} else {
			return "/winter/index";
		}
	}
	@RequestMapping("/user/delete/{username}")
	public String delete(@PathVariable String username) {
		userService.delete((username));
		return "redirect:/userlist";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid LoginForm loginForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/general/home";
		}

		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		User userFetched = userService.getById(username);

		if(userFetched == null) {
			model.addAttribute("usernameError", true);
			return "/general/login";
		}
		String userType = userFetched.getUserType();
		if (userFetched != null && userFetched.getPassword().equals(password)) {
			Session.login(username, userType);
			System.out.println("login success");
			if (Role.Admin == Role.getByName(userFetched.getUserType())) {
				return "/admin/panel";

			} else if (Role.Registered == Role.getByName(userFetched.getUserType())) {

				model.addAttribute("user_type", userFetched.getUserType());
				model.addAttribute("usersOnline", Session.online);
				model.addAttribute("username", username);
				return "/winter/index";
			} else {
				return "/general/home";
			}

		} else {
			model.addAttribute("passwordError", true);
			return "/general/login";
		}
	}
}
