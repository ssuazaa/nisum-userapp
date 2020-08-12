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
public class PhoneDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170741649726804846L;

	private String id;

	private String number;

	private String cityCode;

	private String countryCode;

}
