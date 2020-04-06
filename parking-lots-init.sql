DROP DATABASE IF EXISTS `parkingLots`;
CREATE DATABASE `parkingLots`; 
USE `parkingLots`;

DROP TABLE IF EXISTS `parking-lots-management`;
CREATE TABLE `parking-lots-management` (
  `lot-id` VARCHAR(20) NOT NULL,
  `lot-table` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`lot-id`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `parking-lots-management`
VALUES ('A', 'parking-lot-A'),
	   ('B', 'parking-lot-B');

DROP TABLE IF EXISTS `parking-lot-A`;
CREATE TABLE `parking-lot-A` (
  id INT NOT NULL,
  `car-plate` VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `parking-lot-B`;
CREATE TABLE `parking-lot-B` (
  id INT NOT NULL,
  `car-plate` VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
