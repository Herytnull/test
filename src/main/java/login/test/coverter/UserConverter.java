package login.test.coverter;


import org.springframework.stereotype.Component;

import login.test.dto.UserDTO;
import login.test.model.User;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {
	
	@Override
	public User toEntity (UserDTO userDTO) {
		User user = null;
		if(userDTO != null) {
		user= new User(userDTO.getId(),userDTO.getName(), userDTO.getPassword(), userDTO.getUsertype());
		}
		return user;
	}

	@Override
	public UserDTO toDTO(User user) {
		UserDTO userDTO = null;
		if(user != null) {
		userDTO = new UserDTO (user.getId(), user.getName(), user.getPassword(), user.getUsertype());
		}
		return userDTO;
	}
		
}
