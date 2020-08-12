package com.nisumlatam.userapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisumlatam.userapp.dao.UserDao;
import com.nisumlatam.userapp.domain.dto.Constants;
import com.nisumlatam.userapp.domain.model.User;
import com.nisumlatam.userapp.service.UserService;
import com.nisumlatam.userapp.web.exceptions.BussinesValidationException;
import com.nisumlatam.userapp.web.exceptions.ObjectNotFoundException;
import com.nisumlatam.userapp.web.util.UtilToken;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(String id) {
		return Optional.of(userDao.findById(id).orElseThrow(() -> new ObjectNotFoundException()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User save(User entity) {
		if (!userDao.findByEmailIgnoreCase(entity.getEmail()).isEmpty())
			throw new BussinesValidationException(Constants.ERROR_VALIDATION_EXISTS_EMAIL);
		entity = entity.toBuilder()
			.withCreated(LocalDateTime.now())
			.withLastLogin(LocalDateTime.now())
			.withIsactive(1)
			.withToken(UtilToken.generateToken(entity.getEmail()))
			.build();
		
		return userDao.save(entity);
	}

	@Override
	public void update(String id, User entity) {
		if (!userDao.findByEmailIgnoreCaseAndIdNot(entity.getEmail(), id).isEmpty()) {
			throw new BussinesValidationException(Constants.ERROR_VALIDATION_EXISTS_EMAIL);
		}

//		entity.setPhones(entity.getPhones()
//			.stream()
//			.map(p -> {
//				Phone ph = p;
//				ph.setUser(entity);
//				return ph;
//			}).collect(Collectors.toSet())
//		);
		
		User entityDB = userDao.findById(id).orElseThrow(() -> new ObjectNotFoundException());
		entityDB = entityDB.toBuilder()
			.withId(id)
			.withName(entity.getName())
			.withEmail(entity.getEmail())
			.withPhones(entity.getPhones())
			.withPassword(entity.getPassword())
			.withModified(LocalDateTime.now())
			.withIsactive(entity.getIsactive())
			.build();
		
		userDao.save(entityDB);
	}
	
	@Override
	public void updateLogin(User entity) {
		userDao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		User entity = userDao.findById(id).orElseThrow(() -> new ObjectNotFoundException());
		userDao.delete(entity);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userDao.findByEmailIgnoreCaseAndPassword(email.toUpperCase(), password);
	}

}
