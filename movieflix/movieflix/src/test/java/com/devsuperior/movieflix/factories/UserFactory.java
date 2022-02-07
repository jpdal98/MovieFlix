package com.devsuperior.movieflix.factories;

import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.entities.Role;
import com.devsuperior.movieflix.entities.User;

public class UserFactory {

	public static User createUser() {
		User user = new User(1L, "Ana", "Ana37@hotmail.com", "ana123");
		user.getRoles().add(new Role(1L, "ROLE_OPERATOR"));
		return user;
	}
	
	public static UserDTO createUserDTO() {
		return new UserDTO(createUser());
	}

}
