create table `JobInfo` (
  `ID` int primary key auto_increment,
  `title` varchar(200) not null default "",
  `salary` varchar(200) not null default "",
  `company` varchar(200) not null default "",
  `description` varchar(6000) not null default "",
  `source` varchar(200) not null default "",
  `url` varchar(5000) not null default "",
  `urlMd5` varchar(100) not null default "",
  key `ix_source` (`source`),
  unique key `un_ix_url_md5` (`urlMd5`)
) default charset 'utf8' ENGINE='innodb';


CREATE TABLE IF NOT EXISTS TVData(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	birthDay DATE,
	`type` VARCHAR(255),
	posterUrl VARCHAR(255),
	director MEDIUMTEXT,
	scriptwriter MEDIUMTEXT,
	producer MEDIUMTEXT,
	region VARCHAR(255),
	issueCompany VARCHAR(255),
	story MEDIUMTEXT,
	actor MEDIUMTEXT,
	url VARCHAR(255)
	) ENGINE=INNODB DEFAULT CHARSET=utf8;
	
	CREATE TABLE IF NOT EXISTS MovieData(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	birthDay DATE,
	`type` VARCHAR(255),
	posterUrl VARCHAR(255),
	director MEDIUMTEXT,
	scriptwriter MEDIUMTEXT,
	producer MEDIUMTEXT,
	region VARCHAR(255),
	issueCompany VARCHAR(255),
	story MEDIUMTEXT,
	actor MEDIUMTEXT,
	url VARCHAR(255)
	) ENGINE=INNODB DEFAULT CHARSET=utf8;
	
CREATE TABLE IF NOT EXISTS PeopleData(
			id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
			`name` VARCHAR(255),
			birthDay DATE,
			constellation VARCHAR(255),
			bloodType VARCHAR(255),
			height VARCHAR(255),
			weight VARCHAR(255),
			born VARCHAR(255),
			playExperenceStr MEDIUMTEXT,
			story MEDIUMTEXT,
			url VARCHAR(255),
			posterUrl VARCHAR(255)
			)ENGINE=INNODB DEFAULT CHARSET=utf8;
	