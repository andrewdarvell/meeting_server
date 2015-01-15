CREATE DATABASE meeting;
GRANT ALL PRIVILEGES ON meeting.* TO meeting@localhost IDENTIFIED BY 'meetingvjcmrfblah' WITH GRANT OPTION;
flush privileges;

CREATE TABLE `users`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`login` CHAR(30) NOT NULL,
	`pass` CHAR(32) NOT NULL,
	`email` CHAR(30) NOT NULL,
	`status_id` INT(10),
	`status_mess` CHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `sessions`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`uid` INT(11) NOT NULL,
	`key` CHAR(32) NOT NULL,
	`start_date` DATETIME,
	`stop_date` DATETIME,
	`active` INT(1),
	PRIMARY KEY (`id`)
);

CREATE TABLE `api_keys`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`key` CHAR(32) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `friendship`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `uid` INT(11) NOT NULL,
    `friend_uid` INT(11) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `friendship_request`(
	`id` INT(11) NOT NUll AUTO_INCREMENT,
	`uid1` INT(11) NOT NULL,
	`uid2` INT(11) NOT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE `schedule`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`uid` INT(11) NOT NULL,
	`date1` DATETIME,
	`date2` DATETIME,
	`status_id` INT(11),
	`status_mess` CHAR(255),
	PRIMARY KEY(`id`)
);