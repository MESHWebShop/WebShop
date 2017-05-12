USE `webshop`;

-- Separerade MySQL schemat från dummy datan /Emil

INSERT INTO `account` VALUES (1001,'Sven','nisse@hult.se','passssword'),(5551001,'Sven','nisse@hult.se','passssword');
INSERT INTO `cart` VALUES (1,5001);
INSERT INTO `category` VALUES (4001,'Pennor');
INSERT INTO `customer` VALUES (5001,5551001,'session');
INSERT INTO `order` VALUES (1,5001,1);
INSERT INTO `product` VALUES (2,'skruvar','grunker och grejjer att fästa med',50,'grabber'),(3,'bandsåg','ett band som sågar',800,'DeValt'),(4,'kopp','att ta i ',45,'koppman');
INSERT INTO `taxation` VALUES (1,'Food',1.25);

-- Från inser(ts?).sql

/*
INSERT into account (id, username,email,password )
values (1001, 'Sven','nisse@hult.se','passssword');

INSERT into category(id,name)
values ( 4001,'Pennor');

INSERT into customer ( id,account_id,session)
values ( 5001,551001,'session');

INSERT into cart (id,customer_id)
values ( 2001, 5552001);

INSERT into order ( order_id, customer_id,taxation_id)
values (6001,5556001,999);

INSERT into cart_product( id,product_id,cart_id,count)
values ( 3001,55530011,5553001,1);

INSERT  into order_product(id,product_id,order_id,count)
values (7001,5557001,600,6);

INSERT into product (id,name,description,price,manufacture)
values (8001,'vindsurfing',5000,'Alfa');

INSERT into product_category(id,product_id,category_id)
values (9001.5559001,666401);

INSERT into product_image (id,product_id,image_url,type)
values (10001,5558002,/image,f);

INSERT into stored_product (id,product_id,count)
values (110001, 5558880,4);

INSERT into taxation(id,name,factor)
values ( 120001,'sprit',1.25);
*/