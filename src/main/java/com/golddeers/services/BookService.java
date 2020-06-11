package com.golddeers.services;

import com.golddeers.commands.BookForm;
import com.golddeers.model.Book;

import java.util.List;

public interface BookService {

	List<Book> listAll();

	List<Book> findByDescriptionContaining(String description);

	List<Book> findByAuthorIgnoreCaseContaining(String author);

	List<Book> findByGenreIgnoreCaseContaining(String genre);

	Book getById(Long id);

	Book saveOrUpdate(Book book);

	void delete(Long id);

	Book saveOrUpdateBookForm(BookForm bookForm);


}
