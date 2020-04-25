package golddeers.springframework.services;

import java.util.List;

import golddeers.springframework.commands.UserForm;
import golddeers.springframework.model.User;

public interface UserService {

	List<User> listAll();

	User getById(String id);

	User saveOrUpdate(User user);

	void delete(String id);

	User saveOrUpdateUserForm(UserForm userForm);

}
