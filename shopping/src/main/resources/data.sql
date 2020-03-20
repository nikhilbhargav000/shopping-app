DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id INT AUTO_INCREMENT  PRIMARY KEY,
	name VARCHAR(250),
	email VARCHAR(250),
	password VARCHAR(250),
	account_type VARCHAR(250)
);



