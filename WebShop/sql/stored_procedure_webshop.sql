USE `webshop`;

-- Add account
DROP PROCEDURE IF EXISTS `add_account`;
DELIMITER $$
CREATE PROCEDURE `add_account` () BEGIN
    INSERT INTO `account` (
        `id`,
        `username`,
	`email`,
	`password`
    ) VALUES (
        in_id,
	in_username,
	in_email,
	in_password
    );
END$$
DELIMITER ;

-- Add cart
DROP PROCEDURE IF EXISTS `add_cart`;
DELIMITER $$
CREATE PROCEDURE `add_cart` (
    IN in_id INT,
    IN in_customer_id INT
)
BEGIN
    INSERT INTO `cart` (
        `id`,
        `customer_id`
    ) VALUES (
        in_id,
        in_customer_id
    );
END$$
DELIMITER ;

-- Add cart product
DROP PROCEDURE IF EXISTS `add_cart_product`;
DELIMITER $$
CREATE PROCEDURE `add_cart_product` (
    IN in_id INT,
    IN in_product_id INT,
    IN in_cart_id INT,
    IN in_count INT
) BEGIN
    INSERT INTO `cart_product` (
        `id`,
        `product_id`,
	`cart_id`,
	`count`
    ) VALUES (
        in_id,
        in_product_id,
	in_cart_id,
	in_count
    );
END$$
DELIMITER ;

-- Add category
DROP PROCEDURE IF EXISTS `add_category`;
DELIMITER $$
CREATE PROCEDURE `add_category` (
    IN in_id INT,
    IN in_name VARCHAR(60)
) BEGIN
    INSERT INTO category (
        `id`,
        `name`
    ) VALUES (
        in_id,
        in_name
    );
END$$
DELIMITER ;

-- Add product count to cart
DROP PROCEDURE IF EXISTS `add_count_to_cart_product`;
DELIMITER $$
CREATE PROCEDURE `add_count_to_cart_product` (
    IN in_id INT,
    IN in_product_id INT,
    IN in_cart_id INT,
    IN in_amount INT
) BEGIN
    UPDATE `cart_product`
    SET `count` = `count` + in_amount
    WHERE
        (`cart_id` = in_cart_id
        AND `product_id` = in_product_id)
	OR
	`id` = in_id;
END$$
DELIMITER ;

-- Add customer
DROP PROCEDURE IF EXISTS `add_customer`;
DELIMITER $$
CREATE PROCEDURE `add_customer` (
    IN in_id INT,
    IN in_account_id INT,
    IN in_session VARCHAR(32)
) BEGIN
    INSERT INTO customer (
        `id`,
        `account_id`,
	`session_id`
    ) VALUES (
        in_id,
        in_account_id,
	in_session_id
    );
END$$
DELIMITER ;

-- Add order
DROP PROCEDURE IF EXISTS `add_order`;
DELIMITER $$
CREATE PROCEDURE `add_order` (
    IN in_id INT,
    IN in_customer_id INT,
    IN in_taxation_id INT
) BEGIN
    INSERT INTO `order` (
        `id`,
        `customer_id`,
	`taxation_id`
    ) VALUES (
        in_id,
        in_account_id,
	in_session_id
    );
END$$
DELIMITER ;

-- Add order product
DROP PROCEDURE IF EXISTS `add_order_product`;
DELIMITER $$
CREATE PROCEDURE `add_order_product` (
    IN in_id INT,
    IN in_product_id INT,
    IN in_order_id INT,
    IN in_count INT
) BEGIN
    INSERT INTO `order_product` (
        `id`,
	`product_id`,
	`order_id`,
	`count`
    ) VALUES (
        in_id,
	in_product_id,
	in_order_id,
	in_count
    );
END $$
DELIMITER ;

-- Add product
DROP PROCEDURE IF EXISTS `add_product`;
DELIMITER $$
CREATE PROCEDURE `add_product` (
    IN in_id INT,
    IN in_name VARCHAR(60),
    IN in_description VARCHAR(1024),
    IN in_price DOUBLE,
    IN in_manafacturer VARCHAR(60)
) BEGIN
    INSERT INTO `product` (
        `id`,
	`name`,
	`description`,
	`price`,
	`manafacturer`
    ) VALUES (
        in_id,
	in_name,
	in_description,
	in_price,
	in_manafacturer
    );
END $$
DELIMITER ;

-- Add product to cart product
DROP PROCEDURE IF EXISTS `add_product_to_cart_product`;
DELIMITER $$
CREATE PROCEDURE `add_product_to_cart_product` (
    IN in_id INT,
    IN in_product_id INT,
    IN in_cart_id INT,
    IN in_count INT
) BEGIN
    INSERT INTO `cart_product` (
        `id`,
	`product_id`,
	`cart_id`,
	`count`
    ) VALUES (
        in_id,
	in_product_id,
	in_cart_id,
	in_count
    );
END $$
DELIMITER ;

