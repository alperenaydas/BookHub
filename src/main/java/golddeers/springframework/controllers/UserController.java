package golddeers.springframework.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import golddeers.springframework.commands.LoginForm;
import golddeers.springframework.commands.UserForm;
import golddeers.springframework.converters.UserToUserForm;
import golddeers.springframework.model.Role;
import golddeers.springframework.model.User;
import golddeers.springframework.services.UserService;

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
		model.addAttribute("loginForm", new LoginForm());
		return "/general/login";
	}

	@RequestMapping("/user/new")
	public String newBook(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "users/userform";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveOrUpdateBook(@Valid UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "users/userform";
		}

		User savedUser = userService.saveOrUpdateUserForm(userForm);

		return "redirect:/user/show/" + savedUser.getUsername();
	}

	@RequestMapping("/user/show/{id}")
	public String getBook(@PathVariable String id, Model model) {
		model.addAttribute("user", userService.getById(id));
		return "users/show";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid LoginForm loginForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/general/home";
		}

		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		User userFetched = userService.getById(username);
		System.err.println(username);
		System.err.println(password);
		if (userFetched != null && userFetched.getPassword().equals(password)) {

			if (Role.Admin == Role.getByName(userFetched.getUserType())) {

				return "redirect:/book";

			} else if (Role.Registered == Role.getByName(userFetched.getUserType())) {

				model.addAttribute("username", username);
				return "/users/home";

			} else {

				return "/logininvalid";
			}

		} else {

			return "redirect:/general/home";
		}
	}

}
