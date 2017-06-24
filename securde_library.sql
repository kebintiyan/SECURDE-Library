DROP SCHEMA IF EXISTS `securde_library`;
CREATE SCHEMA `securde_library`;
USE `securde_library`;

CREATE TABLE `users` (
	`user_id` 			VARCHAR(16) NOT NULL,
	`first_name` 		VARCHAR(64) NOT NULL,
    `middle_initial` 	VARCHAR(5) NOT NULL,
    `last_name` 		VARCHAR(64) NOT NULL,
    `username` 			VARCHAR(32) NOT NULL,
    `password` 			VARCHAR(128) NOT NULL,
    `email_address` 	VARCHAR(64) NOT NULL,
    `birthday` 			DATE NOT NULL,
    `secret_question` 	VARCHAR(128) NOT NULL,
    `secret_answer` 	VARCHAR(128) NOT NULL,
    `user_type` 		ENUM('student', 'faculty') NOT NULL,
    
    PRIMARY KEY(`user_id`)
);

CREATE TABLE `admins` (
	`admin_id` 		INT NOT NULL AUTO_INCREMENT,
    `username` 		VARCHAR(32) NOT NULL,
    `password` 		VARCHAR(128) NOT NULL,
    `admin_type` 	ENUM('manager', 'staff', 'administrator'),
    
    PRIMARY KEY (`admin_id`)
);

CREATE TABLE `temp_admins` (
	`temp_admin_id` 		INT NOT NULL AUTO_INCREMENT,
    `username` 				VARCHAR(32) NOT NULL,
    `password` 				VARCHAR(128) NOT NULL,
    `admin_type` 			ENUM('manager', 'staff', 'administrator'),
    `date_time_created` 	DATETIME NOT NULL,
    
    PRIMARY KEY (`temp_admin_id`)
);


CREATE TABLE `texts` (
	`text_id` 		INT NOT NULL AUTO_INCREMENT,
    `title` 		VARCHAR(128) NOT NULL,
    `location` 		VARCHAR(128) NOT NULL,
    `author` 		VARCHAR(128) NOT NULL,
    `publisher` 	VARCHAR(128),
    `year`			VARCHAR(4) NOT NULL,
    `tags` 			VARCHAR(255),
    `status` 		ENUM('out', 'reserved', 'available') NOT NULL DEFAULT 'available',
    
    PRIMARY KEY (`text_id`)
);

CREATE TABLE `rooms` (
	`room_id` 		INT NOT NULL AUTO_INCREMENT,
    `name` 			VARCHAR(64) NOT NULL,
    `availability` 	ENUM('reserved', 'available') NOT NULL DEFAULT 'available',
    
    PRIMARY KEY (`room_id`)
);

CREATE TABLE `text_reservations` (
	`text_reservation_id` 		INT NOT NULL AUTO_INCREMENT,
    `user_id` 					VARCHAR(16) NOT NULL,
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
    `user_id` 					VARCHAR(16) NOT NULL,
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
