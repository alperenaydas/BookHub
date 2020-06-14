package com.golddeers.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golddeers.model.Book;
import com.golddeers.model.Cart;
import com.golddeers.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
	// private BookRepository bookRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	// @Autowired
	// public CartServiceImpl(BookRepository bookRepository) { this.bookRepository =
	// bookRepository;}

	@Override
	public void removeBook(Book book, Long id) {
		cartRepository.deleteById(id);
	}

	@Override
	public BigDecimal getTotal() {
		System.out.println("sa");
		;
		return BigDecimal.valueOf(5);
	}

	@Override
	public void addBook(Book book) {
		System.out.println("as");
	}

	@Override
	public Cart saveOrUpdate(Cart cart) {
		cartRepository.save(cart);
		return cart;
	}

	@Override
	public void delete(Long fakeid) {
		cartRepository.deleteById(fakeid);
	}

	@Override
	public List<Cart> listAll() {
		List<Cart> carts = new ArrayList<>();
		cartRepository.findAll().forEach(carts::add);
		return carts;
	}

	@Override
	public List<Cart> findByUsernameContaining(Object username) {
		return cartRepository.findByUsernameIgnoreCaseContaining(username);
	}

	@Override
	public Cart getByBookId(Long bookid, String username) {
		for (Cart cart : cartRepository.findAll()) {

			System.out.println(username);
			System.out.println(cart.getUsername());
			System.out.println("SASASASAASSA");
			System.out.println(cart.getBookid());
			System.out.println(bookid);
			if (cart.getUsername().equals(username) && cart.getBookid().equals(bookid)) {

				return cart;
			}

		}
		return null;
	}

}
