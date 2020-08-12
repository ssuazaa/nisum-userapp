-- Table: users
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	user_id varchar(255) not null, 
	user_created timestamp, 
	user_email varchar(255), 
	user_isactive integer, 
	user_last_login timestamp, 
	user_modified timestamp, 
	user_name varchar(255), 
	user_password varchar(255), 
	user_token varchar(500), 
	primary key (user_id)
);

-- Table: phones

DROP TABLE IF EXISTS phones CASCADE;

CREATE TABLE phones (
	phone_id varchar(255) not null, 
	phone_city_code varchar(255), 
	phone_country_code varchar(255), 
	phone_number varchar(255),
	user_id varchar(255) not null, 
	primary key (phone_id)
);

ALTER TABLE phones ADD CONSTRAINT FKmg6d77tgqfen7n1g763nvsqe3 FOREIGN KEY (user_id) REFERENCES users;