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
    
    `temp` 				TINYINT(1) DEFAULT 0,
    
    `active`			TINYINT(1) DEFAULT 1,
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
        ON DELETE CASCADE
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

CREATE TABLE `reviews` (
	`review_id` 			INT NOT NULL AUTO_INCREMENT,
    `user_id` 				INT NOT NULL,
    `text_id` 				INT NOT NULL,
    `rating` 				INT,
    `review_text` 			VARCHAR(10000) NOT NULL,
    `date_time_reviewed` 	DATETIME NOT NULL,
	
    PRIMARY KEY (`review_id`),
    
	FOREIGN KEY (`user_id`)
        REFERENCES `users`(`user_id`),
	FOREIGN KEY (`text_id`)
        REFERENCES `texts`(`text_id`)
        ON DELETE CASCADE
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
DROP TRIGGER IF EXISTS `default_date_time_reviews`$$
CREATE TRIGGER `default_date_time_reviews` BEFORE INSERT ON `reviews` FOR EACH ROW
	IF ( isnull(NEW.date_time_reviewed) ) THEN
		SET NEW.date_time_reviewed=NOW();
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

INSERT INTO `texts`(`title`, `location`, `author`, `publisher`, `year`, `tags`, `type`)
VALUES
	('Introduction to Algorithms', '000-AAB', 'Cormen', 'MIT Press', '1990', 'computer science,algorithms', 'BOOK'),
    ('Mistborn', '100-AEB', 'Brandon Sanderson', 'TOR', '2000', 'fiction', 'BOOK'),
    ('The Well of Ascension', '001-TAB', 'Brandon Sanderson', 'TOR', '2003', 'fiction', 'BOOK'),
    ('The Hero of Ages', '020-GAB', 'Brandon Sanderson', 'TOR', '2006', 'fiction', 'BOOK'),
    ('FHM', '021-TAB', 'Patrick Tobias', 'Viva', '2017', 'lifestyle', 'MAGAZINE'),
    ('Musical Metacreation via Gesture Interactions', '101-TCB', 'Migo Dancel', 'De La Salle University', '2017', 'academic', 'THESIS'),
    ('Genetic Algorithms', '101-TCB', 'Migo Dancel', 'De La Salle University', '2017', 'academic', 'THESIS'),
    ('Rule-based Algorithms', '101-TTB', 'Vince Gonzales', 'De La Salle University', '2013', 'academic', 'THESIS'),
    ('An Introduction to Musical Metacreation', '121-TCB', 'Philippe Pasquier', 'De La Salle University', '2005', 'academic', 'THESIS'),
    ('Animal Farm', '311-TCB', 'George Orwell', 'MIT', '2002', 'fiction', 'BOOK'),
    ('The Alchemist', '913-AAB', 'Paolo Coelho', 'Scholastic', '2005', 'fiction', 'BOOK'),
    ('The Quick Brown Fox', '921-TCB', 'Tobi Tobias', 'Lazy Dog Publishing', '2015', 'fiction', 'BOOK'),
    ('Jumps Over the Lazy Dog', '812-PBB', 'Digo Mancel', 'The Quick Brown Fox Publishing', '2016', 'fiction', 'BOOK'),
    ('K-Zone', '413-ARB', 'Benson Polican', 'ABC Publishing', '2016', 'kids', 'MAGAZINE'),
    ('Cosmpolitan', '119-ARB', 'Junlyn Bryan', 'DCE Publishing', '2014', 'lifestyle', 'MAGAZINE'),
    ('YES', '381-AAE', 'Mavic Reccion', 'TRE Publishing', '2016', 'lifestyle', 'MAGAZINE'),
    ('Playboy', '191-AAE', 'Ryan Roque', 'Playboy Publishing', '2011', 'lifestyle', 'MAGAZINE'),
    ('FHM v2', '781-AAE', 'Darren David', 'Viva Publishing', '2013', 'lifestyle', 'MAGAZINE'),
    ('Home Magazine', '323-ARB', 'Jack Cole', 'Home Life Publishing', '2016', 'lifestyle', 'MAGAZINE'),
    ('A Intro to Brain Waves', '122-ARB', 'Jack Cole', 'De La Salle University', '2015', 'academic', 'THESIS'),
    ('A Study on Humans', '132-CTB', 'Jack Black', 'De La Salle University', '2005', 'academic', 'THESIS'),
    ('Community Detection on Friendster', '212-ARB', 'Mark San Pedro', 'De La Salle University', '2013', 'academic', 'THESIS'),
    ('Genetic Algorithm in Music Generation', '128-HKZ', 'Jordan Deja', 'De La Salle University', '2009', 'academic', 'THESIS'),
    ('Identifying User Emotion Through EEG', '913-ARB', 'Aiko Deja', 'De La Salle University', '2011', 'academic', 'THESIS'),
    ('Community Sentiment Analysis', '716-UAI', 'Alron Lam', 'De La Salle University', '2014', 'academic', 'THESIS'),
    ('Finding the Leader in You', '913-PDB', 'Andres Dancel', 'The Quick Brown Fox Publishing', '2009', 'non-fiction', 'BOOK'),
    ('Search Inside Yourself', '919-DSB', 'Migo Andres', 'Quick Publishing', '2013', 'non-fiction', 'BOOK'),
    ('Looking in the Mirror', '153-PDB', 'Michael Cole', 'Token Publishing', '2011', 'non-fiction', 'BOOK'),
    ('Looking for the Missing Thing', '193-NBA', 'John Doe', 'Elementary Publishing', '2012', 'non-fiction,self-help', 'BOOK'),
    ('The Millenial Leader', '193-JKL', 'John Patrick', 'Vivo Publishing', '2009', 'non-fiction,self-help', 'BOOK'),
    ('Ball is Life', '913-ILA', 'Michael Jordan', 'NBA Publishing', '2010', 'non-fiction,sports', 'BOOK'),
    ('Red Queen', '101-FIC', 'Victoria Aveyard', 'HarperCollins', '2015', 'fiction,romance', 'BOOK'),
	('Glass Sword', '102-FIC', 'Victoria Aveyard', 'HarperCollins', '2016', 'fiction,romance', 'BOOK'),
	('Kings Cage', '103-FIC', 'Victoria Aveyard', 'HarperCollins', '2017', 'fiction,romance', 'BOOK'),
	('Harry Potter and the Philosophers Stone', '104-FIC', 'J.K. Rowling', 'Bloomsbury', '1997', 'fantasy,adventure', 'BOOK'),
	('Harry Potter and the Chamber of Secrets', '105-FIC', 'J.K. Rowling', 'Bloomsbury', '1998', 'fantasy,adventure', 'BOOK'),
	('Harry Potter and the Prisoner of Azkaban', '106-FIC', 'J.K. Rowling', 'Bloomsbury', '1999', 'fantasy,adventure', 'BOOK'),
	('Harry Potter and the Goblet of Fire', '107-FIC', 'J.K. Rowling', 'Bloomsbury', '2000', 'fantasy,adventure', 'BOOK'),
	('Harry Potter and the Order of the Phoenix', '108-FIC', 'J.K. Rowling', 'Bloomsbury', '2003', 'fantasy,adventure', 'BOOK'),
	('Harry Potter and the Half Blood Prince', '109-FIC', 'J.K. Rowling', 'Bloomsbury', '2005', 'fantasy,adventure', 'BOOK'),
	('Harry Potter and the Deathly Hallows', '110-FIC', 'J.K. Rowling', 'Bloomsbury', '2007', 'fantasy,adventure', 'BOOK'),
	('Towards Modeling Guitar Chords through Electromyography', '114-CCS', 'Tobias, J.P.', 'De La Salle University', '2016', 'music,guitar,electromyography', 'THESIS'),
	('Community Detection on Facebook and Twitter', '113-CCS', 'Fernandez, A.', 'De La Salle University', '2016', 'social networks,community detection', 'THESIS'),
	('Community Detection on Facebook and Twitter', '113-CCS', 'Fernandez, A.', 'De La Salle University', '2016', 'social networks,community detection', 'THESIS'),
	('Recognizing Reader Affect Using EEG Data', '112-CCS', 'Kalaw, C..', 'De La Salle University', '2013', 'emotions,reading,eeg', 'THESIS'),
	('Automatic Extraction of Conceptual Relations from Children Stories', '101-CCS', 'Samson, Pogi', 'De La Salle University', '2013', 'children stories,language', 'THESIS'),
	('Tech: The New Thang', '121-TCH', 'Nig, G.', 'Publishing Inc.', '2017', 'technology', 'MAGAZINE'),
	('Food: Finger Lickin Good', '123-FOD', 'Bacon, C.P.', 'TLC Inc.', '2017', 'food', 'MAGAZINE'),
    ('Noli Me Tangere', '913-FIC', 'Jose Rizal', 'Pluma Publishing', '1981', 'fiction', 'BOOK'),
    ('El Filibusterismo', '913-FTC', 'Jose Rizal', 'Pluma Publishing', '1983', 'fiction', 'BOOK'),
    ('On the Life of Jose Rizal', '193-LAP', 'Arturo Gonzales', 'Candlelight', '1990', 'non-fiction,history', 'BOOK'),
    ('The Filipino People', '019-ATP', 'Michael Gonzales', 'Candlelight', '1910', 'non-fiction,nationalism', 'BOOK'),
    ('Time: It\'s Nearly Time', '143-LIF', 'Wow, W.W.', 'Life Publishing', '2017', 'lifestyle', 'MAGAZINE');
    

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