package com.nisumlatam.userapp.domain.dto;

public class Constants {

	public static final String ERROR_OBJECT_NOT_FOUND = "No se encontro ningun registro";
	public static final String ERROR_BAD_CREDENTIALS = "Credenciales invalidas";

	public static final String ERROR_VALIDATION_EXISTS_EMAIL = "Este email ya se encuentra registrado";
	public static final String ERROR_BUSSINESS_VALIDATION_EMAIL = "el email no cumple con el formato valido (aaaaaaa@dominio.cl).";
	public static final String ERROR_BUSSINESS_VALIDATION_PASSWORD_NUMBERS = "el password debe tener m�nimo 2 n�meros.";
	public static final String ERROR_BUSSINESS_VALIDATION_PASSWORD_UPPER = "el password debe tener m�nimo 1 car�cter en min�scula.";
	public static final String ERROR_BUSSINESS_VALIDATION_PASSWORD_LOWER = "el password debe tener m�nimo 2 caracteres en may�scula.";

	public static final String REGGEX_EMAIL = "^(.+)@(.+)cl$";
	public static final String REGGEX_PASSWORD_NUMBERS = "^(?=(?:.*[0-9]){2,}).+$";
	public static final String REGGEX_PASSWORD_UPPER = "^(?=(?:.*[a-z]){1,}).+$";
	public static final String REGGEX_PASSWORD_LOWER = "^(?=(?:.*[a-z]){1,}).+$";

	private Constants() {

	}

}
