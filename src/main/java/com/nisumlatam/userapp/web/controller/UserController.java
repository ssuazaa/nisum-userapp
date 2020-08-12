package com.nisumlatam.userapp.web.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nisumlatam.userapp.domain.dto.UserDto;
import com.nisumlatam.userapp.domain.model.User;
import com.nisumlatam.userapp.service.UserService;
import com.nisumlatam.userapp.web.exceptions.BussinesValidationException;

@RestController
@RequestMapping("api/users")
public class UserController {

	private final UserService userService;

	private final ModelMapper mapper;

	public UserController(UserService userService, ModelMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		List<UserDto> dtos = userService.findAll()
				.stream()
				.map(u -> mapper.map(u, UserDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable String id) {
		User entity = userService.findById(id).get();
		return new ResponseEntity<UserDto>(mapper.map(entity, UserDto.class), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody UserDto dto, Errors errors) {
		if (errors.hasErrors())
			throw new BussinesValidationException(errors);
		User entity = userService.save(mapper.map(dto, User.class));
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(entity.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody UserDto dto, Errors errors) {
		if (errors.hasErrors())
			throw new BussinesValidationException(errors);
		userService.update(id, mapper.map(dto, User.class));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
