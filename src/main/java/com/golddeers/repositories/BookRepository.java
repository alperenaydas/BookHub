package com.golddeers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.golddeers.model.Book;


public interface BookRepository extends CrudRepository<Book, Long> {
}