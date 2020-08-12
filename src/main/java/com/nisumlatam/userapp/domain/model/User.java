package com.nisumlatam.userapp.domain.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Builder(toBuilder = true, setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_password")
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Phone> phones;

	@Column(name = "user_created")
	private LocalDateTime created;

	@Column(name = "user_modified")
	private LocalDateTime modified;

	@Column(name = "user_last_login")
	private LocalDateTime lastLogin;

	@Column(name = "user_token")
	private String token;

	@Column(name = "user_isactive")
	private int isactive;

}
