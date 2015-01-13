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

)