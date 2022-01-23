drop table if exists cats;

create table cats(
	cat_id int not null auto_increment,
	cat_name varchar (20),
	primary key (cat_id)
);

