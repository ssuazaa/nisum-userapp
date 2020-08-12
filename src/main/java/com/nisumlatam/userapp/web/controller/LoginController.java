package com.nisumlatam.userapp.web.controller;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisumlatam.userapp.domain.dto.Constants;
import com.nisumlatam.userapp.domain.dto.UserTokenDto;
import com.nisumlatam.userapp.domain.model.User;
import com.nisumlatam.userapp.service.UserService;
import com.nisumlatam.userapp.web.util.UtilToken;

@RestController
@RequestMapping("api/login")
public class LoginController {

	private final UserService userService;
	
	private final ModelMapper mapper;
	
	public LoginController(UserService userService, ModelMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<UserTokenDto> login(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		User userLogin = userService.findByEmailAndPassword(email, password);
		
		if (userLogin == null)
			throw new BadCredentialsException(Constants.ERROR_BAD_CREDENTIALS);
		
		String token = UtilToken.generateToken(userLogin.getEmail());
		userLogin = userLogin.toBuilder()
				.withLastLogin(LocalDateTime.now())
				.withToken(token)
				.build();
		userService.updateLogin(userLogin);
		return new ResponseEntity<UserTokenDto>(mapper.map(userLogin, UserTokenDto.class), HttpStatus.OK);
	}

	

}
