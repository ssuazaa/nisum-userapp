package com.nisumlatam.userapp.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Phone")
@Table(name = "phones")
@Builder(toBuilder = true, setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@DynamicUpdate
public class Phone {

	@Id
	@Column(name = "phone_id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", insertable = true, updatable = false)
//	@JsonBackReference
//	private User user;

	@Column(name = "phone_number")
	private String number;

	@Column(name = "phone_city_code")
	private String cityCode;

	@Column(name = "phone_country_code")
	private String countryCode;

}
