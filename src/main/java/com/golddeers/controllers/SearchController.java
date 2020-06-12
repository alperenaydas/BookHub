package com.golddeers.controllers;

import java.util.HashSet;
import java.util.Set;

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

	private static Set<Book> foundStaticBooks;

	@Autowired
	public void setBookService(BookService bookService) {

		this.bookService = bookService;
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public String filterByAuthor(@RequestParam(name = "authors_dropdown") String author,
			@RequestParam(name = "genres_dropdown") String genre, Model model) {

		Set<Book> retBooks = new HashSet<Book>();
		Set<String> retGenre = new HashSet<String>();
		Set<String> retAuthors = new HashSet<String>();
		System.out.println(genre);
		System.out.println(author);

		foundStaticBooks.forEach((book) -> {

			if ((genre.equals("all") || book.getGenre().equals(genre))
					&& (author.equals("all") || book.getAuthor().equals(author))) {

				retBooks.add(book);
			}
			retGenre.add(book.getGenre());
			retAuthors.add(book.getAuthor());
		});

		model.addAttribute("books", retBooks);
		model.addAttribute("authors", retAuthors);
		model.addAttribute("genres", retGenre);

		return "/winter/list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchByKey(@RequestParam(name = "myid") String myid, Model model) {

		Set<Book> foundBooks = new HashSet<>();
		Set<String> foundAuthors = new HashSet<>();
		Set<String> foundGenres = new HashSet<>();

		foundBooks.addAll(bookService.findByDescriptionContaining(myid));
		foundBooks.addAll(bookService.findByAuthorIgnoreCaseContaining(myid));
		foundBooks.addAll(bookService.findByGenreIgnoreCaseContaining(myid));

		foundStaticBooks = foundBooks;
		foundBooks.forEach((book) -> {

			foundAuthors.add(book.getAuthor());

			foundGenres.add(book.getGenre());
			System.out.println(book.getDescription());
		});

		model.addAttribute("books", foundBooks);
		model.addAttribute("genres", foundGenres);
		model.addAttribute("authors", foundAuthors);

		return "/winter/list";
	}

}
