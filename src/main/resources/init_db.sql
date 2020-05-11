CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`users` (
  `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(225) NOT NULL,
  `password` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(225) NOT NULL,
  `price` decimal(20,0) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`product_name`);