DROP SCHEMA IF EXISTS `securde_library`;
CREATE SCHEMA `securde_library`;
USE `securde_library`;

CREATE TABLE `users` (
	`user_id` 			INT NOT NULL AUTO_INCREMENT,
    `username` 			VARCHAR(32) NOT NULL,
    `password` 			VARCHAR(128) NOT NULL,
    `role` 				ENUM('STUDENT', 'FACULTY', 'MANAGER', 'STAFF', 'ADMINISTRATOR') NOT NULL,
    
    `id_number` 		VARCHAR(16),
    `email` 			VARCHAR(64),
	`first_name` 		VARCHAR(64),
    `middle_initial` 	VARCHAR(5),
    `last_name` 		VARCHAR(64),    
    `birthday` 			DATE,
    `secret_question` 	VARCHAR(128),
    `secret_answer` 	VARCHAR(128),
    
    `temp` 				TINYINT(1) NOT NULL DEFAULT 0,
    
    `active`			TINYINT(1) NOT NULL DEFAULT 1,
    `date_time_created` DATETIME NOT NULL,
    
    PRIMARY KEY(`user_id`)
);

/*
CREATE TABLE `admins` (
	`admin_id` 		INT NOT NULL AUTO_INCREMENT,
    `username` 		VARCHAR(32) NOT NULL,
    `password` 		VARCHAR(128) NOT NULL,
    `admin_type` 	ENUM('MANAGER', 'STAFF', 'ADMINISTRATOR'),
    
    PRIMARY KEY (`admin_id`)
);

CREATE TABLE `temp_admins` (
	`temp_admin_id` 		INT NOT NULL AUTO_INCREMENT,
    `username` 				VARCHAR(32) NOT NULL,
    `password` 				VARCHAR(128) NOT NULL,
    `admin_type` 			ENUM('MANAGER', 'STAFF', 'ADMINISTRATOR'),
    `date_time_created` 	DATETIME NOT NULL DEFAULT current_timestamp,
    
    PRIMARY KEY (`temp_admin_id`)
);

CREATE TABLE `roles` (
	`username` VARCHAR(32) NOT NULL,
    `role` ENUM('STUDENT', 'FACULTY', 'MANAGER', 'STAFF', 'ADMINISTRATOR') NOT NULL
);



DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `set_roles_users`$$
CREATE TRIGGER `set_roles_users` AFTER INSERT ON `users` FOR EACH ROW
BEGIN
	INSERT INTO `roles` VALUES (NEW.username, NEW.user_type);
END$$
DELIMITER ;


DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `set_roles_admins`$$
CREATE TRIGGER `set_roles_admins` AFTER INSERT ON `admins` FOR EACH ROW
BEGIN
	INSERT INTO `roles` VALUES (NEW.username, NEW.admin_type);
END$$
DELIMITER ;

DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `set_roles_temp_admins`$$
CREATE TRIGGER `set_roles_temp_admins` AFTER INSERT ON `temp_admins` FOR EACH ROW
BEGIN
	INSERT INTO `roles` VALUES (NEW.username, NEW.admin_type);
END$$
DELIMITER ;

DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `remove_roles_temp_admins`$$
CREATE TRIGGER `remove_roles_temp_admins` BEFORE DELETE ON `temp_admins` FOR EACH ROW
BEGIN
	DELETE FROM `roles` WHERE OLD.username = roles.username;
END$$
DELIMITER ;
*/

CREATE TABLE `texts` (
	`text_id` 		INT NOT NULL AUTO_INCREMENT,
    `title` 		VARCHAR(128) NOT NULL,
    `location` 		VARCHAR(128) NOT NULL,
    `author` 		VARCHAR(128) NOT NULL,
    `publisher` 	VARCHAR(128),
    `year`			VARCHAR(4) NOT NULL,
    `tags` 			VARCHAR(255),
    `status` 		ENUM('OUT', 'RESERVED', 'AVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
    
    PRIMARY KEY (`text_id`)
);

CREATE TABLE `rooms` (
	`room_id` 	INT NOT NULL AUTO_INCREMENT,
    `name` 		VARCHAR(64) NOT NULL,
    `status` 	ENUM('RESERVED', 'AVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
    
    PRIMARY KEY (`room_id`)
);

CREATE TABLE `text_reservations` (
	`text_reservation_id` 		INT NOT NULL AUTO_INCREMENT,
    `user_id` 					INT NOT NULL,
    `text_id` 					INT NOT NULL,
    `reservation_date_time` 	DATETIME NOT NULL,
    
    PRIMARY KEY (`text_reservation_id`),
    
	FOREIGN KEY (`user_id`)
        REFERENCES `users`(`user_id`),
	FOREIGN KEY (`text_id`)
        REFERENCES `texts`(`text_id`)
);

CREATE TABLE `room_reservations` (
	`room_reservation_id` 		INT NOT NULL AUTO_INCREMENT,
    `user_id` 					INT NOT NULL,
    `room_id` 					INT NOT NULL,
    `reservation_date` 			DATE NOT NULL,
    `reservation_start_time` 	TIME NOT NULL,
    `reservation_end_time` 		TIME NOT NULL,
    
    PRIMARY KEY (`room_reservation_id`),
    
	FOREIGN KEY (`user_id`)
        REFERENCES `users`(`user_id`),
	FOREIGN KEY (`room_id`)
        REFERENCES `rooms`(`room_id`)
);

DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `default_date_time_text_users`$$
CREATE TRIGGER `default_date_time_text_users` BEFORE INSERT ON `users` FOR EACH ROW
	IF ( isnull(NEW.date_time_created) ) THEN
		SET NEW.date_time_created=NOW();
	END IF;
$$
delimiter ;

DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `default_date_time_text_reservations`$$
CREATE TRIGGER `default_date_time_text_reservations` BEFORE INSERT ON `text_reservations` FOR EACH ROW
	IF ( isnull(NEW.reservation_date_time) ) THEN
		SET NEW.reservation_date_time=NOW();
	END IF;
$$
delimiter ;

DELIMITER $$
USE `securde_library`$$
DROP TRIGGER IF EXISTS `default_date_room_reservations`$$
CREATE TRIGGER `default_date_room_reservations` BEFORE INSERT ON `room_reservations` FOR EACH ROW
	IF ( isnull(NEW.reservation_date) ) THEN
		SET NEW.reservation_date=CURDATE();
	END IF;
$$
delimiter ;

/* Initial data*/

INSERT INTO `rooms` (`name`)
VALUES 
	('Room 1'),
    ('Room 2'), 
    ('Room 3'), 
    ('Room 4'), 
    ('Room 5');

INSERT INTO `texts`(`title`, `location`, `author`, `publisher`, `year`, `tags`)
VALUES
	('Introduction to Algorithms', 'Sciences', 'Cormen', 'MIT Press', '1990', 'computer science;algorithms'),
    ('Mistborn', 'Fiction', 'Brandon Sanderson', 'TOR', '2000', 'fiction'),
    ('The Well of Ascension', 'Fiction', 'Brandon Sanderson', 'TOR', '2003', 'fiction'),
    ('The Hero of Ages', 'Fiction', 'Brandon Sanderson', 'TOR', '2006', 'fiction');

INSERT INTO `users`(`role`, `password`, `username`)
VALUES
	('ADMINISTRATOR', '$2a$10$590se6hcQjWKT5POucM1zO0rtWzk/VMZK4lJezJYTTfSGhWnqG012', 'admin');