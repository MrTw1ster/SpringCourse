CREATE TABLE `spring_lesson9`.`roles` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

insert into roles (name) values ('ROLE_ADMIN');
insert into roles (name) values ('ROLE_USER');

CREATE TABLE `spring_lesson9`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `spring_lesson9`.`users_roles` (
  `id_user` INT UNSIGNED NOT NULL,
  `id_role` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_user`, `id_role`),
  INDEX `fk_users_roles_role_id_idx` (`id_role` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles_user_id`
    FOREIGN KEY (`id_user`)
    REFERENCES `spring_lesson9`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_users_roles_role_id`
    FOREIGN KEY (`id_role`)
    REFERENCES `spring_lesson9`.`roles` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
