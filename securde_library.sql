DROP SCHEMA IF EXISTS `securde_library`;
CREATE SCHEMA `securde_library`;
USE `securde_library`;

CREATE TABLE `users` (
	`user_id` 			INT NOT NULL AUTO_INCREMENT,
    `username` 			VARCHAR(16) NOT NULL,
    `password` 			VARCHAR(128) NOT NULL,
    `role` 				ENUM('STUDENT', 'FACULTY', 'MANAGER', 'STAFF', 'ADMINISTRATOR') NOT NULL DEFAULT 'STUDENT',
    
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

CREATE TABLE `texts` (
	`text_id` 		INT NOT NULL AUTO_INCREMENT,
    `title` 		VARCHAR(128) NOT NULL,
    `location` 		VARCHAR(128) NOT NULL,
    `author` 		VARCHAR(128) NOT NULL,
    `publisher` 	VARCHAR(128),
    `year`			VARCHAR(4) NOT NULL,
    `type` 			ENUM('BOOK', 'THESIS', 'MAGAZINE') NOT NULL DEFAULT 'BOOK',
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
    `reservation_start_date` 	DATE NOT NULL,
    `reservation_end_date` 		DATE NOT NULL,
    
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
	('Introduction to Algorithms', 'Sciences', 'Cormen', 'MIT Press', '1990', 'computer science,algorithms'),
    ('Mistborn', 'Fiction', 'Brandon Sanderson', 'TOR', '2000', 'fiction'),
    ('The Well of Ascension', 'Fiction', 'Brandon Sanderson', 'TOR', '2003', 'fiction'),
    ('The Hero of Ages', 'Fiction', 'Brandon Sanderson', 'TOR', '2006', 'fiction');

INSERT INTO `users`(`role`, `password`, `username`)
VALUES
	('ADMINISTRATOR', '$2a$10$590se6hcQjWKT5POucM1zO0rtWzk/VMZK4lJezJYTTfSGhWnqG012', 'admin'),
    ('STAFF', '$2a$10$gnxZlQQxcccuRF7XW6R0h.7bx8V3eVlvGpUQOKOCL2o33vCgO.ts6', 'staff'),
    ('MANAGER', '$2a$10$xHXarKF8V1wldza3Yurm0O3q9HJ307VMHcJlwV8a5LeHHY0aYZzCm', 'manager');
    
INSERT INTO `users`(`id_number`, `username`, `password`, `email`, `role`, `first_name`, `middle_initial`, `last_name`,
	`birthday`, `secret_question`, `secret_answer`)
VALUES
	('11425520', 'user', '$2a$10$p.8wHhg0ht54.01DN5vmS.WNq5DagkbGDSfS.aCwWwM2UM0jZfxa2', 'test@test.com', 'STUDENT', 'Test', 'T.', 'Test',
    '1998-01-11', 'The answer is answer', '$2a$10$.vGupWhgS.oorGBlfVRNBunSWEzx1ypRJFkCa5qk5oOTign5REY8.');