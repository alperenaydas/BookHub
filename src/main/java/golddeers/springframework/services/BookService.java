package golddeers.springframework.services;

import golddeers.springframework.commands.BookForm;
import golddeers.springframework.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book getById(Long id);

    Book saveOrUpdate(Book book);
    
    void delete(Long id);

    Book saveOrUpdateBookForm(BookForm bookForm);
}
