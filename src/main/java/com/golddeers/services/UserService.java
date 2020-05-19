package com.golddeers.services;

import java.util.List;

import com.golddeers.commands.UserForm;
import com.golddeers.model.User;

public interface UserService {

	List<User> listAll();

	User getById(String id);

	User saveOrUpdate(User user);

	void delete(String id);

	User saveOrUpdateUserForm(UserForm userForm);

}
