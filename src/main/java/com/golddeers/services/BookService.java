package com.golddeers.services;

import java.util.List;

import com.golddeers.commands.BookForm;
import com.golddeers.model.Book;

public interface BookService {

    List<Book> listAll();

    Book getById(Long id);

    Book saveOrUpdate(Book book);
    
    void delete(Long id);

    Book saveOrUpdateBookForm(BookForm bookForm);
}
