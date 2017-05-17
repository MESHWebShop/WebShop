DROP PROCEDURE IF EXISTS `add_account`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_account`(
	IN id int(11),
    IN username varchar(60),
    in email varchar(60),
    in password varchar(60)
)
BEGIN
	INSERT INTO account
    VALUES (null,username,email,password);
END$$
DELIMITER ;




DROP PROCEDURE IF EXISTS `add_cart`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_cart`(
	IN id int(11),
    IN customer_id int(11)
)
BEGIN
	INSERT INTO cart
    VALUES (null,customer_id);
END$$
DELIMITER ;




DROP PROCEDURE IF EXISTS `add_cart_product`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_cart_product`(
	 
    IN id int(11),
	IN product_id int(11),
    in cart_id int(11),
	in cout int (11)
)
BEGIN
	INSERT INTO cart_product
    VALUES (null,product_id,cart_id,count);
END$$
DELIMITER ;





DROP PROCEDURE IF EXISTS `add_category`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_category`(
	IN id int(11),
    IN name varchar(60)
)
BEGIN
	INSERT INTO category
    VALUES (id,name);
END$$
DELIMITER ;




DROP PROCEDURE IF EXISTS `add_count_to_cart_product`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_count_to_cart_product`(
	
    IN product_id int(11),
    IN cart_id int(11),
    IN amount int(11),
    IN count int (11)
   
 )
BEGIN
    UPDATE cart_product
    set count= amount+count     
    where cart_product.id = cart_id;    
    
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `add_customer`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_customer`(
	IN id int(11),
    IN account_id int(11),
    IN session varchar(32)   
)
BEGIN
	INSERT INTO customer
    VALUES (id,account_id,session);
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `add_order`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_order`(
	IN id int(11),
    IN customer_id int(11),
    in taxation_id int (11)
)
BEGIN
	INSERT INTO webshop.order
    VALUES (id,customer_id,taxation_id);
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `add_order_product`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_order_product`(
	IN id int(11),
    IN product_id int(11),
    IN order_id int(11),
    IN count int(11)
)
BEGIN
	INSERT INTO order_product
    VALUES (id,product_id,order_id,count);
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `add_product`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_product`(
	IN id int(11),
    IN name varchar(60),
    IN description varchar(1024),
    IN price double,
    in manufacturer varchar(60)
)
BEGIN
	INSERT INTO product
    VALUES (id,name,description,price,manufacturer);
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `add_product_to_cart_product`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_product_to_cart_product`(
	
    IN product_id int(11),
    IN cart_id int(11),
    IN count int(11) 

)
BEGIN
	INSERT INTO cart_product
    VALUES (null,product_id,cart_id,count);
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `delete_account_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_account_by_id`(IN id int(11))
BEGIN
	DELETE FROM account
    WHERE product.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `delete_cart_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_cart_by_id`(IN id int(11))
BEGIN
	DELETE FROM cart
    WHERE cart.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `delete_cart_product_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_cart_product_by_id`(IN id int(11))
BEGIN
	DELETE FROM cart_product
    WHERE cart_product.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `delete_category_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_category_by_id`(IN id int(11))
BEGIN
	DELETE FROM category
    WHERE category.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `delete_customer_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_customer_by_id`(IN id int(11))
BEGIN
	DELETE FROM customer
    WHERE customer.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `delete_order_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_order_by_id`(IN id int(11))
BEGIN
	DELETE FROM webshop.order
    WHERE order.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `delete_order_product_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_order_product_by_id`(IN id int(11))
BEGIN
	DELETE FROM order_product
    WHERE order_product.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `delete_product_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_product_by_id`(IN id int(11))
BEGIN
	DELETE FROM product
    WHERE product.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `delete_product_category_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_product_category_by_id`(IN id int(11))
BEGIN
	DELETE FROM product_category
    WHERE product_category.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `delete_product_image_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_product_image_by_id`(IN id int(11))
BEGIN
	DELETE FROM product_image
    WHERE product_image.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `delete_stored_product_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_stored_product_by_id`(IN id int(11))
BEGIN
	DELETE FROM stored_product
    WHERE stored_product.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `delete_taxation_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_taxation_by_id`(IN id int(11))
BEGIN
	DELETE FROM taxation
    WHERE taxation.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `get_account_by_email`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_account_by_email`(IN username varchar(60))
BEGIN
	SELECT * FROM account
    WHERE account.email=email
    
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `get_account_by_username`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_account_by_username`(IN username varchar(60))
BEGIN
	SELECT * FROM account
    WHERE account.username=username
    
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `get_all_products_from_cart`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_products_from_cart`()
BEGIN    
SELECT cart_product.product_id, product.id
FROM product
INNER JOIN cart_product
ON cart_product.product_id= product.id;    
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `get_all_products_from_cart_product`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_products_from_cart_product`(IN cart_id int(11))
BEGIN
SELECT product.id ,product.name, product.description, product.price, product.manufacturer,cart_product.count
FROM product
INNER JOIN cart_product
ON cart_product.product_id= product.id;   
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `get_category_by_name`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_category_by_name`(IN name varchar(60))
BEGIN
	SELECT * FROM category
    WHERE category.name=name
    
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `get_order_by_customer_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_order_by_customer_id`(
IN customer_id int(11))
BEGIN
	SELECT * FROM webshop.order
    WHERE customer_id=customer_id
    
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `get_order_product_by_order_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_order_product_by_order_id`(IN order_id int(11))
BEGIN
	SELECT * FROM order_product
    WHERE order_id=order_id
    
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `get_prod`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_prod`(IN product_id int(11))
BEGIN
	SELECT * FROM product
    WHERE product= (select product_id from cart_product);   

END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `get_product_by_id`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_product_by_id`(
IN id int(11)
)
BEGIN
	SELECT * FROM product
    WHERE product.id=id
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `get_product_by_name`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_product_by_name`(IN name VARCHAR(60))
BEGIN
	SELECT * FROM product
    WHERE product.name=name
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `get_product_by_price`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_product_by_price`(IN price double)
BEGIN
	SELECT * FROM product
    WHERE product.price=price
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS `get_product_from_cart`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_product_from_cart`(IN id int(11))
BEGIN
SELECT  product.id ,product.name, product.description, product.price, product.manufacturer
FROM cart_product
INNER join product
ON cart_product.product_id=product_id
where cart_id=id
    
    COLLATE utf8_swedish_ci;
END$$
DELIMITER ;























