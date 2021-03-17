CREATE TABLE `spring_lesson9`.`sessions` (
  `guid` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`guid`));

CREATE TABLE `spring_lesson9`.`basket_products` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `session_guid` BINARY(16) NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  `product_count` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_basket_session_id_idx` (`session_guid` ASC) VISIBLE,
  INDEX `fk_basket_product_id_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_basket_session_id`
    FOREIGN KEY (`session_guid`)
    REFERENCES `spring_lesson9`.`sessions` (`guid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_basket_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `spring_lesson9`.`products` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
