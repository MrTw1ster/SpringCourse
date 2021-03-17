CREATE TABLE `products` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `cost` float DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `spring_lesson9`.`products` (`title`, `cost`, `created_at`, `updated_at`) VALUES
('one', '110', now(), now()),
('two', '220', now(), now()),
('three', '330', now(), now()),
('four', '440', now(), now()),
('five', '550', now(), now()),
('six', '660', now(), now()),
('seven', '770', now(), now()),
('eight', '880', now(), now()),
('nine', '99', now(), now()),
('ten', '100', now(), now()),
('eleven', '111', now(), now()),
('twelve', '222', now(), now()),
('thirteen', '133', now(), now()),
('fourteen', '144', now(), now()),
('fifteen', '155', now(), now()),
('sixteen', '166', now(), now()),
('seventeen', '177', now(), now()),
('eighteen', '188', now(), now()),
('nineteen', '199', now(), now()),
('twenty', '200', now(), now());

CREATE TABLE `spring_lesson9`.`categories` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `spring_lesson9`.`categories` (`name`) VALUES
('cat_1'),
('cat_2'),
('cat_3'),
('cat_4'),
('cat_5');

CREATE TABLE `spring_lesson9`.`products_categories` (
  `id_product` INT UNSIGNED NOT NULL,
  `id_category` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_product`, `id_category`),
  INDEX `fk_prod_cat_category_id_idx` (`id_category` ASC) VISIBLE,
  CONSTRAINT `fk_prod_cat_product_id`
    FOREIGN KEY (`id_product`)
    REFERENCES `spring_lesson9`.`products` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_prod_cat_category_id`
    FOREIGN KEY (`id_category`)
    REFERENCES `spring_lesson9`.`categories` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);