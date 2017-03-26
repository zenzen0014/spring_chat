-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.26 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for chatdb
CREATE DATABASE IF NOT EXISTS `chatdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `chatdb`;


-- Dumping structure for table chatdb.chat
CREATE TABLE IF NOT EXISTS `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid_to` int(11) DEFAULT NULL,
  `uid_from` int(11) DEFAULT NULL,
  `chatdate` datetime DEFAULT NULL,
  `text` text,
  `isread` int(1) DEFAULT '0',
  `isdelete` int(1) DEFAULT '0',
  `isgroup` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table chatdb.chat: ~0 rows (approximately)
DELETE FROM `chat`;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` (`id`, `uid_to`, `uid_from`, `chatdate`, `text`, `isread`, `isdelete`, `isgroup`) VALUES
	(1, 15, 2, '2017-03-24 10:59:23', 'Hey im comin', 0, 0, 0),
	(2, 15, 2, '2017-03-24 11:00:11', 'How are you', 0, 0, 0),
	(3, 2, 1, '2017-03-24 11:00:29', 'im oke', 0, 0, 0),
	(4, 15, 2, '2017-03-24 11:13:09', 'jangan baper kalo baper nanti bopeng kelakuan si kadal bopeng niiiiii', 0, 0, 0),
	(5, 15, 3, '2017-03-24 11:45:28', 'kelakuan si kadal bopeng niiiiii jangan baper kalo baper nanti bopeng kelakuan si kadal bopeng niiiiii', 0, 0, 0),
	(6, 2, 15, '2017-03-24 20:30:02', 'Kadal Bopeng', 0, 0, 0),
	(7, 15, 2, '2017-03-24 21:57:18', 'yang yang yang yang yang yang yang yang ', 0, 0, 0),
	(8, 16, 15, '2017-03-24 22:07:21', 'cuma test saja', 0, 0, 0),
	(9, 16, 15, '2017-03-24 22:07:21', 'is oke', 0, 0, 0),
	(10, 15, 16, '2017-03-24 22:07:21', 'jangan lama yaa', 0, 0, 0),
	(11, 16, 15, '2017-03-24 22:07:21', 'hahahaha', 0, 0, 0),
	(12, 17, 15, '2017-03-24 22:07:21', 'oi', 0, 0, 0),
	(13, 15, 17, '2017-03-24 22:07:21', 'hahhahaha', 0, 0, 0),
	(14, 15, 17, '2017-03-24 22:07:21', 'kgiuyfyfy', 0, 0, 0),
	(15, 15, 17, '2017-03-24 22:07:21', 'jhvuyfufu', 0, 0, 0),
	(16, 15, 17, '2017-03-24 22:07:21', '\\gctd', 0, 0, 0),
	(17, 17, 15, '2017-03-24 22:07:21', 'jhyyufytdtdytdtddt', 0, 0, 0),
	(18, 17, 15, '2017-03-24 22:07:21', 'gcgcgc', 0, 0, 0),
	(19, 17, 15, '2017-03-24 22:07:21', 'nbgcghcgc', 0, 0, 0),
	(20, 17, 15, '2017-03-24 22:07:21', 'nbcg', 0, 0, 0),
	(21, 17, 15, '2017-03-24 22:07:21', 'gcttc', 0, 0, 0),
	(22, 17, 15, '2017-03-24 22:07:21', 'gcgc', 0, 0, 0);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


-- Dumping structure for table chatdb.group
CREATE TABLE IF NOT EXISTS `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` char(50) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `recordstatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table chatdb.group: ~0 rows (approximately)
DELETE FROM `group`;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;


-- Dumping structure for table chatdb.groupchat
CREATE TABLE IF NOT EXISTS `groupchat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table chatdb.groupchat: ~0 rows (approximately)
DELETE FROM `groupchat`;
/*!40000 ALTER TABLE `groupchat` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupchat` ENABLE KEYS */;


-- Dumping structure for table chatdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `regist_date` datetime NOT NULL,
  `isactive` int(1) NOT NULL,
  `recordstatus` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table chatdb.user: ~2 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `regist_date`, `isactive`, `recordstatus`) VALUES
	(1, 'andi', '2017-03-24 01:05:43', 1, 1),
	(2, 'zendi', '2017-03-24 01:05:43', 1, 1),
	(3, 'kurnia', '2017-03-24 11:43:24', 1, 1),
	(15, 'doni', '2017-03-24 12:25:29', 1, 1),
	(16, 'zenzen', '2017-03-24 20:34:55', 1, 1),
	(17, 'budi', '2017-03-24 22:07:21', 1, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
