CREATE TABLE products (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `cost` FLOAT NULL,
  PRIMARY KEY (`id`));
INSERT INTO `spring_course`.`products` (`title`, `cost`) VALUES
('one', '110'),
('two', '220'),
('three', '330'),
('four', '440'),
('five', '550');

CREATE TABLE users (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
INSERT INTO users (`name`) VALUES
('first user'),
('second user'),
('third user'),
('fourth user');

CREATE TABLE products_users (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  `product_count` SMALLINT NOT NULL,
  `current_cost` FLOAT NOT NULL,
  `current_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_users_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_products_users_product_id_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_users_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `spring_course`.`users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_products_users_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `spring_course`.`products` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
INSERT INTO `spring_course`.`products_users` (`user_id`, `product_id`, `product_count`, `current_cost`, `current_time`) VALUES
('1', '2', '2', '222', now()),
('1', '3', '1', '333', now()),
('1', '4', '1', '444', now()),
('2', '2', '1', '220', now()),
('3', '2', '2', '220', now()),
('3', '4', '1', '440', now());