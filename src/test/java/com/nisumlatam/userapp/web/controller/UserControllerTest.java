package com.nisumlatam.userapp.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.nisumlatam.userapp.domain.dto.Constants;
import com.nisumlatam.userapp.domain.dto.ErrorDto;
import com.nisumlatam.userapp.domain.dto.UserDto;
import com.nisumlatam.userapp.domain.model.User;
import com.nisumlatam.userapp.service.UserService;
import com.nisumlatam.userapp.util.UtilMocks;
import com.nisumlatam.userapp.web.exceptions.BussinesValidationException;
import com.nisumlatam.userapp.web.exceptions.ObjectNotFoundException;

@WebMvcTest(UserController.class)
@ActiveProfiles(value = "test")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@MockBean
	private ModelMapper mapper;
	
	private final String preffixApi = "/api/users";
	
	@Test
	void testFindAll() throws Exception {
		// arrange
		List<User> entities = new ArrayList<>();
		entities.add(UtilMocks.mockUser());
		entities.add(UtilMocks.mockUser());
		entities.add(UtilMocks.mockUser());
		
		List<UserDto> dtos = new ArrayList<>();
		dtos.add(UtilMocks.mockUserDto());
		dtos.add(UtilMocks.mockUserDto());
		dtos.add(UtilMocks.mockUserDto());
		
		when(userService.findAll()).thenReturn(entities);
		when(mapper.map(any(), any())).thenReturn(UtilMocks.mockUserDto());
		when(mapper.map(any(), any())).thenReturn(UtilMocks.mockUserDto());
		when(mapper.map(any(), any())).thenReturn(UtilMocks.mockUserDto());
		
		// act
		MvcResult result = mockMvc.perform(get(preffixApi)
	            .contentType(APPLICATION_JSON))
				.andExpect(content().contentType(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();
		
		UserDto[] dtosTest = UtilMocks.jsonArrayToObjectArray(result.getResponse().getContentAsString(), UserDto[].class);
		
		// assert
		assertTrue(dtosTest.length > 0);
	}

	@Test
	void testFindById() throws Exception {
		// arrange
		when(userService.findById(any())).thenReturn(Optional.of(UtilMocks.mockUser()));
		when(mapper.map(any(), any())).thenReturn(UtilMocks.mockUserDto());
		
		// act
		MvcResult result = mockMvc.perform(get(preffixApi + "/{id}", UUID.randomUUID())
	            .contentType(APPLICATION_JSON))
				.andExpect(content().contentType(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();
		
		UserDto dto = UtilMocks.jsonToObject(result.getResponse().getContentAsString(), UserDto.class);
		
		// assert
		assertNotNull(dto);
	}
	
	@Test
	void testFindByIdObjectNotFoundException() throws Exception {
		// arrange
		when(userService.findById(any())).thenThrow(new ObjectNotFoundException(Constants.ERROR_OBJECT_NOT_FOUND));
		
		// act
		MvcResult result = mockMvc.perform(get(preffixApi + "/{id}", UUID.randomUUID())
	            .contentType(APPLICATION_JSON))
				.andExpect(content().contentType(APPLICATION_JSON))
	            .andExpect(status().isBadRequest())
	            .andReturn();
		
		ErrorDto dto = UtilMocks.jsonToObject(result.getResponse().getContentAsString(), ErrorDto.class);
		
		// assert
		assertEquals(dto.getMensaje(), Constants.ERROR_OBJECT_NOT_FOUND);
	}

	@Test
	void testSave() throws Exception {
		// arrange
		User entity = UtilMocks.mockUser();
		UserDto dto = UtilMocks.mockUserDto();
		dto = dto.toBuilder().withId(entity.getId()).build();
		
		when(userService.save(any())).thenReturn(entity);
		when(mapper.map(any(), any())).thenReturn(entity);
		
		// act
		MvcResult resultPost = mockMvc.perform(post(preffixApi)
				.content(UtilMocks.objectToJson(dto))
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isCreated())
	            .andReturn();
		
		// arrange
		when(userService.findById(any())).thenReturn(Optional.of(entity));
		when(mapper.map(any(), any())).thenReturn(dto);
		
		// act
		String location = String.valueOf(resultPost.getResponse().getHeaderValue(HttpHeaders.LOCATION));
		location = "/" + location.split("/", 4)[3];
		MvcResult resultGet = mockMvc.perform(get(location)
	            .contentType(APPLICATION_JSON))
				.andExpect(content().contentType(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();
		
		// assert
		assertNotNull(location);
		assertEquals(dto, UtilMocks.jsonToObject(resultGet.getResponse().getContentAsString(), UserDto.class));
	}
	
	@Test
	void testSaveBussinesValidationExceptionExistsEmail() throws Exception {
		// arrange
		when(userService.save(any())).thenThrow(new BussinesValidationException(Constants.ERROR_VALIDATION_EXISTS_EMAIL));
		
		// act
		MvcResult result = mockMvc.perform(post(preffixApi)
				.content(UtilMocks.objectToJson(UtilMocks.mockUserDto()))
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isBadRequest())
	            .andReturn();
		
		ErrorDto errorDto = UtilMocks.jsonToObject(result.getResponse().getContentAsString(), ErrorDto.class);
		
		// assert
		assertEquals(errorDto.getMensaje(), Constants.ERROR_VALIDATION_EXISTS_EMAIL);
	}
	
	@Test
	void testSaveBussinesValidationExceptionValidateEmail() throws Exception {
		// arrange
		UserDto dto = UtilMocks.mockUserDto();
		dto = dto.toBuilder().withEmail("a@a.pe").build();
		
		// act
		MvcResult result = mockMvc.perform(post(preffixApi)
				.content(UtilMocks.objectToJson(dto))
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isBadRequest())
	            .andReturn();
		
		ErrorDto errorDto = UtilMocks.jsonToObject(result.getResponse().getContentAsString(), ErrorDto.class);
		
		// assert
		assertEquals(errorDto.getMensaje(), Constants.ERROR_BUSSINESS_VALIDATION_EMAIL);
	}

	@Test
	void testUpdate() throws Exception {
		// arrange
		doNothing().when(userService).update(any(), any());
		when(mapper.map(any(), any())).thenReturn(UtilMocks.mockUser());
		
		// act
		MvcResult result = mockMvc.perform(put(preffixApi + "/{id}", UUID.randomUUID())
				.content(UtilMocks.objectToJson(UtilMocks.mockUserDto()))
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isNoContent())
	            .andReturn();
		
		// assert
		assertEquals(result.getResponse().getStatus(), HttpServletResponse.SC_NO_CONTENT);
	}
	
	@Test
	void testUpdateBussinesValidationExceptionExistsEmail() throws Exception {
		// arrange
		doThrow(new BussinesValidationException(Constants.ERROR_VALIDATION_EXISTS_EMAIL)).when(userService).update(any(), any());
		
		// act
		MvcResult result = mockMvc.perform(put(preffixApi + "/{id}", UUID.randomUUID())
				.content(UtilMocks.objectToJson(UtilMocks.mockUserDto()))
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isBadRequest())
	            .andReturn();
		
		ErrorDto errorDto = UtilMocks.jsonToObject(result.getResponse().getContentAsString(), ErrorDto.class);
		
		// assert
		assertEquals(errorDto.getMensaje(), Constants.ERROR_VALIDATION_EXISTS_EMAIL);
		
	}
	
	@Test
	void testUpdateBussinesValidationExceptionValidateEmail() throws Exception {
		// arrange
		UserDto dto = UtilMocks.mockUserDto();
		dto = dto.toBuilder().withEmail("a@a.pe").build();
		
		// act
		MvcResult result = mockMvc.perform(put(preffixApi + "/{id}", UUID.randomUUID())
				.content(UtilMocks.objectToJson(dto))
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isBadRequest())
	            .andReturn();
		
		ErrorDto errorDto = UtilMocks.jsonToObject(result.getResponse().getContentAsString(), ErrorDto.class);
		
		// assert
		assertEquals(errorDto.getMensaje(), Constants.ERROR_BUSSINESS_VALIDATION_EMAIL);
	}

	@Test
	void testDelete() throws Exception {
		// arrange
		doNothing().when(userService).deleteById(any());
		
		// act
		MvcResult result = mockMvc.perform(delete(preffixApi + "/{id}", UUID.randomUUID())
	            .contentType(APPLICATION_JSON))
				//.andDo(MockMvcResultHandlers.print())
	            .andExpect(status().isNoContent())
	            .andReturn();
		
		// assert
		assertEquals(result.getResponse().getStatus(), HttpServletResponse.SC_NO_CONTENT);
	}

}
