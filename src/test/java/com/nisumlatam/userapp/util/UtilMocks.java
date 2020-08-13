package com.nisumlatam.userapp.util;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisumlatam.userapp.domain.dto.UserDto;
import com.nisumlatam.userapp.domain.model.User;

public class UtilMocks {

	public static User mockUser() {
		return User.builder()
				.withId(UUID.randomUUID())
				.withEmail("ssuazaa@domain.cl")
				.withPassword("SuSo08")
				.withName("Juan Sebastian")
				.build();
	}
	
	public static UserDto mockUserDto() {
		return UserDto.builder()
				.withId(UUID.randomUUID())
				.withEmail("ssuazaa@domain.cl")
				.withPassword("SuSo08")
				.withName("Juan Sebastian")
				.build();
	}
	
	public static <T> String objectToJson(T object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <T> T jsonToObject(String json, Class<T> object) {
		try {
			return new ObjectMapper().readValue(json, object);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <T> T[] jsonArrayToObjectArray(String json, Class<T[]> object) {
		try {
			return new ObjectMapper().readValue(json, object);
		} catch (Exception e) {
			return null;
		}
	}
}
