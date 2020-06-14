package com.golddeers.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.golddeers.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUsernameIgnoreCaseContaining(Object username);
}
