package com.golddeers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golddeers.model.Cart;
import com.golddeers.model.Order;
import com.golddeers.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public void removeCart(Cart cart, Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public void addCart(Cart cart) {
		System.out.println("as");
	}

	@Override
	public Order saveOrUpdate(Order order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public void delete(Long fakeid) {
		orderRepository.deleteById(fakeid);
	}

	@Override
	public List<Order> listAll() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}

	@Override
	public List<Order> findByUsernameContaining(Object username) {
		return orderRepository.findByUsernameIgnoreCaseContaining(username);
	}

	@Override
	public Order getByCartId(Long cartid, String username) {
		for (Order order : orderRepository.findAll()) {

			System.out.println(username);
			System.out.println(order.getUsername());
			System.out.println("SASASASAASSA");
			System.out.println(cartid);
			if (order.getUsername().equals(username)) {
				return order;
			}

		}
		return null;
	}
}
