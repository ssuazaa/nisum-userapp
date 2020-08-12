package com.nisumlatam.userapp.config;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nisumlatam.userapp.domain.dto.PhoneDto;
import com.nisumlatam.userapp.domain.dto.UserDto;
import com.nisumlatam.userapp.domain.dto.UserTokenDto;
import com.nisumlatam.userapp.domain.model.Phone;
import com.nisumlatam.userapp.domain.model.User;

@Configuration
public class MapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(userToUserDto());
		mapper.addConverter(userToUserTokenDto());
		mapper.addConverter(userDtoToUser());
		mapper.addConverter(phoneToPhoneDto());
		mapper.addConverter(phoneDtoToPhone());
		return mapper;
	}

	private Converter<User, UserDto> userToUserDto() {
		return new AbstractConverter<User, UserDto>() {

			@Override
			protected UserDto convert(User entity) {
				Set<PhoneDto> phones = new HashSet<>();
				if (!entity.getPhones().isEmpty()) {
					phones = entity.getPhones().stream()
							.map(e -> modelMapper().map(e, PhoneDto.class))
							.collect(Collectors.toSet());
				}
				
				return UserDto.builder()
						.withId(entity.getId())
						.withName(entity.getName())
						.withEmail(entity.getEmail())
						.withPassword(entity.getPassword())
						.withPhones(phones)
						.withCreated(entity.getCreated())
						.withModified(entity.getModified())
						.withLast_login(entity.getLastLogin())
						.withToken(entity.getToken())
						.withIsactive(entity.getIsactive() == 1 ? true : false)
						.build();
			}
		};
	}
	
	private Converter<UserDto, User> userDtoToUser() {
		return new AbstractConverter<UserDto, User>() {

			@Override
			protected User convert(UserDto dto) {
				Set<Phone> phones = new HashSet<>();
				if (!dto.getPhones().isEmpty()) {
					phones = dto.getPhones().stream()
							.map(e -> modelMapper().map(e, Phone.class))
//							.map(p -> {
//								Phone ph = p;
//								ph.setUser(user);
//								return ph;
//							})
							.collect(Collectors.toSet());
				}
				
				return User.builder()
						.withId(dto.getId())
						.withName(dto.getName())
						.withEmail(dto.getEmail())
						.withPassword(dto.getPassword())
						.withPhones(phones)
						.withCreated(dto.getCreated())
						.withModified(dto.getModified())
						.withLastLogin(dto.getLast_login())
						.withToken(dto.getToken())
						.withIsactive(dto.isIsactive() ? 1 : 0)
						.build();
			}
		};
	}
	
	private Converter<User, UserTokenDto> userToUserTokenDto() {
		return new AbstractConverter<User, UserTokenDto>() {

			@Override
			protected UserTokenDto convert(User entity) {
				return UserTokenDto.builder()
						.withName(entity.getName())
						.withEmail(entity.getEmail())
						.withLast_login(entity.getLastLogin())
						.withToken(entity.getToken())
						.build();
			}
		};
	}
	
	private Converter<Phone, PhoneDto> phoneToPhoneDto() {
		return new AbstractConverter<Phone, PhoneDto>() {

			@Override
			protected PhoneDto convert(Phone entity) {
				return PhoneDto.builder()
						.withId(entity.getId())
						.withNumber(entity.getNumber())
						.withCityCode(entity.getCityCode())
						.withCountryCode(entity.getCountryCode())
						.build();
			}
		};
	}
	
	
	private Converter<PhoneDto, Phone> phoneDtoToPhone() {
		return new AbstractConverter<PhoneDto, Phone>() {

			@Override
			protected Phone convert(PhoneDto dto) {
				return Phone.builder()
						.withId(dto.getId())
						.withNumber(dto.getNumber())
						.withCityCode(dto.getCityCode())
						.withCountryCode(dto.getCountryCode())
						.build();
			}
		};
	}
}
