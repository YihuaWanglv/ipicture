/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : ipicture_post

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2016-07-21 00:02:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `title` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `creator` bigint(20) NOT NULL DEFAULT '0',
  `content` longtext NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `last_updated` datetime DEFAULT NULL,
  `last_updator` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for `post_comment`
-- ----------------------------
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `creator` bigint(20) NOT NULL DEFAULT '0',
  `updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` longtext NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(64) NOT NULL,
  `descr` varchar(512) DEFAULT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `creator` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
