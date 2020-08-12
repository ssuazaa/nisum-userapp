package com.nisumlatam.userapp.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

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

	private String id;

	private String name;

	@Pattern(regexp = "^(.+)@(.+)cl$", message = "el email no cumple con el formato valido (aaaaaaa@dominio.cl).")
	private String email;

	@Pattern(regexp = "^(?=(?:.*[0-9]){2,}).+$", message = "el password debe tener mínimo 2 números.")
	@Pattern(regexp = "^(?=(?:.*[a-z]){1,}).+$", message = "el password debe tener mínimo 1 carácter en minúscula.")
	@Pattern(regexp = "^(?=(?:.*[A-Z]){2,}).+$", message = "el password debe tener mínimo 2 caracteres en mayúscula.")
	private String password;

	private Set<PhoneDto> phones;

	private LocalDateTime created;

	private LocalDateTime modified;

	private LocalDateTime last_login;

	private String token;

	private boolean isactive;

}
