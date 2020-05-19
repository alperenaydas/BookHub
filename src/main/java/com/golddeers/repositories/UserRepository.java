package com.golddeers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.golddeers.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
