DROP TABLE IF EXISTS user_accounts;
drop table if exists user_credentials;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS savings_accounts;
DROP TABLE IF EXISTS checking_accounts;


CREATE TABLE users(
	id 				serial,
	first_name 		varchar(25) not null,
	last_name 		varchar(25) not null,
	
	constraint user_pk
	primary key(id)
);

create table user_credentials(
	id 				serial,
	user_id 		int,
	username 		varchar(25) unique not null, 
	password 		varchar(100) not null,
	
	constraint cred_pk
	primary key(id),
	
	constraint cred_fk
	foreign key(user_id)
	references users
);

commit;