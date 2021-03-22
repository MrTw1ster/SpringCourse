CREATE TABLE `ht11`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `score` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));


CREATE TABLE `ht11`.`roles` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ht11`.`users_roles` (
  `user_id` INT UNSIGNED NOT NULL,
  `role_id` SMALLINT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_users_roles_users_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles_users_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ht11`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_roles_roles_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `ht11`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into roles (name)
values
('ROLE_USER');

insert into users (username, password, score)
values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 100),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 222),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 333),
('user4', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 444);

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 1),
(3, 1),
(4, 1);