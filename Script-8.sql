select * from user;
select * from Reimbursement;

create table Reimbursement(
	id int,
	amount float(24),
	submitted Timestamp,
	resolved Timestamp,
	description text,
	author int,
	resolver int,
	status_id int,
	type_id int
);

insert into Reimbursement values(1, 220, '2020-01-01 10:10:10', '2020-01-02 12:12:12', 'Hello World', 1 ,2,3,4) ;

create table user()

select * from reimbursement;

create table ers_users(
	user_id int,
	username text,
	password text,
	firstname text,
	lastname text,
	email text,
	role_id int
);

drop table ers_users;

insert into ers_users values( 1, 'danrey321', 'password', 'Daniel', 'Reyes', 'dane@gmail.com', 100);

select * from ers_users;

SELECT * FROM ers_users;