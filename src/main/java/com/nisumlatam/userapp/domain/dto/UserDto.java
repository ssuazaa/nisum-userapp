package com.nisumlatam.userapp.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true, setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2769494367096119626L;

	private UUID id;

	private String name;

	@Pattern(regexp = Constants.REGGEX_EMAIL, message = Constants.ERROR_BUSSINESS_VALIDATION_EMAIL)
	private String email;

	@Pattern(regexp = Constants.REGGEX_PASSWORD_NUMBERS, message = Constants.ERROR_BUSSINESS_VALIDATION_PASSWORD_NUMBERS)
	@Pattern(regexp = Constants.REGGEX_PASSWORD_UPPER, message = Constants.ERROR_BUSSINESS_VALIDATION_PASSWORD_UPPER)
	@Pattern(regexp = Constants.REGGEX_PASSWORD_LOWER, message = Constants.ERROR_BUSSINESS_VALIDATION_PASSWORD_LOWER)
	private String password;

	private Set<PhoneDto> phones;

	private LocalDateTime created;

	private LocalDateTime modified;

	private LocalDateTime last_login;

	private String token;

	private boolean isactive;

}
