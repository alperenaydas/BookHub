package golddeers.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import golddeers.springframework.commands.BookForm;
import golddeers.springframework.model.Book;

@Component
public class BookFormToBook implements Converter<BookForm, Book> {

	@Override
	public Book convert(BookForm bookForm) {
		Book book = new Book();
		if (bookForm.getId() != null && !StringUtils.isEmpty(bookForm.getId())) {
			book.setId(new Long(bookForm.getId()));
		}
		book.setDescription(bookForm.getName());
		book.setAuthor(bookForm.getAuthor());
		book.setGenre(bookForm.getGenre());
		book.setPrice(bookForm.getPrice());
		book.setImageUrl(bookForm.getImageURL());
		return book;
	}
}