-- Delete account by ID
DROP PROCEDURE IF EXISTS `delete_account_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_account_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `account`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete cart by ID
DROP PROCEDURE IF EXISTS `delete_cart_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_cart_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `cart`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete cart product by ID
DROP PROCEDURE IF EXISTS `delete_cart_product_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_cart_product_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `cart_product`
    WHERE `id` = in_id;
END $$
DELIMITER ;


-- Delete category by ID
DROP PROCEDURE IF EXISTS `delete_category_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_category_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `category`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete customer by ID
DROP PROCEDURE IF EXISTS `delete_customer_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_customer_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `customer`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete order by ID
DROP PROCEDURE IF EXISTS `delete_order_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_order_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `order`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete order product by ID
DROP PROCEDURE IF EXISTS `delete_order_product_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_order_product_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `order_product`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete product category by ID
DROP PROCEDURE IF EXISTS `delete_product_category_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_product_category_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `product_category`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete product image by ID
DROP PROCEDURE IF EXISTS `delete_product_image_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_product_image_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `product_image`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete stored product by ID
DROP PROCEDURE IF EXISTS `delete_stored_product_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_stored_product_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `stored_product`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Delete taxation by ID
DROP PROCEDURE IF EXISTS `delete_taxation_by_id`;
DELIMITER $$
CREATE PROCEDURE `delete_taxation_by_id` (
    IN in_id INT
) BEGIN
    DELETE FROM `taxation`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Get account by authentication
DROP PROCEDURE IF EXISTS `get_account_by_authentication`;
DELIMITER $$
CREATE PROCEDURE `get_account_by_authentication` (
    IN in_authentication VARCHAR(60)
) BEGIN
    SELECT * FROM `account`
    WHERE `username` = in_authentication
    OR `email` = in_authentication;
END $$
DELIMITER ;

-- Get all cart products from cart
DROP PROCEDURE IF EXISTS `get_all_cart_products_from_cart`;
DELIMITER $$
CREATE PROCEDURE `get_all_cart_products_from_cart` (
    IN in_cart_id INT
) BEGIN
    SELECT * FROM `cart_product`
    WHERE `cart_id` = in_cart_id;
END $$
DELIMITER ;

-- Get all products from cart
DROP PROCEDURE IF EXISTS `get_all_products_from_cart`;
DELIMITER $$
CREATE PROCEDURE `get_all_products_from_cart` (
    IN in_cart_id INT
) BEGIN
    SELECT * FROM `product` AS t1
    INNER JOIN `cart_product` AS t2
        ON t2.`product_id` = t1.`id`
    WHERE t2.`cart_id` = in_cart_id;
END $$
DELIMITER ;

-- Get category by name
DROP PROCEDURE IF EXISTS `get_category_by_name`;
DELIMITER $$
CREATE PROCEDURE `get_category_by_name` (
    IN in_name VARCHAR(60)
) BEGIN
    SELECT * FROM `category`
    WHERE LCASE(`name`) = LCASE(in_name);
END $$
DELIMITER ;

-- Get orders by customer ID
DROP PROCEDURE IF EXISTS `get_orders_by_customer_id`;
DELIMITER $$
CREATE PROCEDURE `get_orders_by_customer_id` (
    IN in_customer_id INT
) BEGIN
    SELECT * FROM `order`
    WHERE `customer_id` = in_customer_id;
END $$
DELIMITER ;

-- Get order products by order ID
DROP PROCEDURE IF EXISTS `get_order_products_by_order_id`;
DELIMITER $$
CREATE PROCEDURE `get_order_products_by_order_id` (
    IN in_order_id INT
) BEGIN
    SELECT * FROM `order_product`
    WHERE `order_id` = in_order_id;
END $$
DELIMITER ;

-- Get product by ID
DROP PROCEDURE IF EXISTS `get_product_by_id`;
DELIMITER $$
CREATE PROCEDURE `get_product_by_id` (
    IN in_id INT
) BEGIN
    SELECT * FROM `product`
    WHERE `id` = in_id;
END $$
DELIMITER ;

-- Get product by name
DROP PROCEDURE IF EXISTS `get_product_by_name`;
DELIMITER $$
CREATE PROCEDURE `get_product_by_name` (
    IN in_name VARCHAR(60)
) BEGIN
    SELECT * FROM `product`
    WHERE LCASE(`name`) = in_name;
END $$
DELIMITER ;

-- Get product by price
DROP PROCEDURE IF EXISTS `get_product_by_price`;
DELIMITER $$
CREATE PROCEDURE `get_product_by_price` (
    IN in_price INT
) BEGIN
    SELECT * FROM `product`
    WHERE `price` = in_price;
END $$
DELIMITER ;

-- Check if account exists
DROP PROCEDURE IF EXISTS `account_exists`;
DELIMITER $$
CREATE PROCEDURE `account_exists` (
    IN in_authentication VARCHAR(60)
) BEGIN
	SELECT COUNT(*) AS `1`
	FROM `account`
	WHERE LCASE(`username`) = LCASE(in_username)
	    OR LCASE(`email`) = LCASE(in_authentication)
        LIMIT 1;
END$$
DELIMITER ;
