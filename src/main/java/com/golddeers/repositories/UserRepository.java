package com.golddeers.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.golddeers.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	@Transactional
	void deleteByUsername(String username);

}
