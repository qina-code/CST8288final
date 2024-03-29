create database if not exists FWRP;
use FWRP;

drop table if exists transaction;
drop table if exists itemInventory;
drop table if exists user;

create table user(
	id INT primary key auto_increment,
    name VARCHAR(45),
    email VARCHAR(45),
    password VARCHAR(45),
    type VARCHAR(45),
    subscribed VARCHAR(45)
);

insert into user(name, email, password, type, subscribed) value("qiu", "qiu@gmail.com", "Aa123456","retailer",false);
insert into user(name, email, password, type, subscribed) value("le", "le@gmail.com", "Aa123456","consumer",true);
insert into user(name, email, password, type, subscribed) value("yu","yu@gmail.com", "Aa123456","charitable_organization",true);
insert into user(name, email, password, type, subscribed) value("liu", "liu@gmail.com", "Aa123456","retailer",false);

create table itemInventory(
	id INT primary key auto_increment,
    name varchar(45),
    quantity int,
    price decimal(10,2),
    expirationDate DATE,
    owner_id int,
    FOREIGN KEY (owner_id) REFERENCES user(id)
);

insert into itemInventory(name, quantity, price, expirationDate, owner_id) value("cookie", 100, 2.99, '2024-10-30', 1);
insert into itemInventory(name, quantity, price, expirationDate, owner_id) value("potato", 500, 6.99, '2024-04-30', 1);
insert into itemInventory(name, quantity, price, expirationDate, owner_id) value("onion", 100, 3.99, '2024-12-10', 2);
insert into itemInventory(name, quantity, price, expirationDate, owner_id) value("ham", 300, 10.99,  '2024-03-20', 2);

create table transaction(
	id INT primary key auto_increment,
    item_id int,
    quantity int,
    purchaser_id int,
<<<<<<< HEAD
=======
    transaction_time datetime,
>>>>>>> 89057128b54a48d2dba527f0b20e92ac4166ddcc
    FOREIGN KEY (item_id) REFERENCES itemInventory(id),
    FOREIGN KEY (purchaser_id) REFERENCES user(id)
);

<<<<<<< HEAD
insert into transaction(item_id, quantity, purchaser_id) value(1, 50, 2);
insert into transaction(item_id, quantity, purchaser_id) value(2, 20, 2);
insert into transaction(item_id, quantity, purchaser_id) value(3, 30, 3);
insert into transaction(item_id, quantity, purchaser_id) value(4, 50, 3);

select * from user;
select * from itemInventory;
select * from transaction;
=======
insert into transaction(item_id, quantity, purchaser_id,`transaction_time`) value(1, 50, 2, now());
insert into transaction(item_id, quantity, purchaser_id,`transaction_time`) value(2, 20, 2, now());
insert into transaction(item_id, quantity, purchaser_id,`transaction_time`) value(3, 30, 3, now());
insert into transaction(item_id, quantity, purchaser_id,`transaction_time`) value(4, 50, 3, now());
>>>>>>> 89057128b54a48d2dba527f0b20e92ac4166ddcc
