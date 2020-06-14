package com.golddeers.services;

import java.util.List;

import com.golddeers.model.Cart;
import com.golddeers.model.Order;

public interface OrderService {
	List<Order> listAll();

	void removeCart(Cart cart, Long id);

	Order getByCartId(Long bookid, String username);

	void addCart(Cart cart);

	Order saveOrUpdate(Order order);

	void delete(Long fakeid);

	List<Order> findByUsernameContaining(Object username);

}
