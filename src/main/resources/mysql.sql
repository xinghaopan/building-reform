/*
MySQL Backup
Source Server Version: 5.5.16
Source Database: building-reform
Date: 2016/7/17 21:34:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `br_department`
-- ----------------------------
DROP TABLE IF EXISTS `br_department`;
CREATE TABLE `br_department` (
  `DEPARTMENT_ID` varchar(15) NOT NULL,
  `DEPARTMENT_FATHER_ID` varchar(15) NOT NULL DEFAULT '0',
  `DEPARTMENT_NAME` varchar(255) DEFAULT NULL,
  `DEPARTMENT_FULL_NAME` varchar(2000) DEFAULT NULL,
  `DEPARTMENT_IS_WORK` int(11) NOT NULL DEFAULT '0',
  `DEPARTMENT_ORDER` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `br_info`
-- ----------------------------
DROP TABLE IF EXISTS `br_info`;
CREATE TABLE `br_info` (
  `INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INFO_PERSON_NAME` varchar(255) DEFAULT NULL,
  `INFO_PERSON_SEX` int(11) DEFAULT NULL,
  `INFO_PERSON_ID` varchar(18) DEFAULT NULL,
  `INFO_PERSON_NATION` varchar(255) DEFAULT NULL,
  `INFO_PERSON_NUM` int(11) DEFAULT NULL,
  `INFO_PERSON_TEL` varchar(255) DEFAULT NULL,
  `INFO_HOUSE_AGE` varchar(255) DEFAULT NULL,
  `INFO_HOUSE_OLD_SIZE1` double DEFAULT NULL,
  `INFO_HOUSE_OLD_SIZE2` double DEFAULT NULL,
  `INFO_HOUSE_NEW_SIZE1` double DEFAULT NULL,
  `INFO_HOUSE_NEW_SIZE2` double DEFAULT NULL,
  `INFO_HOUSE_OLD_TYPE` int(11) DEFAULT NULL,
  `INFO_HOUSE_OLD_TYPE_NAME` varchar(255) DEFAULT NULL,
  `INFO_TOILET_OLD_TYPE` int(11) DEFAULT NULL,
  `INFO_TOILET_OLD_TYPE_NAME` varchar(255) DEFAULT NULL,
  `INFO_TOILET_NEW_TYPE` int(11) DEFAULT NULL,
  `INFO_TOILET_NEW_TYPE_NAME` varchar(255) DEFAULT NULL,
  `INFO_BUILD_MODE` int(11) DEFAULT NULL,
  `INFO_BUILD_MODE_NAME` varchar(255) DEFAULT NULL,
  `INFO_REBUILD_MODE` int(11) DEFAULT NULL,
  `INFO_REBUILD_MODE_NAME` varchar(255) DEFAULT NULL,
  `INFO_PLAN_YEAR` varchar(255) DEFAULT NULL,
  `INFO_REBUILD_RATE` int(255) DEFAULT NULL,
  `INFO_REBUILD_RATE_NAME` varchar(255) DEFAULT NULL,
  `INFO_REBUILD_BEGIN_DATE` datetime DEFAULT NULL,
  `INFO_REBUILD_END_DATE` datetime DEFAULT NULL,
  `INFO_IS_ACCEPTANCE` int(11) DEFAULT NULL,
  `INFO_GRANT_TYPE` int(11) DEFAULT NULL,
  `INFO_GRANT_TYPE_NAME` varchar(255) DEFAULT NULL,
  `INFO_SUM_FUND` double(255,0) DEFAULT NULL,
  `INFO_GRANT_PROVINCE_FUND` double(255,0) DEFAULT NULL,
  `INFO_GRANT_COUNTIES_FUND` double(255,0) DEFAULT NULL,
  `INFO_PERSON_SELF_FUND` double(255,0) DEFAULT NULL,
  `INFO_DEPARTMENT_ID` varchar(200) DEFAULT NULL,
  `INFO_DEPARTMENT_NAME` varchar(255) DEFAULT NULL,
  `INFO_USER_ID` int(11) DEFAULT NULL,
  `INFO_USER_NAME` varchar(255) DEFAULT NULL,
  `INFO_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `br_menu`
-- ----------------------------
DROP TABLE IF EXISTS `br_menu`;
CREATE TABLE `br_menu` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_FATHER_ID` int(11) DEFAULT NULL,
  `MENU_FRONT_NAME` varchar(500) DEFAULT NULL,
  `MENU_FRONT_SUB_NAME` varchar(500) DEFAULT NULL,
  `MENU_FRONT_LINK` varchar(500) DEFAULT NULL,
  `MENU_BACK_NAME` varchar(500) DEFAULT NULL,
  `MENU_BACK_LINK` varchar(500) DEFAULT NULL,
  `MENU_BIG_IMAGE` varchar(500) DEFAULT NULL,
  `MENU_MIDDLE_IMAGE` varchar(500) DEFAULT NULL,
  `MENU_SMALL_IMAGE` varchar(500) DEFAULT NULL,
  `MENU_ISAUDIT` int(11) DEFAULT NULL,
  `MENU_TYPE` int(255) DEFAULT NULL,
  `MENU_HAS_TITLE` int(255) DEFAULT NULL,
  `MENU_ORDER` int(11) DEFAULT NULL,
  `MENU_IS_SUBJECT` int(255) DEFAULT NULL,
  `MENU_IS_NAVIGATION` int(11) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `br_news`
-- ----------------------------
DROP TABLE IF EXISTS `br_news`;
CREATE TABLE `br_news` (
  `NEWS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NEWS_MENU_ID` int(11) NOT NULL,
  `NEWS_TITLE` varchar(500) DEFAULT NULL,
  `NEWS_SUB_TITLE` varchar(500) DEFAULT NULL,
  `NEWS_CONTENT` longtext,
  `NEWS_AUTHOR` varchar(500) DEFAULT NULL,
  `NEWS_SOURCE` varchar(500) DEFAULT NULL,
  `NEWS_BIG_IMAGE` varchar(500) DEFAULT NULL,
  `NEWS_MIDDLE_IMAGE` varchar(500) DEFAULT NULL,
  `NEWS_SMALL_IMAGE` varchar(500) DEFAULT NULL,
  `NEWS_VIDEO` varchar(500) DEFAULT NULL,
  `NEWS_LINK` varchar(500) DEFAULT NULL,
  `NEWS_FILE` varchar(500) DEFAULT NULL,
  `NEWS_ORDER` int(11) DEFAULT NULL,
  `NEWS_ISTOP` int(11) DEFAULT NULL,
  `NEWS_ISNEW` int(11) DEFAULT NULL,
  `NEWS_AUDIT` int(11) DEFAULT NULL,
  `NEWS_HITS` int(11) DEFAULT NULL,
  `NEWS_KEY` varchar(500) DEFAULT NULL,
  `NEWS_DES` varchar(500) DEFAULT NULL,
  `NEWS_FLAG` int(11) DEFAULT NULL,
  `NEWS_DATE` datetime DEFAULT NULL,
  `NEWS_USER_ID` int(11) DEFAULT NULL,
  `NEWS_REMARK` varchar(500) DEFAULT NULL,
  `NEWS_IP` int(11) DEFAULT NULL,
  `NEWS_ORIGIN_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`NEWS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `br_quota`
-- ----------------------------
DROP TABLE IF EXISTS `br_quota`;
CREATE TABLE `br_quota` (
  `QUOTA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUOTA_YEAR` int(11) DEFAULT NULL,
  `QUOTA_DEPARTMENT_ID` varchar(255) DEFAULT NULL,
  `QUOTA_DEPARTMENT_NAME` varchar(255) DEFAULT NULL,
  `QUOTA_NUM` int(11) DEFAULT NULL,
  `QUOTA_REST_NUM` int(11) DEFAULT NULL,
  `QUOTA_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`QUOTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `br_role`
-- ----------------------------
DROP TABLE IF EXISTS `br_role`;
CREATE TABLE `br_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(500) DEFAULT NULL,
  `ROLE_POWER` longtext,
  `ROLE_ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `br_user`
-- ----------------------------
DROP TABLE IF EXISTS `br_user`;
CREATE TABLE `br_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(500) DEFAULT NULL,
  `USER_TRUE_NAME` varchar(500) DEFAULT NULL,
  `USER_PASSWORD` varchar(500) DEFAULT NULL,
  `USER_ROLE_ID` int(11) DEFAULT NULL,
  `USER_ORDER` int(11) DEFAULT NULL,
  `USER_STATE` int(11) DEFAULT NULL,
  `USER_DEPARTMENT_ID` varchar(255) DEFAULT NULL,
  `USER_DEPARTMENT_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `br_department` VALUES ('01','00','省厅',NULL,'1','10'), ('0101','01','长春市',NULL,'0','100'), ('0102','01','吉林市',NULL,'0','90'), ('0103','01','四平市',NULL,'0','80'), ('0104','01','辽源市',NULL,'0','70');
INSERT INTO `br_info` VALUES ('12','pan1','1','22020219780909423x','汉','3','15043075308','65','50',NULL,'12',NULL,'1','住房结构类型一','1','改造前厕所类型一','1','改造后厕所类型一','2','建设方式二','2','改造方式二','2016','2','改造进度二','2016-08-09 00:00:00','2016-08-10 00:00:00','1','1','享受补助资金类型一','100','90','80','70','01','省厅','1','管理员','2016-07-17 21:23:06'), ('13','1','1','1','1','1','1','1','1',NULL,'1',NULL,'1','住房结构类型一','1','改造前厕所类型一','1','改造后厕所类型一','1','建设方式一','1','改造方式一','2016','1','改造进度一','2015-01-01 00:00:00','2015-01-01 00:00:00','1','1','享受补助资金类型一','1','1','1','1','01','省厅','1','管理员','2016-07-17 21:29:06');
INSERT INTO `br_menu` VALUES ('1','0','系统管理',NULL,'','系统管理','','','','','1','1',NULL,'1','0','0'), ('2','1','功能管理',NULL,'','功能管理','/bk/menu/list','','','','1','2',NULL,'9','0','0'), ('3','1','用户管理',NULL,'','用户管理','/bk/user/list','','','','1','2',NULL,'7','0','0'), ('4','1','角色管理',NULL,'','角色管理','/bk/role/list','','','','1','2',NULL,'5','0','0'), ('5','0','新闻管理',NULL,'','新闻管理','',NULL,NULL,'','1','1',NULL,'100',NULL,NULL), ('14','5','下载专区','','/news/list','下载专区','/bk/news/list','','','','1','1',NULL,'97','0','1'), ('15','5','系统更新',NULL,'/news/list','系统更新','/bk/news/list',NULL,NULL,'','1','1',NULL,'99',NULL,NULL), ('16','5','政策文件',NULL,'/news/list','政策文件','/bk/news/list',NULL,NULL,'','1','1',NULL,'105',NULL,NULL), ('17','5','工作动态',NULL,'/news/list','工作动态','/bk/news/list',NULL,NULL,'','1','1',NULL,'110',NULL,NULL), ('60','1','组织机构管理',NULL,'','组织机构管理','/bk/department/list',NULL,NULL,'','1','1',NULL,'100',NULL,NULL), ('61','1','指标管理',NULL,'','指标管理','/bk/quota/list',NULL,NULL,'','1','1',NULL,'90',NULL,NULL), ('62','0','上报管理',NULL,'','上报管理','',NULL,NULL,'','1','1',NULL,'30',NULL,NULL), ('63','62','农户信息录入',NULL,'','农户信息录入','/bk/info/edit',NULL,NULL,'',NULL,NULL,NULL,'100',NULL,NULL), ('64','62','信息列表',NULL,'','信息列表','/bk/info/list',NULL,NULL,'',NULL,NULL,NULL,'110',NULL,NULL);
INSERT INTO `br_news` VALUES ('119','17','工作动态工作动态工作动态工作动态工作动态工作动态工作动态工作动态工作动态1','','<p>工作动态工作动态工作动态工作动态工作动态工作动态工作动态</p>','','','','','','','','','0','1',NULL,'1','0','','',NULL,'2016-06-26 00:00:00','1',NULL,NULL,NULL), ('120','17','工作动态工作动态工作动态2','<p>fes</p>','<p>fes</p>','qq','','','','','','','','0','1',NULL,'1','0','','',NULL,'2016-06-26 00:00:00','1',NULL,NULL,NULL), ('121','17','2','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('122','17','3','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('123','17','4','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('124','17','5','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('125','17','6','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('126','17','7','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('127','17','8','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('128','17','9','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL), ('129','17','10','','','','','','','','','','','0','0',NULL,'1','0','','',NULL,'2016-07-15 00:00:00','1',NULL,NULL,NULL);
INSERT INTO `br_quota` VALUES ('1','2014',NULL,NULL,NULL,NULL,NULL), ('2','2015',NULL,NULL,NULL,NULL,NULL), ('3','2014',NULL,NULL,NULL,NULL,NULL), ('5','2016','01','省厅','132','9','2016-07-15 22:57:35');
INSERT INTO `br_role` VALUES ('1','管理员',',-1,5,17,16,15,14,1,60,26,2,3,4,61,62,63,64,',NULL);
INSERT INTO `br_user` VALUES ('1','admin','管理员','e10adc3949ba59abbe56e057f20f883e','1','9',NULL,'01','省厅');
