SET SQL_SAFE_UPDATES = 0;
DROP DATABASE IF EXISTS `parkingLots`;
CREATE DATABASE `parkingLots`; 
USE `parkingLots`;

DROP TABLE IF EXISTS `parking_lots_management`;
CREATE TABLE `parking_lots_management` (
  `lot_id` VARCHAR(20) NOT NULL,
  `lot_table` VARCHAR(50) NOT NULL,
  `lot_size` INT NOT NULL,
  PRIMARY KEY (`lot_id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8;
INSERT INTO `parking_lots_management`
VALUES ('A', 'parking_lot_A', 8),
	   ('B', 'parking_lot_B', 10);

DROP TABLE IF EXISTS `parking_lot_A`;
CREATE TABLE `parking_lot_A` (
  id INT NOT NULL,
  plate VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET utf8;

DROP TABLE IF EXISTS `parking_lot_B`;
CREATE TABLE `parking_lot_B` (
  id INT NOT NULL,
  plate VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET utf8;
