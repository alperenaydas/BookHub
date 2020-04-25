package golddeers.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import golddeers.springframework.commands.UserForm;
import golddeers.springframework.model.User;

@Component
public class UserFormToUser implements Converter<UserForm, User> {

	@Override
	public User convert(UserForm userForm) {

		User user = new User();

		if (userForm.getUsername() != null && StringUtils.isEmpty(userForm.getUsername()) == false) {

			user.setUsername(userForm.getUsername());
		}

		user.setPassword(userForm.getPassword());
		user.setEmail(userForm.getEmail());
		user.setAddress(userForm.getAddress());
		user.setUserType(userForm.getUserType());

		return user;
	}

}
