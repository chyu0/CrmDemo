-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.16 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 crm 的数据库结构
DROP DATABASE IF EXISTS `crm`;
CREATE DATABASE IF NOT EXISTS `crm` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `crm`;


-- 导出  表 crm.user_info 结构
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nickName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `realName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `birthday` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `headPic` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `idCardNo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `extFields` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='会员';




-- 导出  表 crm.user_point 结构
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE IF NOT EXISTS `user_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `pointDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='积分';

