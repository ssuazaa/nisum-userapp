package com.nisumlatam.userapp.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisumlatam.userapp.domain.model.User;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {

	List<User> findByEmailIgnoreCase(String email);
	
	List<User> findByEmailIgnoreCaseAndIdNot(String email, UUID id);

	User findByEmailIgnoreCaseAndPassword(String email, String password);

}
