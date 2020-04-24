package golddeers.springframework.repositories;

import golddeers.springframework.model.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {
}