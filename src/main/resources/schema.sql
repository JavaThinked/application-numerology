DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `id` int primary key auto_increment,
  `category` varchar(20) DEFAULT NULL,
  `number` integer DEFAULT NULL,
  `message` varchar(1024) DEFAULT NULL,
  `language` varchar(2) DEFAULT NULL
);

DROP TABLE IF EXISTS `result_description`;
CREATE TABLE `result_description` (
  `id` integer primary key auto_increment,
  `category` varchar(20) DEFAULT NULL,
  `description` varchar(1024),
  `language` varchar(2) DEFAULT NULL
);

DROP TABLE IF EXISTS `formula`;
CREATE TABLE `formula` (
  `id` integer primary key auto_increment,
  `category` varchar(20) DEFAULT NULL,
  `expression` varchar(45) DEFAULT NULL
);