-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: myoffice
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accessoryfile`
--

DROP TABLE IF EXISTS `accessoryfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accessoryfile` (
  `accessoryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件附件id',
  `fileId` int(11) NOT NULL COMMENT '文件id',
  `accessoryName` varchar(50) NOT NULL COMMENT '附件名称',
  `accessorySize` int(11) NOT NULL COMMENT '附件大小',
  `accessoryType` int(11) NOT NULL COMMENT '附件类型（外-filetypeid）',
  `createDate` datetime NOT NULL COMMENT '创建日期',
  `accessoryPath` varchar(200) NOT NULL COMMENT '附件路径',
  PRIMARY KEY (`accessoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessoryfile`
--

LOCK TABLES `accessoryfile` WRITE;
/*!40000 ALTER TABLE `accessoryfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `accessoryfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branchinfo`
--

DROP TABLE IF EXISTS `branchinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `branchinfo` (
  `branchId` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `branchName` varchar(50) NOT NULL COMMENT '机构名称',
  `branchShortName` varchar(50) NOT NULL COMMENT '机构简称',
  PRIMARY KEY (`branchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branchinfo`
--

LOCK TABLES `branchinfo` WRITE;
/*!40000 ALTER TABLE `branchinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `branchinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departinfo`
--

DROP TABLE IF EXISTS `departinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `departinfo` (
  `departId` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `departName` varchar(50) NOT NULL COMMENT '部门名称',
  `principalUser` varchar(50) NOT NULL COMMENT '部门负责人（对应UserInfo表的UserId字段，不建约束）',
  `connectTelNo` bigint(20) DEFAULT NULL COMMENT '联系电话',
  `connectMobileTelNo` bigint(20) DEFAULT NULL COMMENT '移动电话',
  `faxes` bigint(20) DEFAULT NULL COMMENT '传真',
  `branchId` int(11) NOT NULL COMMENT '所属机构（外-branchinfo-branchid）',
  PRIMARY KEY (`departId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departinfo`
--

LOCK TABLES `departinfo` WRITE;
/*!40000 ALTER TABLE `departinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `departinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fileinfo`
--

DROP TABLE IF EXISTS `fileinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fileinfo` (
  `fileId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `fileName` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `fileType` int(11) NOT NULL COMMENT '文件类型（外-filetypeinfo-fileTypeId）',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `fileOwner` varchar(50) NOT NULL COMMENT '创建者',
  `createDate` datetime NOT NULL COMMENT '创建日期',
  `parentId` int(11) NOT NULL COMMENT '父节点id',
  `filePath` varchar(200) NOT NULL COMMENT '文件路径',
  `ifDelete` int(1) NOT NULL COMMENT '是否删除（1：已删；2：未删）',
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileinfo`
--

LOCK TABLES `fileinfo` WRITE;
/*!40000 ALTER TABLE `fileinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `fileinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filetypeinfo`
--

DROP TABLE IF EXISTS `filetypeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `filetypeinfo` (
  `filetypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件类型id',
  `filetypeName` varchar(50) NOT NULL COMMENT '文件类型名',
  `filetypeImage` varchar(50) NOT NULL COMMENT '文件类型对应的图标',
  `filetypeSuffix` varchar(50) DEFAULT NULL COMMENT '文件类型后缀',
  PRIMARY KEY (`filetypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filetypeinfo`
--

LOCK TABLES `filetypeinfo` WRITE;
/*!40000 ALTER TABLE `filetypeinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `filetypeinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginlog`
--

DROP TABLE IF EXISTS `loginlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loginlog` (
  `loginId` bigint(20) unsigned NOT NULL,
  `isSuccess` tinyint(4) NOT NULL DEFAULT '0',
  `loginUserId` bigint(20) unsigned DEFAULT NULL,
  `loginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `loginIp` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `loginDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`loginId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginlog`
--

LOCK TABLES `loginlog` WRITE;
/*!40000 ALTER TABLE `loginlog` DISABLE KEYS */;
INSERT INTO `loginlog` VALUES (6980507862564864,1,6845764974149632,'2019-09-09 23:07:49','0:0:0:0:0:0:0:1','登录成功!'),(6980758191210496,1,6845764974149632,'2019-09-09 23:08:48','0:0:0:0:0:0:0:1','登录成功!'),(6981779915276288,0,NULL,'2019-09-09 23:12:52','0:0:0:0:0:0:0:1','登录失败!'),(6984622080524288,1,6845764974149632,'2019-09-09 23:24:10','0:0:0:0:0:0:0:1','登录成功!'),(6988839398670336,1,6845764974149632,'2019-09-09 23:40:55','0:0:0:0:0:0:0:1','登录成功!'),(7144132237590528,1,6845764974149632,'2019-09-10 09:58:00','0:0:0:0:0:0:0:1','登录成功!'),(7146160053551104,1,6845764974149632,'2019-09-10 10:06:03','0:0:0:0:0:0:0:1','登录成功!'),(7148383668011008,1,6866337364180992,'2019-09-10 10:14:53','0:0:0:0:0:0:0:1','登录成功!'),(7162307972956160,1,6866337364180992,'2019-09-10 11:10:13','0:0:0:0:0:0:0:1','登录成功!'),(7162519755948032,1,6845764974149632,'2019-09-10 11:11:04','0:0:0:0:0:0:0:1','登录成功!'),(10439492796153856,0,NULL,'2019-09-19 12:12:35','0:0:0:0:0:0:0:1','账户名或者密码输入错误!'),(10439801425625088,0,NULL,'2019-09-19 12:13:49','0:0:0:0:0:0:0:1','账户名或者密码输入错误!'),(10439817686941696,0,NULL,'2019-09-19 12:13:52','0:0:0:0:0:0:0:1','账户名或者密码输入错误!'),(10450106142687232,1,10449879381835776,'2019-09-19 12:54:45','0:0:0:0:0:0:0:1','登录成功!'),(10452245791375360,1,10449879381835776,'2019-09-19 13:03:16','0:0:0:0:0:0:0:1','登录成功!'),(10452705692614656,1,6845764974149632,'2019-09-19 13:05:05','0:0:0:0:0:0:0:1','登录成功!'),(10453615395209216,1,6866337364180992,'2019-09-19 13:08:42','0:0:0:0:0:0:0:1','登录成功!');
/*!40000 ALTER TABLE `loginlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manualsign`
--

DROP TABLE IF EXISTS `manualsign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manualsign` (
  `signId` int(11) NOT NULL AUTO_INCREMENT COMMENT '签卡id',
  `userId` varchar(50) NOT NULL COMMENT '用户id（外-userinfo-userid）',
  `signTime` datetime NOT NULL COMMENT '签卡时间',
  `signDesc` varchar(200) NOT NULL COMMENT '签卡备注',
  `signTag` int(11) NOT NULL COMMENT '签卡标记',
  PRIMARY KEY (`signId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manualsign`
--

LOCK TABLES `manualsign` WRITE;
/*!40000 ALTER TABLE `manualsign` DISABLE KEYS */;
/*!40000 ALTER TABLE `manualsign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meetinginfo`
--

DROP TABLE IF EXISTS `meetinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `meetinginfo` (
  `meetingId` int(11) NOT NULL AUTO_INCREMENT COMMENT '会议类型id',
  `meetingName` varchar(50) NOT NULL COMMENT '会议类型名称',
  PRIMARY KEY (`meetingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meetinginfo`
--

LOCK TABLES `meetinginfo` WRITE;
/*!40000 ALTER TABLE `meetinginfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `meetinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu` (
  `menuId` bigint(20) unsigned NOT NULL,
  `menuUrl` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `menuPath` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `menuComponent` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `menuName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `menuIcon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `menuOrder` int(11) NOT NULL DEFAULT '0',
  `parentMenuId` bigint(20) unsigned NOT NULL,
  `requireAuth` tinyint(4) DEFAULT '1',
  `disabled` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (7161924118642688,'/','/home','Home','人事管理','',0,0,1,0),(7161926496813056,'/branch/**','/personal/organization','organization','机构管理','',0,7161924118642688,1,0),(7161926534561792,'/department/**','/personal/department','department','部门管理','',0,7161924118642688,1,0),(7161926601670656,'/user/**','/personal/employee','employee','员工管理','',0,7161924118642688,1,0),(7161926626836480,'/','/home','Home','日程管理','',0,0,1,0),(7161926647808000,'/schedule/**','/schedule/mySchedule','mySchedule','我的日程','',0,7161926626836480,1,0),(7161926668779520,'/schedule/**','/schedule/departmentSchedule','departmentSchedule','部门日程','',0,7161926626836480,1,0),(7161926693945344,'/note/**','/schedule/myNotes','myNote','我的便签','',0,7161926626836480,1,0),(7161926719111168,'/','/home','Home','文档管理','',0,0,1,0),(7161926740082688,'/file/**','/file/manager','FileManager','文件管理','',0,7161926719111168,1,0),(7161926756859904,'/trash/**','/file/trash','FileTrash','回收站','',0,7161926719111168,1,0),(7161926773637120,'/','/home','Home','消息管理','',0,0,1,0),(7161926790414336,'/message/**','/message/msgManage','msgManage','信息管理','',0,7161926773637120,1,0),(7161926807191552,'/message/**','/message/mailBox','mailBox','信箱','',0,7161926773637120,1,0),(7161926828163072,'/','/home','Home','考勤管理','',0,0,1,0),(7161926844940288,'/manualSign/**','/check/checkCount','ManualSignCount','考勤统计','',0,7161926828163072,1,0),(7161926870106112,'/manualSign/**','/check/Inquiry','ManualSignQuery','考勤历史记录查询','',0,7161926828163072,1,0),(7161926886883328,'/manualSign/**','/check/employeeCheck','manualSign','员工签到/签退','',0,7161926828163072,1,0),(7161926907854848,'/','/home','Home','系统管理','',0,0,1,0),(7161926924632064,'/loginLog/**','/system/loginRecord','loginRecord','登录日志','',0,7161926907854848,1,0),(7161926941409280,'/operationLog/**','/system/operationRecord','operateRecord','操作日志','',0,7161926907854848,1,0),(7161926962380800,'/role/**','/system/roleManage','roleManage','角色管理','',0,7161926907854848,1,0),(7161926979158016,'/menu/**','/system/menuOrder','menuOrder','菜单排序','',0,7161926907854848,1,0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menurole`
--

DROP TABLE IF EXISTS `menurole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menurole` (
  `menuRoleId` bigint(20) unsigned NOT NULL,
  `menuId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`menuRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menurole`
--

LOCK TABLES `menurole` WRITE;
/*!40000 ALTER TABLE `menurole` DISABLE KEYS */;
INSERT INTO `menurole` VALUES (7161926480035840,7161924118642688,2867588375773184),(7161926513590272,7161926496813056,2867588375773184),(7161926576504832,7161926534561792,2867588375773184),(7161926614253568,7161926601670656,2867588375773184),(7161926639419392,7161926626836480,2867590938492928),(7161926660390912,7161926647808000,2867590938492928),(7161926681362432,7161926668779520,2867590938492928),(7161926702333952,7161926693945344,2867590938492928),(7161926731694080,7161926719111168,2867590938492928),(7161926748471296,7161926740082688,2867590938492928),(7161926765248512,7161926756859904,2867590938492928),(7161926786220032,7161926773637120,2867590938492928),(7161926798802944,7161926790414336,2867590938492928),(7161926819774464,7161926807191552,2867590938492928),(7161926836551680,7161926828163072,2867590938492928),(7161926861717504,7161926844940288,2867590938492928),(7161926878494720,7161926870106112,2867590938492928),(7161926895271936,7161926886883328,2867590938492928),(7161926916243456,7161926907854848,2867588375773184),(7161926933020672,7161926924632064,2867588375773184),(7161926949797888,7161926941409280,2867588375773184),(7161926970769408,7161926962380800,2867588375773184),(7161926987546624,7161926979158016,2867588375773184);
/*!40000 ALTER TABLE `menurole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `title` varchar(100) NOT NULL COMMENT '消息标题',
  `content` varchar(5000) NOT NULL COMMENT '消息内容',
  `type` int(11) NOT NULL COMMENT '消息类型',
  `beginTime` datetime NOT NULL COMMENT '开始有效时间',
  `endTime` datetime NOT NULL COMMENT '有效结束时间',
  `fromUserId` varchar(50) NOT NULL COMMENT '发送者',
  `ifPublish` int(1) NOT NULL DEFAULT '0' COMMENT '是否已发布（发布：1；未发布：0）',
  `recordTime` datetime NOT NULL COMMENT '发送时间',
  `ifDelete` int(1) NOT NULL COMMENT '删除标志位（1：已删除；0：未删除）',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `updateUserId` varchar(50) NOT NULL COMMENT '更新人id',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messagetouser`
--

DROP TABLE IF EXISTS `messagetouser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messagetouser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号id',
  `messageId` int(11) NOT NULL COMMENT '消息id（外-message-messageid）',
  `touserId` varchar(50) NOT NULL COMMENT '发送对象id（外-userinfo-userid）',
  `ifRead` int(1) NOT NULL COMMENT '是否已读（1：已读；0：未读）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messagetouser`
--

LOCK TABLES `messagetouser` WRITE;
/*!40000 ALTER TABLE `messagetouser` DISABLE KEYS */;
/*!40000 ALTER TABLE `messagetouser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messagetype`
--

DROP TABLE IF EXISTS `messagetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messagetype` (
  `messageTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息类型id',
  `messageTypeName` varchar(50) NOT NULL COMMENT '消息类型名称',
  `messageDesc` varchar(50) DEFAULT NULL COMMENT '消息类型描述',
  PRIMARY KEY (`messageTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messagetype`
--

LOCK TABLES `messagetype` WRITE;
/*!40000 ALTER TABLE `messagetype` DISABLE KEYS */;
/*!40000 ALTER TABLE `messagetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mynote`
--

DROP TABLE IF EXISTS `mynote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mynote` (
  `noteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '便签id',
  `noteTitle` varchar(50) NOT NULL COMMENT '便签标题',
  `noteContent` varchar(500) DEFAULT NULL COMMENT '便签内容',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `createUser` varchar(50) NOT NULL COMMENT '创建者',
  PRIMARY KEY (`noteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mynote`
--

LOCK TABLES `mynote` WRITE;
/*!40000 ALTER TABLE `mynote` DISABLE KEYS */;
/*!40000 ALTER TABLE `mynote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operatelog`
--

DROP TABLE IF EXISTS `operatelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operatelog` (
  `operateId` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志id',
  `userId` varchar(50) NOT NULL COMMENT '操作者',
  `operateName` varchar(50) NOT NULL COMMENT '操作名称',
  `objectId` varchar(50) NOT NULL COMMENT '操作对象Id',
  `operateDesc` varchar(200) NOT NULL COMMENT '操作描述',
  `operateTime` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`operateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operatelog`
--

LOCK TABLES `operatelog` WRITE;
/*!40000 ALTER TABLE `operatelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `operatelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precontract`
--

DROP TABLE IF EXISTS `precontract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `precontract` (
  `preContractId` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约序号id',
  `scheduleId` int(11) NOT NULL COMMENT '日程id（外-schedule-scheduleid）',
  `userId` varchar(50) NOT NULL COMMENT '预约人（外-userinfo-userId）',
  PRIMARY KEY (`preContractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precontract`
--

LOCK TABLES `precontract` WRITE;
/*!40000 ALTER TABLE `precontract` DISABLE KEYS */;
/*!40000 ALTER TABLE `precontract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readcommonmessage`
--

DROP TABLE IF EXISTS `readcommonmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `readcommonmessage` (
  `readId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号id',
  `messageId` int(11) NOT NULL COMMENT '消息id（外-message-mesageId）',
  `userId` varchar(50) NOT NULL COMMENT '消息读取者（外-userinfo-uerId）',
  PRIMARY KEY (`readId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readcommonmessage`
--

LOCK TABLES `readcommonmessage` WRITE;
/*!40000 ALTER TABLE `readcommonmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `readcommonmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `roleId` bigint(20) unsigned NOT NULL,
  `roleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `roleDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2867588375773184,'ROLE_ADMIN','超级管理员'),(2867590917521408,'ROLE_HR','管理员'),(2867590938492928,'ROLE_USER','员工');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schedule` (
  `scheduleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '日程id',
  `title` varchar(50) NOT NULL COMMENT '日程标题',
  `address` varchar(500) NOT NULL COMMENT '会议地址',
  `meetingId` int(11) NOT NULL COMMENT '会议类型（外-meetinginfo-meetingId）',
  `beginTime` datetime NOT NULL COMMENT '日程开始时间',
  `endTime` datetime NOT NULL COMMENT '日程结束时间',
  `schContent` varchar(500) NOT NULL COMMENT '日程内容',
  `createUser` varchar(50) NOT NULL COMMENT '创建者',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `ifPrivate` int(1) NOT NULL DEFAULT '1' COMMENT '是否私有（1：私有；0：非私有）',
  PRIMARY KEY (`scheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userId` bigint(20) unsigned NOT NULL,
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '-1',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'http://pic.51yuansu.com/pic3/cover/02/96/71/5ad09ba522904_610.jpg',
  `departmentId` bigint(20) unsigned NOT NULL,
  `isBlocked` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6845764974149632,'admin','$2a$10$RgxBG0PKQwQ5.3tLAQyciO8sosgHBjTVL7y8EfugL3Jgj111e6Yzm','超级管理员',0,'string',4718691837018112,0),(6866337364180992,'user','$2a$10$RgxBG0PKQwQ5.3tLAQyciO8sosgHBjTVL7y8EfugL3Jgj111e6Yzm','员工',0,'string',4718691837018112,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `userrole` (
  `userRoleId` bigint(20) unsigned NOT NULL,
  `userId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (6845765653626880,6845764974149632,2867588375773184),(6866338102378496,6866337364180992,2867590938492928),(10451781146378240,10451780160716800,0);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worktime`
--

DROP TABLE IF EXISTS `worktime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `worktime` (
  `worktimeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作时间id',
  `ondutyTime` varchar(50) NOT NULL COMMENT '上班时间',
  `offdutyTime` varchar(50) NOT NULL COMMENT '下班时间',
  PRIMARY KEY (`worktimeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worktime`
--

LOCK TABLES `worktime` WRITE;
/*!40000 ALTER TABLE `worktime` DISABLE KEYS */;
/*!40000 ALTER TABLE `worktime` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-19 13:18:04
