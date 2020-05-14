CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`users` (
  `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(225) NOT NULL,
  `password` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

INSERT INTO `internet_shop`.`users` (username, password) VALUES
    ('Bob', '123');

CREATE TABLE `products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(225) NOT NULL,
  `price` decimal(20,0) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`product_name`);

CREATE TABLE `internet_shop`.`orders_products` (
  `order_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `order_id_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `product_id_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`shopping_card` (
  `card_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`card_id`),
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`shopping_cards_products` (
  `card_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `card_id_fk_idx` (`card_id` ASC) VISIBLE,
  INDEX `product_id_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `card_id_fk`
    FOREIGN KEY (`card_id`)
    REFERENCES `internet_shop`.`shopping_card` (`card_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`roles` (
  `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(256) NOT NULL,
  PRIMARY KEY (`role_id`);

INSERT INTO `internet_shop`.`roles` (role_name) VALUES
    ('ADMIN');

CREATE TABLE `internet_shop`.`users_roles` (
  `user_id` BIGINT(11) NOT NULL,
  `role_id` BIGINT(11) NOT NULL,
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `users_ids_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `internet_shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO users_roles (user_id, role_id) VALUES
    ((SELECT user_id from users WHERE username = 'Bob'),
    (SELECT role_id FROM roles where role_name = 'ADMIN'));

CREATE TABLE `internet_shop`.`orders` (
  `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `user_id_in_order_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_in_order_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`orders_products` (
  `order_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `order_id_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `product_id_in_order_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id_in_order_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
     
CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(225) NOT NULL,
  `price` decimal(20,0) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`product_name`);
