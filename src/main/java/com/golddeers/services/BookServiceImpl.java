package com.golddeers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golddeers.commands.BookForm;
import com.golddeers.converters.BookFormToBook;
import com.golddeers.model.Book;
import com.golddeers.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private BookFormToBook bookFormToBook;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, BookFormToBook bookFormToBook) {
		this.bookRepository = bookRepository;
		this.bookFormToBook = bookFormToBook;
	}

	@Override
	public List<Book> listAll() {
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add); // fun with Java 8
		return books;
	}

	@Override
	public Book getById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public Book saveOrUpdate(Book book) {
		bookRepository.save(book);
		return book;
	}

	@Override
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book saveOrUpdateBookForm(BookForm bookForm) {
		Book savedBook = saveOrUpdate(bookFormToBook.convert(bookForm));
		System.out.println("Saved Book Id: " + savedBook.getId());
		return savedBook;
	}

	@Override
	public List<Book> findByDescriptionContaining(String description) {

		return bookRepository.findByDescriptionIgnoreCaseContaining(description);
	}

	@Override
	public List<Book> findByAuthorIgnoreCaseContaining(String author) {

		return bookRepository.findByAuthorIgnoreCaseContaining(author);
	}

	@Override
	public List<Book> findByGenreIgnoreCaseContaining(String genre) {

		return bookRepository.findByGenreIgnoreCaseContaining(genre);
	}
}
