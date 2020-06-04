package com.golddeers.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.golddeers.commands.BookForm;
import com.golddeers.converters.BookToBookForm;
import com.golddeers.model.Book;
import com.golddeers.services.BookService;

@Controller
public class BookController {

	private BookService bookService;

	private BookToBookForm bookToBookForm;

	@Autowired
	public void setBookToBookForm(BookToBookForm bookToBookForm) {
		this.bookToBookForm = bookToBookForm;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping({ "/book/list", "/book" })
	public String listBooks(Model model) {
		model.addAttribute("books", bookService.listAll());
		if (Session.online.containsValue("registered") || Session.online.containsValue("Registered")) {
			return "winter/index";
		} else if (Session.online.containsValue("admin") || Session.online.containsValue("Admin")) {
			return "book/list";
		} else {
			return "general/login";
		}
	}

	@RequestMapping("/book/show/{id}")
	public String getBook(@PathVariable String id, Model model) {
		model.addAttribute("book", bookService.getById(Long.valueOf(id)));
		return "book/show";
	}
	
	@RequestMapping("/book/details/{id}")
	public String getBookDetails(@PathVariable String id, Model model) {
		model.addAttribute("book", bookService.getById(Long.valueOf(id)));
		return "book/single-product";
	}
	

	@RequestMapping("book/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		Book book = bookService.getById(Long.valueOf(id));
		BookForm bookForm = bookToBookForm.convert(book);

		model.addAttribute("bookForm", bookForm);
		return "book/bookform";
	}

	@RequestMapping("/book/new")
	public String newBook(Model model) {
		model.addAttribute("bookForm", new BookForm());
		return "book/bookform";
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public String saveOrUpdateBook(@Valid BookForm bookForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "book/bookform";
		}

		Book savedBook = bookService.saveOrUpdateBookForm(bookForm);

		return "redirect:/book/show/" + savedBook.getId();
	}

	@RequestMapping("/book/delete/{id}")
	public String delete(@PathVariable String id) {
		bookService.delete(Long.valueOf(id));
		return "redirect:/book/list";
	}
}
