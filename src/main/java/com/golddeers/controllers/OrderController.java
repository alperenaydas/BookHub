package com.golddeers.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golddeers.model.Order;
import com.golddeers.repositories.OrderRepository;
import com.golddeers.services.CartService;
import com.golddeers.services.OrderService;

@Controller
public class OrderController {

	private OrderRepository orderRepository;
	private CartService cartService;
	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping("/addCartToOrder/{username}")
	public String addBookToCart(@PathVariable String username, Model model) {
		model.addAttribute("cart", cartService.findByUsernameContaining(username));
		String user = (String) Session.online.keySet().toArray()[0];
		System.out.println(username);
		System.out.println(user);
		orderService.saveOrUpdate(new Order(username, false, LocalDate.now()));

		return "winter/index";

		// System.out.println(id);

		// bookService.getById(Long.valueOf(id)).filter(cartService::addBook);

	}

//	@RequestMapping("/cart") // we have errors when we write like this: ("/cart")
//	public String listBooks(Model model) {
//		if (Session.online.containsValue("admin") || Session.online.containsValue("Admin")) {
//			return "/winter/index";
//		} else {
//			model.addAttribute("carts", cartService.findByUsernameContaining(Session.online.keySet().toArray()[0]));
//			List<Book> booksincart = new ArrayList<>();
//			for (Cart cart : cartService.findByUsernameContaining(Session.online.keySet().toArray()[0])) {
//				booksincart.add(bookService.getById(cart.getBookid()));
//			}
//
//			model.addAttribute("booksincart", booksincart);
//			return "/winter/cart";
//		}
//
//	}
//
//	@RequestMapping("/cart/delete/{bookid}")
//	public String delete(@PathVariable String bookid) {
//		String username = (String) Session.online.keySet().toArray()[0];
//
//		cartService.delete(cartService.getByBookId(Long.valueOf(bookid), username).getFakeid());
//		return "redirect:/cart";
//	}

}
