CREATE USER 'webshop'@'localhost';
SET PASSWORD FOR 'webshop'@'localhost' = PASSWORD('webshop');
GRANT ALL ON `webshop`.* TO 'webshop'@'localhost';
