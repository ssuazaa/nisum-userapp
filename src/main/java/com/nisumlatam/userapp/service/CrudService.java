package com.nisumlatam.userapp.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

	Optional<T> findById(ID id);

	List<T> findAll();

	T save(T entity);

	void update(ID id, T entity);

	void deleteById(ID id);

}
