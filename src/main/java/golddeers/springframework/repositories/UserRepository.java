package golddeers.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import golddeers.springframework.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
