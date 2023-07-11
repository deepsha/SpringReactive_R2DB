create table customer
(
id long generated always as identity primary key,
firstname varchar(50) not null,
lastname varchar(50) 
);