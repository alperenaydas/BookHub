package com.golddeers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.golddeers.model.Book;
import com.golddeers.services.BookService;

@Controller
public class SearchController {

	private BookService bookService;

	@Autowired
	public void setBookService(BookService bookService) {

		this.bookService = bookService;
	}

	public List<Book> getBooks(String searchString) {

		return null;
	}

	public List<Book> filter(List<Book> books, String... filters) {

		return null;
	}

	public List<Book> sort(List<Book> books, String sortOption) {

		return null;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchByKey(@RequestParam(name = "myid") String myid, Model model) {

		List<Book> foundBooks = new ArrayList<>();
		foundBooks.addAll(bookService.findByDescriptionContaining(myid));
		foundBooks.addAll(bookService.findByAuthorIgnoreCaseContaining(myid));
		foundBooks.addAll(bookService.findByGenreIgnoreCaseContaining(myid));

		foundBooks.forEach((book) ->

		System.out.println(book.getDescription()));

		return "/winter/index";
	}

}
