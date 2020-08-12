package com.nisumlatam.userapp.service;

import com.nisumlatam.userapp.domain.model.User;
import com.nisumlatam.userapp.service.util.CrudService;

public interface UserService extends CrudService<User, String> {

	User findByEmailAndPassword(String email, String password);

	void updateLogin(User entity);
	
}
