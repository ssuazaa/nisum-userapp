package com.nisumlatam.userapp.service;

import java.util.UUID;

import com.nisumlatam.userapp.domain.model.User;

public interface UserService extends CrudService<User, UUID> {

	User findByEmailAndPassword(String email, String password);

	void updateLogin(User entity);
	
}
