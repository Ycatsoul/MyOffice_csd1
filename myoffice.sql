/*
Navicat MySQL Data Transfer

Source Server         : myDB
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : myoffice

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2019-09-25 13:41:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accessoryfile
-- ----------------------------
DROP TABLE IF EXISTS `accessoryfile`;
CREATE TABLE `accessoryfile` (
  `accessoryId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件附件id',
  `fileId` bigint(20) NOT NULL COMMENT '文件id',
  `accessoryName` varchar(50) NOT NULL COMMENT '附件名称',
  `accessorySize` int(11) NOT NULL COMMENT '附件大小',
  `accessoryType` int(11) NOT NULL COMMENT '附件类型（外-filetypeid）',
  `createDate` datetime NOT NULL COMMENT '创建日期',
  `accessoryPath` varchar(200) NOT NULL COMMENT '附件路径',
  PRIMARY KEY (`accessoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accessoryfile
-- ----------------------------

-- ----------------------------
-- Table structure for branchinfo
-- ----------------------------
DROP TABLE IF EXISTS `branchinfo`;
CREATE TABLE `branchinfo` (
  `branchId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `branchName` varchar(50) NOT NULL COMMENT '机构名称',
  `branchShortName` varchar(50) NOT NULL COMMENT '机构简称',
  PRIMARY KEY (`branchId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of branchinfo
-- ----------------------------
INSERT INTO `branchinfo` VALUES ('1', '行政机构', '行政');
INSERT INTO `branchinfo` VALUES ('2', '销售机构', '销售');
INSERT INTO `branchinfo` VALUES ('3', '开发机构', '开发');

-- ----------------------------
-- Table structure for departinfo
-- ----------------------------
DROP TABLE IF EXISTS `departinfo`;
CREATE TABLE `departinfo` (
  `departId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `departName` varchar(50) NOT NULL COMMENT '部门名称',
  `principalUser` varchar(20) NOT NULL COMMENT '部门负责人（对应UserInfo表的UserId字段，不建约束）',
  `connectTelNo` bigint(20) DEFAULT NULL COMMENT '联系电话',
  `connectMobileTelNo` bigint(20) DEFAULT NULL COMMENT '移动电话',
  `faxes` bigint(20) DEFAULT NULL COMMENT '传真',
  `branchId` bigint(20) NOT NULL COMMENT '所属机构（外-branchinfo-branchid）',
  PRIMARY KEY (`departId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departinfo
-- ----------------------------
INSERT INTO `departinfo` VALUES ('1', '人事部门', '6845764974149632', '4131234', '18542367890', '456789', '1');
INSERT INTO `departinfo` VALUES ('2', '销售部门', '6866337364180992', '4125678', '17245201369', '123456', '2');
INSERT INTO `departinfo` VALUES ('3', '后端开发部门', '6866337364180992', '4117894', '15110567850', '456123', '3');
INSERT INTO `departinfo` VALUES ('4', '前端开发部门', '6866337364180992', '4107855', '15178901236', '123789', '3');

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo` (
  `fileId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `fileName` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `fileType` int(11) NOT NULL COMMENT '文件类型（外-filetypeinfo-fileTypeId）',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `fileOwner` varchar(50) NOT NULL COMMENT '创建者',
  `createDate` datetime NOT NULL COMMENT '创建日期',
  `parentId` bigint(20) NOT NULL COMMENT '父节点id',
  `filePath` varchar(200) NOT NULL COMMENT '文件路径',
  `ifDelete` int(1) NOT NULL COMMENT '是否删除（1：已删；2：未删）',
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fileinfo
-- ----------------------------

-- ----------------------------
-- Table structure for filetypeinfo
-- ----------------------------
DROP TABLE IF EXISTS `filetypeinfo`;
CREATE TABLE `filetypeinfo` (
  `filetypeId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件类型id',
  `filetypeName` varchar(50) NOT NULL COMMENT '文件类型名',
  `filetypeImage` varchar(50) NOT NULL COMMENT '文件类型对应的图标',
  `filetypeSuffix` varchar(50) DEFAULT NULL COMMENT '文件类型后缀',
  PRIMARY KEY (`filetypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filetypeinfo
-- ----------------------------

-- ----------------------------
-- Table structure for loginlog
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `loginId` bigint(20) unsigned NOT NULL,
  `isSuccess` tinyint(4) NOT NULL DEFAULT '0',
  `loginUserId` bigint(20) unsigned DEFAULT NULL,
  `loginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `loginIp` varchar(16) NOT NULL DEFAULT '',
  `loginDesc` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`loginId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of loginlog
-- ----------------------------
INSERT INTO `loginlog` VALUES ('6980507862564864', '1', '6845764974149632', '2019-09-09 23:07:49', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('6980758191210496', '1', '6845764974149632', '2019-09-09 23:08:48', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('6981779915276288', '0', null, '2019-09-09 23:12:52', '0:0:0:0:0:0:0:1', '登录失败!');
INSERT INTO `loginlog` VALUES ('6984622080524288', '1', '6845764974149632', '2019-09-09 23:24:10', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('6988839398670336', '1', '6845764974149632', '2019-09-09 23:40:55', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('7144132237590528', '1', '6845764974149632', '2019-09-10 09:58:00', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('7146160053551104', '1', '6845764974149632', '2019-09-10 10:06:03', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('7148383668011008', '1', '6866337364180992', '2019-09-10 10:14:53', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('7162307972956160', '1', '6866337364180992', '2019-09-10 11:10:13', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('7162519755948032', '1', '6845764974149632', '2019-09-10 11:11:04', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10439492796153856', '0', null, '2019-09-19 12:12:35', '0:0:0:0:0:0:0:1', '账户名或者密码输入错误!');
INSERT INTO `loginlog` VALUES ('10439801425625088', '0', null, '2019-09-19 12:13:49', '0:0:0:0:0:0:0:1', '账户名或者密码输入错误!');
INSERT INTO `loginlog` VALUES ('10439817686941696', '0', null, '2019-09-19 12:13:52', '0:0:0:0:0:0:0:1', '账户名或者密码输入错误!');
INSERT INTO `loginlog` VALUES ('10450106142687232', '1', '10449879381835776', '2019-09-19 12:54:45', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10452245791375360', '1', '10449879381835776', '2019-09-19 13:03:16', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10452705692614656', '1', '6845764974149632', '2019-09-19 13:05:05', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10453615395209216', '1', '6866337364180992', '2019-09-19 13:08:42', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10514222345617408', '0', null, '2019-09-19 17:09:32', '0:0:0:0:0:0:0:1', '账户名或者密码输入错误!');
INSERT INTO `loginlog` VALUES ('10514349818904576', '1', '6845764974149632', '2019-09-19 17:10:02', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10517433982910464', '1', '6845764974149632', '2019-09-19 17:22:18', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10522058140155904', '1', '6845764974149632', '2019-09-19 17:40:40', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10524014275461120', '1', '6845764974149632', '2019-09-19 17:48:26', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10571979333566464', '1', '6845764974149632', '2019-09-19 20:59:02', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10573104048766976', '1', '6845764974149632', '2019-09-19 21:03:30', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10574216084586496', '1', '6845764974149632', '2019-09-19 21:07:56', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10786720454803456', '1', '6845764974149632', '2019-09-20 11:12:21', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10801655066918912', '1', '6845764974149632', '2019-09-20 12:11:41', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10830783296045056', '1', '6845764974149632', '2019-09-20 14:07:26', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10832440490721280', '1', '6845764974149632', '2019-09-20 14:14:01', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10834220238766080', '1', '6845764974149632', '2019-09-20 14:21:05', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10834741129379840', '1', '6845764974149632', '2019-09-20 14:23:10', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10835326893293568', '1', '6845764974149632', '2019-09-20 14:25:29', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10836108501843968', '1', '6845764974149632', '2019-09-20 14:28:36', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10840749595688960', '1', '6845764974149632', '2019-09-20 14:47:02', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10841237581987840', '1', '6845764974149632', '2019-09-20 14:48:58', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10842605537460224', '1', '6845764974149632', '2019-09-20 14:54:25', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10844586863755264', '1', '6845764974149632', '2019-09-20 15:02:17', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10845130550411264', '1', '6845764974149632', '2019-09-20 15:04:27', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10861128670248960', '1', '6845764974149632', '2019-09-20 16:08:01', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10862591060475904', '1', '6845764974149632', '2019-09-20 16:13:49', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('10917408797622272', '1', '6845764974149632', '2019-09-20 19:51:39', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11147749063917568', '1', '6845764974149632', '2019-09-21 11:06:56', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11150658275115008', '1', '6845764974149632', '2019-09-21 11:18:30', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11151635384369152', '1', '6845764974149632', '2019-09-21 11:22:23', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11152289825816576', '1', '6845764974149632', '2019-09-21 11:24:59', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11153583030403072', '1', '6845764974149632', '2019-09-21 11:30:07', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11157114357547008', '1', '6845764974149632', '2019-09-21 11:44:09', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11157164177489920', '1', '6845764974149632', '2019-09-21 11:44:21', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11255837930553344', '1', '6845764974149632', '2019-09-21 18:16:27', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11262772193001472', '1', '6845764974149632', '2019-09-21 18:44:00', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11266905524404224', '1', '6845764974149632', '2019-09-21 19:00:26', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11269273485836288', '1', '6845764974149632', '2019-09-21 19:09:50', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11320293335236608', '1', '6845764974149632', '2019-09-21 22:32:35', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11321962831806464', '1', '6845764974149632', '2019-09-21 22:39:12', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11539757465075712', '1', '6845764974149632', '2019-09-22 13:04:39', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11558830278180864', '1', '6845764974149632', '2019-09-22 14:20:26', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11897045178646528', '1', '6845764974149632', '2019-09-23 12:44:23', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11913903734784000', '1', '6845764974149632', '2019-09-23 13:51:22', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11914938817708032', '1', '6845764974149632', '2019-09-23 13:55:29', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11920232788525056', '1', '6845764974149632', '2019-09-23 14:16:31', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11922434273837056', '1', '6845764974149632', '2019-09-23 14:25:16', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11933630314053632', '1', '6845764974149632', '2019-09-23 15:09:45', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('11933633006796800', '1', '6845764974149632', '2019-09-23 15:09:46', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12018145946501120', '1', '6845764974149632', '2019-09-23 20:45:35', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12313223663452160', '1', '6845764974149632', '2019-09-24 16:18:07', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12318141317120000', '1', '6845764974149632', '2019-09-24 16:37:40', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12332482720432128', '1', '6845764974149632', '2019-09-24 17:34:39', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12393050240188416', '1', '6845764974149632', '2019-09-24 21:35:19', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12401545203154944', '1', '6845764974149632', '2019-09-24 22:09:05', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12591904327729152', '1', '6845764974149632', '2019-09-25 10:45:30', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12597493795651584', '1', '6845764974149632', '2019-09-25 11:07:43', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12624874770006016', '1', '6845764974149632', '2019-09-25 12:56:31', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12628836755701760', '1', '6845764974149632', '2019-09-25 13:12:15', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12630445803962368', '1', '6845764974149632', '2019-09-25 13:18:39', '0:0:0:0:0:0:0:1', '登录成功!');
INSERT INTO `loginlog` VALUES ('12633476809359360', '1', '6845764974149632', '2019-09-25 13:30:42', '0:0:0:0:0:0:0:1', '登录成功!');

-- ----------------------------
-- Table structure for manualsign
-- ----------------------------
DROP TABLE IF EXISTS `manualsign`;
CREATE TABLE `manualsign` (
  `signId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '签卡id',
  `userId` bigint(20) NOT NULL COMMENT '用户id（外-userinfo-userid）',
  `signTime` datetime NOT NULL COMMENT '签卡时间',
  `signDesc` varchar(200) NOT NULL COMMENT '签卡备注',
  `signTag` int(11) NOT NULL COMMENT '签卡标记',
  PRIMARY KEY (`signId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manualsign
-- ----------------------------

-- ----------------------------
-- Table structure for meetinginfo
-- ----------------------------
DROP TABLE IF EXISTS `meetinginfo`;
CREATE TABLE `meetinginfo` (
  `meetingId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会议类型id',
  `meetingName` varchar(50) NOT NULL COMMENT '会议类型名称',
  PRIMARY KEY (`meetingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meetinginfo
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId` bigint(20) unsigned NOT NULL,
  `menuUrl` varchar(128) NOT NULL DEFAULT '',
  `menuPath` varchar(128) DEFAULT '',
  `menuComponent` varchar(64) DEFAULT '',
  `menuName` varchar(64) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `menuIcon` varchar(64) DEFAULT '',
  `menuOrder` int(11) NOT NULL DEFAULT '0',
  `parentMenuId` bigint(20) unsigned NOT NULL,
  `requireAuth` tinyint(4) DEFAULT '1',
  `disabled` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('7161924118642688', '/', '/home', 'Home', '人事管理', '', '0', '0', '1', '0');
INSERT INTO `menu` VALUES ('7161926496813056', '/branch/**', '/personal/organization', 'organization', '机构管理', '', '0', '7161924118642688', '1', '0');
INSERT INTO `menu` VALUES ('7161926534561792', '/department/**', '/personal/department', 'department', '部门管理', '', '0', '7161924118642688', '1', '0');
INSERT INTO `menu` VALUES ('7161926601670656', '/user/**', '/personal/employee', 'employee', '员工管理', '', '0', '7161924118642688', '1', '0');
INSERT INTO `menu` VALUES ('7161926626836480', '/', '/home', 'Home', '日程管理', '', '0', '0', '1', '0');
INSERT INTO `menu` VALUES ('7161926647808000', '/schedule/**', '/schedule/mySchedule', 'mySchedule', '我的日程', '', '0', '7161926626836480', '1', '0');
INSERT INTO `menu` VALUES ('7161926668779520', '/schedule/**', '/schedule/departmentSchedule', 'departmentSchedule', '部门日程', '', '0', '7161926626836480', '1', '0');
INSERT INTO `menu` VALUES ('7161926693945344', '/note/**', '/schedule/myNotes', 'myNote', '我的便签', '', '0', '7161926626836480', '1', '0');
INSERT INTO `menu` VALUES ('7161926719111168', '/', '/home', 'Home', '文档管理', '', '0', '0', '1', '0');
INSERT INTO `menu` VALUES ('7161926740082688', '/file/**', '/file/manager', 'FileManager', '文件管理', '', '0', '7161926719111168', '1', '0');
INSERT INTO `menu` VALUES ('7161926756859904', '/trash/**', '/file/trash', 'FileTrash', '回收站', '', '0', '7161926719111168', '1', '0');
INSERT INTO `menu` VALUES ('7161926773637120', '/', '/home', 'Home', '消息管理', '', '0', '0', '1', '0');
INSERT INTO `menu` VALUES ('7161926790414336', '/message/**', '/message/msgManage', 'msgManage', '信息管理', '', '0', '7161926773637120', '1', '0');
INSERT INTO `menu` VALUES ('7161926807191552', '/message/**', '/message/mailBox', 'mailBox', '信箱', '', '0', '7161926773637120', '1', '0');
INSERT INTO `menu` VALUES ('7161926828163072', '/', '/home', 'Home', '考勤管理', '', '0', '0', '1', '0');
INSERT INTO `menu` VALUES ('7161926844940288', '/manualSign/**', '/check/checkCount', 'ManualSignCount', '考勤统计', '', '0', '7161926828163072', '1', '0');
INSERT INTO `menu` VALUES ('7161926870106112', '/manualSign/**', '/check/Inquiry', 'ManualSignQuery', '考勤历史记录查询', '', '0', '7161926828163072', '1', '0');
INSERT INTO `menu` VALUES ('7161926886883328', '/manualSign/**', '/check/employeeCheck', 'ManualSign', '员工签到/签退', '', '0', '7161926828163072', '1', '0');
INSERT INTO `menu` VALUES ('7161926907854848', '/', '/home', 'Home', '系统管理', '', '0', '0', '1', '0');
INSERT INTO `menu` VALUES ('7161926924632064', '/loginLog/**', '/system/loginRecord', 'loginRecord', '登录日志', '', '0', '7161926907854848', '1', '0');
INSERT INTO `menu` VALUES ('7161926941409280', '/operationLog/**', '/system/operationRecord', 'operateRecord', '操作日志', '', '0', '7161926907854848', '1', '0');
INSERT INTO `menu` VALUES ('7161926962380800', '/role/**', '/system/roleManage', 'roleManage', '角色管理', '', '0', '7161926907854848', '1', '0');
INSERT INTO `menu` VALUES ('7161926979158016', '/menu/**', '/system/menuOrder', 'menuOrder', '菜单排序', '', '0', '7161926907854848', '1', '0');

-- ----------------------------
-- Table structure for menurole
-- ----------------------------
DROP TABLE IF EXISTS `menurole`;
CREATE TABLE `menurole` (
  `menuRoleId` bigint(20) unsigned NOT NULL,
  `menuId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`menuRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menurole
-- ----------------------------
INSERT INTO `menurole` VALUES ('7161926480035840', '7161924118642688', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926513590272', '7161926496813056', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926576504832', '7161926534561792', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926614253568', '7161926601670656', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926639419392', '7161926626836480', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926660390912', '7161926647808000', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926681362432', '7161926668779520', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926702333952', '7161926693945344', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926731694080', '7161926719111168', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926748471296', '7161926740082688', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926765248512', '7161926756859904', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926786220032', '7161926773637120', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926798802944', '7161926790414336', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926819774464', '7161926807191552', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926836551680', '7161926828163072', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926861717504', '7161926844940288', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926878494720', '7161926870106112', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926895271936', '7161926886883328', '2867590938492928');
INSERT INTO `menurole` VALUES ('7161926916243456', '7161926907854848', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926933020672', '7161926924632064', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926949797888', '7161926941409280', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926970769408', '7161926962380800', '2867588375773184');
INSERT INTO `menurole` VALUES ('7161926987546624', '7161926979158016', '2867588375773184');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `title` varchar(100) NOT NULL COMMENT '消息标题',
  `content` varchar(5000) NOT NULL COMMENT '消息内容',
  `type` int(11) NOT NULL COMMENT '消息类型',
  `beginTime` datetime NOT NULL COMMENT '开始有效时间',
  `endTime` datetime NOT NULL COMMENT '有效结束时间',
  `fromUserId` bigint(20) NOT NULL COMMENT '发送者',
  `ifPublish` int(1) NOT NULL DEFAULT '0' COMMENT '是否已发布（发布：1；未发布：0）',
  `recordTime` datetime NOT NULL COMMENT '发送时间',
  `ifDelete` int(1) NOT NULL COMMENT '删除标志位（1：已删除；0：未删除）',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `updateUserId` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `ifAll` int(1) NOT NULL COMMENT '是否是所有人（0：不是所有人；1：是所有人）',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '测试消息', '新增人员测试消息', '2', '2019-09-19 16:15:05', '2019-09-20 16:15:13', '6845764974149632', '0', '2019-09-25 13:07:26', '0', '2019-09-19 16:17:50', '6845764974149632', '0');
INSERT INTO `message` VALUES ('2', '新增消息', '测试新增消息', '1', '2019-09-21 11:04:28', '2019-09-21 11:04:28', '6845764974149632', '0', '2019-09-25 13:07:31', '0', '2019-09-24 18:45:40', '6845764974149632', '1');
INSERT INTO `message` VALUES ('3', 'Test', 'Test新消息', '1', '2019-09-21 12:58:56', '2019-09-25 12:59:01', '6866337364180992', '0', '2019-09-25 13:39:51', '0', '2019-09-25 12:59:25', '6845764974149632', '0');
INSERT INTO `message` VALUES ('4', 'Test111', 'Test111新消息', '2', '2019-09-20 13:00:12', '2019-09-24 13:00:17', '6866337364180992', '0', '2019-09-25 13:40:15', '0', '2019-09-25 13:00:39', '6866337364180992', '0');

-- ----------------------------
-- Table structure for messagetouser
-- ----------------------------
DROP TABLE IF EXISTS `messagetouser`;
CREATE TABLE `messagetouser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号id',
  `messageId` int(11) NOT NULL COMMENT '消息id（外-message-messageid）',
  `touserId` bigint(20) NOT NULL COMMENT '发送对象id（外-userinfo-userid）',
  `ifRead` int(1) NOT NULL COMMENT '是否已读（1：已读；0：未读）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messagetouser
-- ----------------------------
INSERT INTO `messagetouser` VALUES ('1', '1', '6845764974149632', '0');
INSERT INTO `messagetouser` VALUES ('2', '1', '6866337364180992', '0');
INSERT INTO `messagetouser` VALUES ('3', '2', '6845764974149632', '0');

-- ----------------------------
-- Table structure for messagetype
-- ----------------------------
DROP TABLE IF EXISTS `messagetype`;
CREATE TABLE `messagetype` (
  `messageTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息类型id',
  `messageTypeName` varchar(50) NOT NULL COMMENT '消息类型名称',
  `messageDesc` varchar(50) DEFAULT NULL COMMENT '消息类型描述',
  PRIMARY KEY (`messageTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messagetype
-- ----------------------------
INSERT INTO `messagetype` VALUES ('1', '紧急消息', '优先级1');
INSERT INTO `messagetype` VALUES ('2', '一般消息', '优先级2');

-- ----------------------------
-- Table structure for mynote
-- ----------------------------
DROP TABLE IF EXISTS `mynote`;
CREATE TABLE `mynote` (
  `noteId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '便签id',
  `noteTitle` varchar(50) NOT NULL COMMENT '便签标题',
  `noteContent` varchar(500) DEFAULT NULL COMMENT '便签内容',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `createUser` varchar(50) NOT NULL COMMENT '创建者',
  PRIMARY KEY (`noteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mynote
-- ----------------------------

-- ----------------------------
-- Table structure for operatelog
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog` (
  `operateId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '操作日志id',
  `userId` bigint(20) NOT NULL COMMENT '操作者',
  `operateName` varchar(50) NOT NULL COMMENT '操作名称',
  `objectId` bigint(20) NOT NULL COMMENT '操作对象Id',
  `operateDesc` varchar(200) NOT NULL COMMENT '操作描述',
  `operateTime` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`operateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operatelog
-- ----------------------------

-- ----------------------------
-- Table structure for precontract
-- ----------------------------
DROP TABLE IF EXISTS `precontract`;
CREATE TABLE `precontract` (
  `preContractId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预约序号id',
  `scheduleId` bigint(20) NOT NULL COMMENT '日程id（外-schedule-scheduleid）',
  `userId` bigint(20) NOT NULL COMMENT '预约人（外-userinfo-userId）',
  PRIMARY KEY (`preContractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of precontract
-- ----------------------------

-- ----------------------------
-- Table structure for readcommonmessage
-- ----------------------------
DROP TABLE IF EXISTS `readcommonmessage`;
CREATE TABLE `readcommonmessage` (
  `readId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号id',
  `messageId` int(11) NOT NULL COMMENT '消息id（外-message-mesageId）',
  `userId` bigint(20) NOT NULL COMMENT '消息读取者（外-userinfo-uerId）',
  PRIMARY KEY (`readId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readcommonmessage
-- ----------------------------
INSERT INTO `readcommonmessage` VALUES ('1', '2', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('2', '2', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('3', '2', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('4', '1', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('5', '1', '6866337364180992');
INSERT INTO `readcommonmessage` VALUES ('6', '1', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('7', '1', '6866337364180992');
INSERT INTO `readcommonmessage` VALUES ('8', '1', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('9', '1', '6866337364180992');
INSERT INTO `readcommonmessage` VALUES ('10', '1', '6845764974149632');
INSERT INTO `readcommonmessage` VALUES ('11', '1', '6866337364180992');
INSERT INTO `readcommonmessage` VALUES ('12', '2', '6845764974149632');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` bigint(20) unsigned NOT NULL,
  `roleName` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `roleDesc` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2867588375773184', 'ROLE_ADMIN', '超级管理员');
INSERT INTO `role` VALUES ('2867590917521408', 'ROLE_HR', '管理员');
INSERT INTO `role` VALUES ('2867590938492928', 'ROLE_USER', '员工');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `scheduleId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日程id',
  `title` varchar(50) NOT NULL COMMENT '日程标题',
  `address` varchar(500) NOT NULL COMMENT '会议地址',
  `meetingId` bigint(20) NOT NULL COMMENT '会议类型（外-meetinginfo-meetingId）',
  `beginTime` datetime NOT NULL COMMENT '日程开始时间',
  `endTime` datetime NOT NULL COMMENT '日程结束时间',
  `schContent` varchar(500) NOT NULL COMMENT '日程内容',
  `createUser` varchar(50) NOT NULL COMMENT '创建者',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `ifPrivate` int(1) NOT NULL DEFAULT '1' COMMENT '是否私有（1：私有；0：非私有）',
  PRIMARY KEY (`scheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` bigint(20) unsigned NOT NULL,
  `username` varchar(16) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(16) NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '-1',
  `avatar` varchar(255) NOT NULL DEFAULT 'http://pic.51yuansu.com/pic3/cover/02/96/71/5ad09ba522904_610.jpg',
  `departmentId` bigint(20) unsigned NOT NULL,
  `isBlocked` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6845764974149632', 'admin', '$2a$10$RgxBG0PKQwQ5.3tLAQyciO8sosgHBjTVL7y8EfugL3Jgj111e6Yzm', '超级管理员', '0', 'string', '4718691837018112', '0');
INSERT INTO `user` VALUES ('6866337364180992', 'user', '$2a$10$RgxBG0PKQwQ5.3tLAQyciO8sosgHBjTVL7y8EfugL3Jgj111e6Yzm', '员工', '0', 'string', '4718691837018112', '0');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `userRoleId` bigint(20) unsigned NOT NULL,
  `userId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES ('6845765653626880', '6845764974149632', '2867588375773184');
INSERT INTO `userrole` VALUES ('6866338102378496', '6866337364180992', '2867590938492928');
INSERT INTO `userrole` VALUES ('10451781146378240', '10451780160716800', '0');

-- ----------------------------
-- Table structure for worktime
-- ----------------------------
DROP TABLE IF EXISTS `worktime`;
CREATE TABLE `worktime` (
  `worktimeId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '工作时间id',
  `ondutyTime` varchar(50) NOT NULL COMMENT '上班时间',
  `offdutyTime` varchar(50) NOT NULL COMMENT '下班时间',
  PRIMARY KEY (`worktimeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of worktime
-- ----------------------------
