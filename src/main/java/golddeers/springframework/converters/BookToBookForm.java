package golddeers.springframework.converters;

import golddeers.springframework.commands.BookForm;
import golddeers.springframework.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookForm implements Converter<Book, BookForm> {
    @Override
    public BookForm convert(Book book) {
        BookForm bookForm = new BookForm();
        bookForm.setId(book.getId());
        bookForm.setName(book.getDescription());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setGenre(book.getGenre());
        bookForm.setPrice(book.getPrice());
        bookForm.setImageURL(book.getImageUrl());
        return bookForm;
    }
}