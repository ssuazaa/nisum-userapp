-- Table: phones
DROP TABLE IF EXISTS phones CASCADE;

CREATE TABLE phones (
	phone_id 			VARCHAR(255) NOT NULL, 
	phone_city_code 	VARCHAR(255), 
	phone_country_code 	VARCHAR(255), 
	phone_number 		VARCHAR(255), 
	user_id 			VARCHAR(255), 
	PRIMARY KEY (phone_id)
);

-- Table: users
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	user_id 		VARCHAR(255) NOT NULL, 
	user_created 	TIMESTAMP, 
	user_email 		VARCHAR(255), 
	user_isactive 	INTEGER, 
	user_last_login TIMESTAMP, 
	user_modified 	TIMESTAMP, 
	user_name 		VARCHAR(255), 
	user_password 	VARCHAR(255), 
	user_token 		VARCHAR(510), 
	PRIMARY KEY (user_id)
);

-- Alter Table: phones
ALTER TABLE phones ADD CONSTRAINT phone_user_fk FOREIGN KEY (user_id) REFERENCES users;