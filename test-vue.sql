/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : test-vue

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-11-13 21:37:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `back`
-- ----------------------------
DROP TABLE IF EXISTS `back`;
CREATE TABLE `back` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `price` double(5,2) NOT NULL,
  `number` int(6) NOT NULL,
  `date` date NOT NULL,
  `reason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of back
-- ----------------------------
INSERT INTO `back` VALUES ('1', '3', '2019000001', '1001', '10.55', '20', '2021-11-02', '多余');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(255) COLLATE utf8_bin NOT NULL,
  `supplier` varchar(16) COLLATE utf8_bin NOT NULL,
  `price` double(5,2) NOT NULL,
  `reserve` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1001', '数学教科书', '新华书店', '15.90', '1');
INSERT INTO `book` VALUES ('1002', '英语教科书', '新华书店', '14.90', '2');

-- ----------------------------
-- Table structure for `orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `price` double(5,2) NOT NULL,
  `number` int(11) NOT NULL,
  `date` date NOT NULL,
  `situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES ('1', '1', '2019000001', '1001', '0.00', '20', '2019-01-01', '未完成');

-- ----------------------------
-- Table structure for `stuclass`
-- ----------------------------
DROP TABLE IF EXISTS `stuclass`;
CREATE TABLE `stuclass` (
  `stuclass` int(11) NOT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`stuclass`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of stuclass
-- ----------------------------
INSERT INTO `stuclass` VALUES ('2000000', '图书管理处', '图书管理处');
INSERT INTO `stuclass` VALUES ('2019001', '大数据与软件工程学院', '计算机191');
INSERT INTO `stuclass` VALUES ('2019002', '大数据与软件工程学院', '计算机192');
INSERT INTO `stuclass` VALUES ('2019003', '大数据与软件工程学院', '计算机193');
INSERT INTO `stuclass` VALUES ('2019004', '大数据与软件工程学院', '软件工程191');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `nickname` varchar(255) COLLATE utf8_bin NOT NULL,
  `phonenumber` varchar(11) COLLATE utf8_bin NOT NULL,
  `stuclass` int(16) NOT NULL,
  `position` varchar(16) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2019000004 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', '13800000000', '2000000', '管理员');
INSERT INTO `user` VALUES ('2', 'root', 'root', 'admin', '13800000001', '2000000', '管理员');
INSERT INTO `user` VALUES ('3', '赵一', '123456', 'admin', '13800000000', '2000000', '管理员');
INSERT INTO `user` VALUES ('4', '钱二', '1234567', 'admin', '13866816590', '2000000', '管理员');
INSERT INTO `user` VALUES ('2019000001', '金泰相', '123456', 'doinb', '19800000000', '2019001', '学生');
INSERT INTO `user` VALUES ('2019000002', 'henry', 'root123', 'henry', '19800000001', '2019001', '学生');
INSERT INTO `user` VALUES ('2019000003', 'jy', '123456', 'jy', '12333333333', '2019001', '教师');
