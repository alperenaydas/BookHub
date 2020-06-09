package com.golddeers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.golddeers.commands.UserForm;
import com.golddeers.model.User;

@Component
public class UserFormToUser implements Converter<UserForm, User> {

	@Override
	public User convert(UserForm userForm) {

		User user = new User();

		if (userForm.getUsername() != null && StringUtils.isEmpty(userForm.getUsername()) == false) {

			user.setUsername(userForm.getUsername());
		}

		user.setPassword(userForm.getPassword());
		user.setEmail(userForm.getEmail());
		user.setAddress(userForm.getAddress());
		user.setName(userForm.getName());
		user.setUserType(userForm.getUserType());

		return user;
	}

}
