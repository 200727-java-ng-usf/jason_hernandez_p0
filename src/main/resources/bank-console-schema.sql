DROP TABLE IF EXISTS user_accounts;
drop table if exists user_credentials;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS savings_accounts;
DROP TABLE IF EXISTS checking_accounts;


CREATE TABLE users(
	id 				serial,
	first_name 		varchar(25) not null,
	last_name 		varchar(25) not null,
	email			varchar(320) not null,
	
	constraint user_pk
	primary key(id)
);

create table savings_accounts(
	account_number	serial,
	balance			float,
	interest_rate	float,
	
	constraint savings_pk
	primary key(account_number)
);

create table checking_accounts(
	account_number	serial,
	balance			float,
	
	constraint checking_pk
	primary key(account_number)
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

create table user_accounts(
	id 				serial,
	user_id 		int,
	savings_number 	int, 
	checking_number int,
	
	constraint account_pk
	primary key(id),
	
	constraint account_fk_1
	foreign key(user_id)
	references users 
	on delete cascade
	on update cascade,
	
	constraint account_fk_2
	foreign key(savings_number)
	references savings_accounts
	on delete cascade
	on update cascade,
	
	constraint account_fk_3
	foreign key(checking_number)
	references checking_accounts
	on delete cascade
	on update cascade
);

insert into users(first_name, last_name, email) 
values 	('Bobby', 'Tables', 'droptables@xkcd.com'),
		('Allison', 'Wonderland', 'alice@rabbithole.net');
		
insert into user_credentials (user_id, username, password)
values	(1, 'droptables', 'xkcd'),
		(2, 'awonderland', 'whitrbbit');
		
insert into savings_accounts (balance, interest_rate)
values	(500, 0.03),
		(754, 0.05);
		
insert into checking_accounts (balance) 
values	(550);

insert into user_accounts (user_id, savings_number, checking_number)
values	(2, 1, null),
		(1, 2, 1);
	
select u.first_name, u.last_name, sa.account_number, sa.balance 
from users u
join user_accounts ua
on ua.user_id = u.id 
join savings_accounts sa 
on sa.account_number = ua.savings_number;

commit;

alter table user_accounts 
drop constraint account_fk_1;

alter table user_accounts 
drop constraint account_fk_2;

alter table user_accounts 
drop constraint account_fk_3;

alter table user_accounts 
add constraint account_fk_1 
foreign key(user_id)
	references users
	on delete cascade 
	on update cascade,
	
add constraint account_fk_2
foreign key(savings_number)
	references savings_accounts
	on delete cascade 
	on update cascade,
	
add constraint account_fk_2
foreign key(checking_number)
	references checking_accounts
	on delete cascade 
	on update cascade;
