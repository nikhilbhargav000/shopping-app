
DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id VARCHAR(250) PRIMARY KEY,
	name VARCHAR(250),
	email VARCHAR(250) UNIQUE, 
	password VARCHAR(250),
	account_type VARCHAR(250)
);

INSERT INTO users (
	user_id,
	name,
	email,
	password,
	account_type
) VALUES 
( 'd47740c7-ca62-44ff-a6cf-722c50ba6b55', 'Nikhil Bhargav', 'nikhil.bhargav01@infosys.com','Abcd#123', 'REGESTERED' ), 
( 'e253f4fd-e66a-46e4-b6d2-6204fb855b47', 'Aman Gahir', 'aman.gahir01@infosys.com', 'Abcd#123', 'REGESTERED' );


INSERT INTO users (
	user_id,
	account_type
) VALUES 
( 'e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e', 'GUEST' ), 
( 'ed850e28-03fa-4f40-86ea-96f73d393ad2', 'GUEST' );






DROP TABLE IF EXISTS products;
CREATE TABLE products (
	product_name VARCHAR(500) PRIMARY KEY,
	display_name VARCHAR(250) NOT NULL,
	short_desc VARCHAR(500) NOT NULL,
	description CLOB ,
	category VARCHAR(250) NOT NULL,
	price DOUBLE NOT NULL,
	discount DOUBLE NOT NULL,
	delivery_charges DOUBLE NOT NULL
);

INSERT INTO products (
	product_name,
	display_name,
	short_desc,
	description,
	category,
	price,
	discount,
	delivery_charges
) VALUES 	
('Apple iphoneX 256GB black', 'Iphone X', 'Apple smart phone', 'This is one of the premimum mobile by Apple', 
	'Mobile Phones', 60000.0, 5000.0, 0.0),
('Apple TV 256GB silver', 'Apple TV', 'Apple smart tv', 'This is one of the premimum smart tv by Apple', 
	'Electronics', 90000.0, 12000.0, 500.0);


	
	
	
DROP TABLE IF EXISTS wish_lists;
CREATE TABLE wish_lists (
	user_id VARCHAR(250) NOT NULL REFERENCES users(user_id),
	product_name VARCHAR(500) NOT NULL REFERENCES products(product_name),
	display_name VARCHAR(250) NOT NULL,
	short_desc VARCHAR(500) NOT NULL,
	category VARCHAR(250) NOT NULL,
	PRIMARY KEY(user_id, product_name)
);
