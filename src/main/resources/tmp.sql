CREATE TABLE `users`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`login` CHAR(30) NOT NULL,
	`pass` CHAR(32) NOT NULL,
	`email` CHAR(30) NOT NULL,
	`status_id` INT(10),
	`status_mess` CHAR(255),
	PRIMARY KEY (`id`)
);

SELECT `id`, `login`, `pass`, `email`, `status_id`, `status_mess`
FROM `users`
WHERE `login` LIKE '%new%'
;

UPDATE `users`
SET `status_id` = 2, `status_mess` = 'dded'
WHERE id = 11;

SELECT `status_id`, `status_mess`
FROM `users`
WHERE `id` = ?;

SELECT `id`

SELECT id
FROM users
WHERE login =
	AND pass =

CREATE TABLE `api_keys`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`key` CHAR(32) NOT NULL,
	PRIMARY KEY (`id`)
);
INSERT INTO `api_keys`(`key`)
VALUES ('bcbe3365e6ac95ea2c0343a2395834dd');
bcbe3365e6ac95ea2c0343a2395834dd

SELECT `key`
FROM `api_keys`
WHERE `key` =

INSERT INTO `users` (`login`, `pass`, `email`)
VALUES ('newuser','11','11');

CREATE TABLE `sessions`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`uid` INT(11) NOT NULL,
	`key` CHAR(32) NOT NULL,
	`start_date` DATETIME,
	`stop_date` DATETIME,
	`active` INT(1),
	PRIMARY KEY (`id`)
);

UPDATE sessions
SET active = -1
WHERE uid =

SELECT `uid`
FROM `sessions`
WHERE `key` = 'e085392eb0d56d046b9a04c6735ae5d3'
	AND `active` == 0;

INSERT INTO `sessions`(`key`, `start_date`)
VALUES ()


 <server>
        <id>TomcatServer</id>
        <username>darvell</username>
        <password>vjcmrf</password>
 </server>

 <user username="darvell" password="vjcmrf" roles="admin, admin-gui,manager-gui,manager-script"/>
GRANT ALL PRIVILEGES ON meeting.* TO meeting@localhost IDENTIFIED BY 'meetingvjcmrfblah' WITH GRANT OPTION;flush privileges;


 curl --data "action=set_status&status=2&status_mess=mystatus&session_key=2893dc02b886df1bd046b5741692194e" http://localhost:8080/meeting/users

	92e4da84d9d51cf199d2b7289f8d6d3d
	curl --data "action=setStatus&status=2&status_mess=mystatus&session_key=92e4da84d9d51cf199d2b7289f8d6d3d" http://localhost:8080/meeting/users
	curl --data "action=findUser&login=new&session_key=92e4da84d9d51cf199d2b7289f8d6d3d" http://localhost:8080/meeting/users


 curl --data "action=getKey&login=newuser&pass=11&api_key=bcbe3365e6ac95ea2c0343a2395834dd" http://localhost:8080/meeting/secur
 http://localhost:8080/meeting/users?action=register&login=newuser&pass=11&email=11

11 newuser - 2893dc02b886df1bd046b5741692194e
12 darvell - feae65b58ff554b190603d5cc46b745d

2893dc02b886df1bd046b5741692194e
curl --data "action=getKey&login=darvell&pass=11&api_key=bcbe3365e6ac95ea2c0343a2395834dd" http://localhost:8080/meeting/secur

curl --data "action=findUser&login=new&session_key=2893dc02b886df1bd046b5741692194e" http://localhost:8080/meeting/users
curl --data "action=addFriendRequest&friendId=12&session_key=2893dc02b886df1bd046b5741692194e" http://localhost:8080/meeting/friends

curl --data "action=addFriendRequest&friendId=12&session_key=2893dc02b886df1bd046b5741692194e" http://localhost:8080/meeting/friends

curl --data "action=commitFriend&friendId=11&session_key=feae65b58ff554b190603d5cc46b745d" http://localhost:8080/meeting/friends
