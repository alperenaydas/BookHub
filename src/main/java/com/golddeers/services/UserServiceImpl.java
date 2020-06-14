package com.golddeers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golddeers.commands.UserForm;
import com.golddeers.converters.UserFormToUser;
import com.golddeers.model.User;
import com.golddeers.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private UserFormToUser userFormToUser;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserFormToUser userFormToUser) {

		this.userRepository = userRepository;
		this.userFormToUser = userFormToUser;
	}

	@Override
	public List<User> listAll() {

		List<User> allUsers = new ArrayList<>();
		userRepository.findAll().forEach(allUsers::add);
		return allUsers;
	}

	@Override
	public User getById(String id) {

		return userRepository.findById(id).orElse(null);
	}
	@Override
	public User getByUsername(String username) {
		return userRepository.getByUsername(username);
	}

	@Override
	public User saveOrUpdate(User user) {

		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(String username) {

		userRepository.deleteByUsername(username);
	}

	@Override
	public User saveOrUpdateUserForm(UserForm userForm) {

		User savedUser = saveOrUpdate(userFormToUser.convert(userForm));
		System.out.println("Saved user: " + savedUser);
		return savedUser;
	}

	@Override
	public void delete(Long long1) {
		// TODO Auto-generated method stub
		
	}

	

}
