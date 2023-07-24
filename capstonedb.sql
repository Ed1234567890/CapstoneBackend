-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.11-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for capstonedb
DROP DATABASE IF EXISTS `capstonedb`;
CREATE DATABASE IF NOT EXISTS `capstonedb` /*!40100 DEFAULT CHARACTER SET utf16 COLLATE utf16_general_ci */;
USE `capstonedb`;

-- Dumping structure for table capstonedb.question
DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `qID` int(10) NOT NULL AUTO_INCREMENT,
  `QnStr` varchar(2047) NOT NULL DEFAULT '',
  `Category` varchar(255) NOT NULL DEFAULT '',
  `Active` tinyint(1) NOT NULL DEFAULT 1,
  `Option1` varchar(2047) NOT NULL,
  `Option2` varchar(2047) NOT NULL,
  `Option3` varchar(2047) NOT NULL,
  `Option4` varchar(2047) NOT NULL,
  `Ans` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`qID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf16 COLLATE=utf16_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table capstonedb.quiz
DROP TABLE IF EXISTS `quiz`;
CREATE TABLE IF NOT EXISTS `quiz` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QuizTemplate` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `PassingMark` tinyint(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf16 COLLATE=utf16_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table capstonedb.result
DROP TABLE IF EXISTS `result`;
CREATE TABLE IF NOT EXISTS `result` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `QuizID` int(11) NOT NULL,
  `Score` smallint(5) NOT NULL DEFAULT 0,
  `DateTaken` date NOT NULL DEFAULT curdate(),
  `selectedAns` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_result_quiz` (`QuizID`),
  CONSTRAINT `FK_result_quiz` FOREIGN KEY (`QuizID`) REFERENCES `quiz` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf16 COLLATE=utf16_general_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
