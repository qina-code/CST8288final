create database if not exists FWRP;
use FWRP;

drop table if exists transaction;
drop table if exists itemInventory;
drop table if exists location;
drop table if exists preference;
drop table if exists user;
drop table if exists category;



create table category(
	id INT primary key auto_increment,
    name VARCHAR(45)
);

insert into category(name) value("meat");
insert into category(name) value("seafood");
insert into category(name) value("vegetables");
insert into category(name) value("fruits");
insert into category(name) value("grains");
insert into category(name) value("seasonings");
insert into category(name) value("cans");
insert into category(name) value("snacks");
insert into category(name) value("others");

create table user(
	id INT primary key auto_increment,
    name VARCHAR(45),
    email VARCHAR(45),
    password VARCHAR(45),
    type VARCHAR(45),
    subscribed VARCHAR(45)
);

insert into user(name, email, password, type, subscribed) value("qiu", "qiu@gmail.com", "Aa123456","retailer", false);
insert into user(name, email, password, type, subscribed) value("le", "le@gmail.com", "Aa123456","consumer",true);
insert into user(name, email, password, type, subscribed) value("yu","yu@gmail.com", "Aa123456","charitable_organization",true);
insert into user(name, email, password, type, subscribed) value("liu", "liu@gmail.com", "Aa123456","retailer",false);
insert into user(name, email, password, type, subscribed) value("real", "le000149@algonquinlive.com", "Aa123456","consumer",true);


create table location(
	id INT primary key auto_increment,
    city VARCHAR(45),
    postal_code VARCHAR(7),
    user_id int,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into location(city, postal_code, user_id) value("Ottawa", "K2C 3D3", 1);
insert into location(city, postal_code, user_id) value("Ottawa", "K6N 1G3",2);
insert into location(city, postal_code, user_id) value("Montreal", "H1C 4G1", 3);
insert into location(city, postal_code, user_id) value("Toronto", "M2C 4G1",4);
insert into location(city, postal_code, user_id) value("Ottawa", "K2N 1G3", 5);

create table preference(
	id INT primary key auto_increment,
    category_id int,
    user_id int,
	FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into preference(category_id, user_id) value(1, 2);
insert into preference(category_id, user_id) value(2, 2);
insert into preference(category_id, user_id) value(3, 2);
insert into preference(category_id, user_id) value(4, 2);


create table itemInventory(
	id INT primary key auto_increment,
    name varchar(45),
    quantity int,
    price decimal(10,2),
    discount int default 1,
    expirationDate DATE,
    donate VARCHAR(45) default false,
    owner_id int,
    category_id int,
    FOREIGN KEY (owner_id) REFERENCES user(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
    
);

insert into itemInventory(name, quantity, price, expirationDate, donate, owner_id, category_id) value("cookie", 100, 2.99, '2024-04-03', true, 1, 8);
insert into itemInventory(name, quantity, price, expirationDate, donate, owner_id, category_id) value("potato", 500, 6.99, '2024-04-06',true, 1, 3);
insert into itemInventory(name, quantity, price, expirationDate, donate, owner_id, category_id) value("onion", 100, 3.99, '2024-06-01', false, 2, 3);
insert into itemInventory(name, quantity, price, discount, expirationDate, owner_id, category_id) value("ham", 300, 10.99, 0.3, '2024-04-30', 2, 7);
insert into itemInventory(name, quantity, price, discount, expirationDate, owner_id, category_id) value("apple", 300, 10.99, 0.7,  '2024-04-05', 2, 4);

create table transaction(
	id INT primary key auto_increment,
    order_id int,
    item_id int,
    quantity int,
    purchaser_id int,
    transaction_time datetime,
    FOREIGN KEY (item_id) REFERENCES itemInventory(id),
    FOREIGN KEY (purchaser_id) REFERENCES user(id)
);


insert into transaction(order_id, item_id, quantity, purchaser_id,`transaction_time`) value(1,1, 50, 2, now());
insert into transaction(order_id,item_id, quantity, purchaser_id,`transaction_time`) value(1,2, 20, 2, now());
insert into transaction(order_id,item_id, quantity, purchaser_id,`transaction_time`) value(2,5, 30, 3, now());
insert into transaction(order_id,item_id, quantity, purchaser_id,`transaction_time`) value(3,5, 30, 5, now());

