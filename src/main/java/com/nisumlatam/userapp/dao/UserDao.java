package com.nisumlatam.userapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisumlatam.userapp.domain.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	List<User> findByEmailIgnoreCase(String email);
	
	List<User> findByEmailIgnoreCaseAndIdNot(String email, String id);

	User findByEmailIgnoreCaseAndPassword(String email, String password);

}
