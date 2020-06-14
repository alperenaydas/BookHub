package com.golddeers.services;

import java.util.List;

import com.golddeers.commands.UserForm;
import com.golddeers.model.User;

public interface UserService {

	List<User> listAll();

	User getById(String id);
	
	User getByUsername(String username);

	User saveOrUpdate(User user);

	void delete(Long long1);
	
	void delete(String username);

	User saveOrUpdateUserForm(UserForm userForm);
	
	

}
