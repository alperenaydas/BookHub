package com.golddeers.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golddeers.model.Cart;
import com.golddeers.model.Order;
import com.golddeers.repositories.OrderRepository;
import com.golddeers.services.BookService;
import com.golddeers.services.CartService;
import com.golddeers.services.OrderService;
import com.golddeers.services.UserService;

@Controller
public class OrderController {

	private OrderRepository orderRepository;
	private CartService cartService;
	private OrderService orderService;
	private BookService bookService;
	private UserService userService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/successful/{username}")
	public String addBookToCart(@PathVariable String username, Model model) {
		model.addAttribute("cart", cartService.findByUsernameContaining(username));

		String user = (String) Session.online.keySet().toArray()[0];
		List<Cart> carts = cartService.findByUsernameContaining(username);
		Order savedOrder = orderService.saveOrUpdate(new Order(username, false, LocalDate.now()));

		for (Cart carty : carts) {
			if (carty.getUsername().equals(username) && !carty.isSold()) {
				carty.setSold(true);
				cartService.saveOrUpdate(carty);
				savedOrder.setTotalprice(savedOrder.getTotalprice().doubleValue()
						+ bookService.getById(carty.getBookid()).getPrice().doubleValue());
			}
		}

		if (savedOrder.getTotalprice() == 0.0) {
			orderService.delete(savedOrder.getFakeid());
			return "winter/index";
		}

		model.addAttribute("savedOrder", savedOrder);
		model.addAttribute("adress", userService.getByUsername(username).getAddress());

		if (Session.online.isEmpty() == false) {

			if (Session.online.containsValue("admin")) {

				model.addAttribute("admin", true);
				model.addAttribute("user_type", "admin");

			} else {

				model.addAttribute("admin", false);
				model.addAttribute("user_type", "registered");
			}

			model.addAttribute("usersOnline", Session.online);
			model.addAttribute("username", Session.online.keySet().toArray()[0]);

		}
		return "winter/confirmation";

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
