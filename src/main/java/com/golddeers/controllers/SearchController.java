package com.golddeers.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.golddeers.model.Book;
import com.golddeers.model.Category;
import com.golddeers.services.BookService;
import com.golddeers.services.CategoryService;

@Controller
public class SearchController {

	private BookService bookService;
	private CategoryService categoryService;

	private static Set<Book> foundStaticBooks;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {

		this.categoryService = categoryService;
	}

	@Autowired
	public void setBookService(BookService bookService) {

		this.bookService = bookService;
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public String filterByAuthor(@RequestParam(name = "authors_dropdown") String author,
			@RequestParam(name = "genres_dropdown") String genre,
			@RequestParam(name = "sort_dropdown") String sortOption, Model model) {

		Set<Book> retBooks = new HashSet<Book>();
		Set<String> retAuthors = new HashSet<String>();

		List<Book> retList = new ArrayList<Book>();

		List<Category> genres = categoryService.listAll();
		Set<String> retGenre = new HashSet<String>();
		genres.forEach((category) -> retGenre.add(category.getName()));

		System.out.println(genre);
		System.out.println(author);

		foundStaticBooks.forEach((book) -> {

			if ((genre.equals("all") || book.getGenre().equals(genre))
					&& (author.equals("all") || book.getAuthor().equals(author))) {

				retBooks.add(book);
			}

			retAuthors.add(book.getAuthor());
		});

		retList = new ArrayList<Book>(retBooks);

		if (sortOption.equals("none") == false) {

			if (sortOption.equals("pri_asc") == true) {

				Collections.sort(retList, new Comparator<Book>() {
					@Override
					public int compare(Book b2, Book b1) {

						return b2.getPrice().compareTo(b1.getPrice());
					}
				});

			} else if (sortOption.equals("pri_desc") == true) {

				Collections.sort(retList, new Comparator<Book>() {
					@Override
					public int compare(Book b2, Book b1) {

						return b1.getPrice().compareTo(b2.getPrice());
					}
				});

			} else if (sortOption.equals("name_asc") == true) {

				Collections.sort(retList, new Comparator<Book>() {
					@Override
					public int compare(Book b2, Book b1) {

						return b2.getDescription().compareTo(b1.getDescription());
					}
				});

			} else if (sortOption.equals("name_desc") == true) {

				Collections.sort(retList, new Comparator<Book>() {
					@Override
					public int compare(Book b2, Book b1) {

						return b1.getDescription().compareTo(b2.getDescription());
					}
				});

			}
		}

		model.addAttribute("books", retList);
		model.addAttribute("authors", retAuthors);
		model.addAttribute("genres", retGenre);

		return "/winter/list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchByKey(@RequestParam(name = "myid") String myid, Model model) {

		Set<Book> foundBooks = new HashSet<>();
		Set<String> foundAuthors = new HashSet<>();
		List<Category> genres = categoryService.listAll();
		Set<String> retGenre = new HashSet<String>();
		genres.forEach((category) -> retGenre.add(category.getName()));
		
		if(myid.equals("*")) {
			foundBooks.addAll(bookService.listAll());
		}
		else {
			foundBooks.addAll(bookService.findByDescriptionContaining(myid));
			foundBooks.addAll(bookService.findByAuthorIgnoreCaseContaining(myid));
			foundBooks.addAll(bookService.findByGenreIgnoreCaseContaining(myid));
		}

		foundStaticBooks = foundBooks;
		foundBooks.forEach((book) -> {

			foundAuthors.add(book.getAuthor());

		});

		model.addAttribute("books", foundBooks);
		model.addAttribute("genres", retGenre);
		model.addAttribute("authors", foundAuthors);

		return "/winter/list";
	}

	@RequestMapping(value = "/search/{category}")
	public String searchByCategory(@PathVariable(name = "category") String category, Model model) {

		Set<Book> foundBooks = new HashSet<>();
		Set<String> foundAuthors = new HashSet<>();
		List<Category> genres = categoryService.listAll();
		Set<String> retGenre = new HashSet<String>();

		genres.forEach((cat) -> retGenre.add(cat.getName()));

		foundBooks.addAll(bookService.findByGenreIgnoreCaseContaining(category));

		foundStaticBooks = foundBooks;
		foundBooks.forEach((book) -> {

			foundAuthors.add(book.getAuthor());

		});

		model.addAttribute("books", foundBooks);
		model.addAttribute("genres", retGenre);
		model.addAttribute("authors", foundAuthors);

		return "/winter/list";
	}

}
