package com.nisumlatam.userapp.web.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisumlatam.userapp.domain.dto.Constants;
import com.nisumlatam.userapp.domain.dto.UserTokenDto;
import com.nisumlatam.userapp.domain.model.User;
import com.nisumlatam.userapp.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
		
		String token = getJWTToken(userLogin.getEmail());
		userLogin = userLogin.toBuilder()
				.withLastLogin(LocalDateTime.now())
				.withToken(token)
				.build();
		userService.updateLogin(userLogin);
		return new ResponseEntity<UserTokenDto>(mapper.map(userLogin, UserTokenDto.class), HttpStatus.OK);
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts.builder()
				.setId("JWTtoken")
				.setSubject(username)
				.claim("authorities", grantedAuthorities.stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

}
