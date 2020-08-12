package com.nisumlatam.userapp.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true, setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2769494367096119626L;

	private String name;

	private String email;

	private LocalDateTime last_login;

	private String token;

}
