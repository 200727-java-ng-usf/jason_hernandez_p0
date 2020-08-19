DROP TABLE IF EXISTS user_accounts;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS savings_accounts;
DROP TABLE IF EXISTS checking_accounts;

CREATE TABLE users(
	id 				serial,
	first_name 		varchar(25) not null,
	last_name 		varchar(25) not null,
	username 		varchar(25) unique not null, 
	password 		varchar(100) not null
);

commit;