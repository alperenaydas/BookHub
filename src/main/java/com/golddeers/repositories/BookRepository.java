package com.golddeers.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.golddeers.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByDescriptionIgnoreCaseContaining(String description);

	List<Book> findByAuthorIgnoreCaseContaining(String author);

	List<Book> findByGenreIgnoreCaseContaining(String genre);

}