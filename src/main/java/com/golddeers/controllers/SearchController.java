package com.golddeers.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/filterby/author/{authorname}/{searchkey}")
	public String filterByAuthor(@PathVariable("searchkey") String searchkey, @PathVariable String authorname,
			Model model) {

		Set<Book> foundBooks = new HashSet<>();
		Set<Book> ret = new HashSet<>();

		foundBooks.addAll(bookService.findByDescriptionContaining(searchkey));
		foundBooks.addAll(bookService.findByAuthorIgnoreCaseContaining(searchkey));
		foundBooks.addAll(bookService.findByGenreIgnoreCaseContaining(searchkey));

		foundBooks.forEach((book) -> {

			if (book.getAuthor().equals(authorname) == true) {

				ret.add(book);
			}

		});

		model.addAttribute("books", ret);
		return "/winter/list";
	}

	@RequestMapping(value = "/filterby/genre/{genre}")
	public String filterByGenre(@ModelAttribute("books") String searchkey, @PathVariable String genre, Model model) {

		Set<Book> foundBooks = new HashSet<>();
		Set<Book> ret = new HashSet<>();

		foundBooks.addAll(bookService.findByDescriptionContaining(searchkey));
		foundBooks.addAll(bookService.findByAuthorIgnoreCaseContaining(searchkey));
		foundBooks.addAll(bookService.findByGenreIgnoreCaseContaining(searchkey));

		foundBooks.forEach((book) -> {

			if (book.getGenre().equals(genre) == true) {

				ret.add(book);
			}

		});

		model.addAttribute("books", ret);
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
