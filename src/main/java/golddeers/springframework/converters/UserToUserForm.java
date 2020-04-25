package golddeers.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import golddeers.springframework.commands.UserForm;
import golddeers.springframework.model.User;

@Component
public class UserToUserForm implements Converter<User, UserForm> {

	@Override
	public UserForm convert(User user) {

		UserForm userForm = new UserForm();

		userForm.setUsername(user.getUsername());
		userForm.setPassword(user.getPassword());
		userForm.setEmail(user.getEmail());
		userForm.setAddress(user.getAddress());
		userForm.setUserType(user.getUserType());
		return userForm;
	}

}
