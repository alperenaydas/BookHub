package com.golddeers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.golddeers.commands.BookForm;
import com.golddeers.model.Book;

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
		bookForm.setDetails(book.getDetails());
		bookForm.setImageURL(book.getImageUrl());
		bookForm.setNumberInStock(book.getNumberInStock());
		return bookForm;
	}
}