/*
MySQL Backup
Source Server Version: 5.5.16
Source Database: building-reform
Date: 2016/6/26 18:35:06
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `br_menu` VALUES ('-1','0','审核',NULL,'','审核','','','','','1','0',NULL,'999','0','0'), ('1','0','系统管理',NULL,'','系统管理','','','','','1','1',NULL,'1','0','0'), ('2','1','功能管理',NULL,'','功能管理','/bk/menu/list','','','','1','2',NULL,'9','0','0'), ('3','1','用户管理',NULL,'','用户管理','/bk/user/list','','','','1','2',NULL,'7','0','0'), ('4','1','角色管理',NULL,'','角色管理','/bk/role/list','','','','1','2',NULL,'5','0','0'), ('5','0','新闻管理','','','新闻管理','','','','','1','2',NULL,'10','0','0'), ('14','5','下载专区','','/news/list','下载专区','/bk/news/list','','','','1','1',NULL,'97','0','1'), ('15','5','系统更新','','/news/list','系统更新','/bk/news/list','','','','1','1',NULL,'99','0','1'), ('16','5','政策文件','','/news/list','政策文件','/bk/news/list','','','','1','1',NULL,'105','0','1'), ('17','5','工作动态','','/news/list','工作动态','/bk/news/list','','','','1','1',NULL,'110','0','1'), ('26','1','网站基本信息',NULL,'','网站基本信息','/bk/webinfo/edit','','','','1','2',NULL,'15','0','0');
INSERT INTO `br_news` VALUES ('119','17','工作动态工作动态工作动态工作动态工作动态工作动态工作动态工作动态工作动态1','','<p>工作动态工作动态工作动态工作动态工作动态工作动态工作动态</p>','','','','','','','','','0','1',NULL,'1','0','','','0','2016-06-26 00:00:00','1',NULL,'0',NULL), ('120','17','工作动态2','','','','','','','','','','','0','1',NULL,'1','0','','','0','2016-06-26 00:00:00','1',NULL,'0',NULL);
INSERT INTO `br_role` VALUES ('1','管理员',',-1,5,52,24,21,22,6,15,7,8,9,10,11,12,13,14,43,50,23,51,42,44,45,46,47,48,58,49,16,17,18,19,53,20,27,28,35,29,30,31,32,33,34,36,40,37,39,38,41,1,54,55,56,26,2,3,4,25,59,57,',NULL);
INSERT INTO `br_user` VALUES ('1','admin','管理员','e10adc3949ba59abbe56e057f20f883e','1','9',NULL);
