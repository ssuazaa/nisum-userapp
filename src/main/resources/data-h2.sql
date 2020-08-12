INSERT INTO users (
	user_id, 
	user_created, 
	user_email, 
	user_isactive, 
	user_last_login, 
	user_modified, 
	user_name, 
	user_password, 
	user_token
) VALUES (
	'3a32684e-dc23-11ea-87d0-0242ac130003', 
	CURRENT_TIMESTAMP(), 
	'suso@domain.cl', 
	1, 
	CURRENT_TIMESTAMP(), 
	null, 
	'Juan Sebastian', 
	'SuSo08', 
	null
);
	
INSERT INTO phones (
	phone_id, 
	phone_city_code, 
	phone_country_code, 
	phone_number, 
	user_id
) VALUES (
	'3a32684e-dc23-11ea-87d0-0242ac130001', 
	'4', 
	'57', 
	'3002187376',
	'3a32684e-dc23-11ea-87d0-0242ac130003'
), (
	'3a32684e-dc23-11ea-87d0-0242ac130002', 
	'4', 
	'57', 
	'3002187378',
	'3a32684e-dc23-11ea-87d0-0242ac130003'
);