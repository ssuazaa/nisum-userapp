package com.nisumlatam.userapp.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true, setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2370719483102579539L;

	private String mensaje;

}