create database if not exists FWRP;
use FWRP;

drop table if exists user;
drop table if exists itemInventory;

create table user(
	id INT primary key auto_increment,
    email VARCHAR(45),
    password VARCHAR(45),
    type VARCHAR(45),
    subscribed VARCHAR(45)
);

insert into user(email, password, type, subscribed) value("qiu@gmail.com", "Aa123456","retailer",false);
insert into user(email, password, type, subscribed) value("le@gmail.com", "Aa123456","consumer",true);
insert into user(email, password, type, subscribed) value("yu@gmail.com", "Aa123456","organization",true);

create table itemInventory(
	id INT primary key auto_increment,
    name varchar(45),
    quantity int,
    expirationDate DATE
);

insert into itemInventory(name, quantity, expirationDate) value("cookie", 100, '2024-10-30');
insert into itemInventory(name, quantity, expirationDate) value("potatp", 500, '2024-04-30');
insert into itemInventory(name, quantity, expirationDate) value("cookie", 100, '2024-12-10');
insert into itemInventory(name, quantity, expirationDate) value("cookie", 300, '2024-03-20');


