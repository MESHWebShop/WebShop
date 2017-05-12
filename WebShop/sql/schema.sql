CREATE DATABASE IF NOT EXISTS `webshop`;

USE `webshop`;

CREATE TABLE `webshop`.`account` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(60) NOT NULL,
`email` VARCHAR(60) NOT NULL,
`password` VARCHAR(60) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `webshop`.`customer` (
`id` INT NOT NULL AUTO_INCREMENT,
`account_id` INT NULL,
`session` VARCHAR(32) NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `uix_customer_session` (`session`),
CONSTRAINT `fk_customer_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`category` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(60) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `webshop`.`product` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(60) NOT NULL,
`description` VARCHAR(1024) NOT NULL,
`price` DOUBLE NOT NULL,
`manafacturer` VARCHAR(60),
PRIMARY KEY (`id`)
);

CREATE TABLE `webshop`.`taxation` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` INT NULL,
`factor` DOUBLE NULL DEFAULT 1,
PRIMARY KEY (`id`)
);

CREATE TABLE `webshop`.`product_image` (
`id` INT NOT NULL AUTO_INCREMENT,
`product_id` INT NOT NULL,
`image_url` VARCHAR(255) NOT NULL,
`type` CHAR(1) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `fk_product_image_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`cart` (
`id` INT NOT NULL AUTO_INCREMENT,
`customer_id` INT NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `fk_cart_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`cart_product` (
`id` INT NOT NULL AUTO_INCREMENT,
`product_id` INT NOT NULL,
`cart_id` INT NOT NULL,
`count` INT NOT NULL DEFAULT 1,
PRIMARY KEY (`id`),
CONSTRAINT `fk_cart_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_cart_product_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`stored_product` (
`id` INT NOT NULL AUTO_INCREMENT,
`product_id` INT NOT NULL,
`count` INT NULL DEFAULT 0,
PRIMARY KEY (`id`),
CONSTRAINT `fk_stored_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`order` (
`id` INT NOT NULL AUTO_INCREMENT,
`customer_id` INT NOT NULL,
`taxation_id` INT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `fk_order_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_order_taxation_id` FOREIGN KEY (`taxation_id`) REFERENCES `taxation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`order_product` (
`id` INT NOT NULL AUTO_INCREMENT,
`product_id` INT NOT NULL,
`order_id` INT NOT NULL,
`count` INT NOT NULL DEFAULT 1,
PRIMARY KEY (`id`),
CONSTRAINT `fk_order_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_order_product_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `webshop`.`product_category` (
`id` INT NOT NULL AUTO_INCREMENT,
`product_id` INT NOT NULL,
`category_id` INT NOT NULL,
PRIMARY KEY(`id`),
CONSTRAINT `fk_product_category_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_product_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
