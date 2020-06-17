/*
 Navicat Premium Data Transfer

 Source Server         : localtest
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : devicedb

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 17/06/2020 04:35:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_pid` int(11) NOT NULL COMMENT '接口父ID(即接口分组)',
  `api_pids` varchar(64) NOT NULL COMMENT '当前接口的所有上级id(即所有上级分组)',
  `is_leaf` tinyint(1) NOT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `api_name` varchar(64) NOT NULL COMMENT '接口名称',
  `url` varchar(64) DEFAULT NULL COMMENT '跳转URL',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) NOT NULL COMMENT '层级，1：接口分组，2：接口',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='系统Http接口表，配合sys_role_api控制接口访问权限';

-- ----------------------------
-- Records of sys_api
-- ----------------------------
BEGIN;
INSERT INTO `sys_api` VALUES (1, 0, '[0]', 0, '系统数据接口', NULL, 1, 1, 0);
INSERT INTO `sys_api` VALUES (2, 1, '[0],[1]', 0, '系统管理模块', NULL, 1, 2, 0);
INSERT INTO `sys_api` VALUES (3, 2, '[0],[1],[2]', 1, '用户信息接口', '/sysuser/info', 1, 3, 0);
INSERT INTO `sys_api` VALUES (4, 2, '[0],[1],[2]', 1, '组织管理-树形数据接口', '/sysorg/tree', 2, 3, 0);
INSERT INTO `sys_api` VALUES (5, 2, '[0],[1],[2]', 1, '组织管理-新增组织接口', '/sysorg/add', 3, 3, 0);
INSERT INTO `sys_api` VALUES (6, 2, '[0],[1],[2]', 1, '组织管理-修改组织接口', '/sysorg/update', 4, 3, 0);
INSERT INTO `sys_api` VALUES (7, 2, '[0],[1],[2]', 1, '组织管理-删除组织接口', '/sysorg/delete', 5, 3, 0);
INSERT INTO `sys_api` VALUES (8, 2, '[0],[1],[2]', 1, '菜单树形数据加载接口', '/sysmenu/tree', 6, 3, 0);
INSERT INTO `sys_api` VALUES (9, 2, '[0],[1],[2]', 1, '菜单管理-新增菜单项接口', '/sysmenu/add', 7, 3, 0);
INSERT INTO `sys_api` VALUES (10, 2, '[0],[1],[2]', 1, '菜单管理-修改菜单项接口', '/sysmenu/update', 8, 3, 0);
INSERT INTO `sys_api` VALUES (11, 2, '[0],[1],[2]', 1, '菜单管理-删除菜单项接口', '/sysmenu/delete', 9, 3, 0);
INSERT INTO `sys_api` VALUES (12, 2, '[0],[1],[2]', 1, '查询某角色已具备菜单权限接口', '/sysmenu/checkedtree', 10, 3, 0);
INSERT INTO `sys_api` VALUES (13, 2, '[0],[1],[2]', 1, '保存某角色分配勾选的菜单权限', '/sysmenu/savekeys', 11, 3, 0);
INSERT INTO `sys_api` VALUES (14, 2, '[0],[1],[2]', 1, '接口分类树形结构数据加载', '/sysapi/tree', 12, 3, 0);
INSERT INTO `sys_api` VALUES (15, 2, '[0],[1],[2]', 1, '接口管理-新增接口', '/sysapi/add', 13, 3, 0);
INSERT INTO `sys_api` VALUES (16, 2, '[0],[1],[2]', 1, '接口管理-更新接口数据', '/sysapi/update', 14, 3, 0);
INSERT INTO `sys_api` VALUES (17, 2, '[0],[1],[2]', 1, '接口管理-删除接口', '/sysapi/delete', 15, 3, 0);
INSERT INTO `sys_api` VALUES (18, 2, '[0],[1],[2]', 1, '查询某角色已具备的接口访问权限', '/sysapi/checkedtree', 16, 3, 0);
INSERT INTO `sys_api` VALUES (19, 2, '[0],[1],[2]', 1, '保存某角色勾选分配的接口访问权限', '/sysapi/savekeys', 17, 3, 0);
INSERT INTO `sys_api` VALUES (20, 2, '[0],[1],[2]', 1, '角色管理-列表查询', '/sysrole/query', 18, 3, 0);
INSERT INTO `sys_api` VALUES (21, 2, '[0],[1],[2]', 1, '角色管理-新增角色', '/sysrole/add', 19, 3, 0);
INSERT INTO `sys_api` VALUES (22, 2, '[0],[1],[2]', 1, '角色管理-更新角色数据', '/sysrole/update', 20, 3, 0);
INSERT INTO `sys_api` VALUES (23, 2, '[0],[1],[2]', 1, '角色管理-删除角色', '/sysrole/delete', 21, 3, 0);
INSERT INTO `sys_api` VALUES (24, 2, '[0],[1],[2]', 1, '查询某用户具备的角色id列表', '/sysrole/checkedroles', 22, 3, 0);
INSERT INTO `sys_api` VALUES (25, 2, '[0],[1],[2]', 1, '保存为某用户分配的角色', '/sysrole/savekeys', 23, 3, 0);
INSERT INTO `sys_api` VALUES (26, 2, '[0],[1],[2]', 1, '用户管理-用户列表查询', '/sysuser/query', 24, 3, 0);
INSERT INTO `sys_api` VALUES (27, 2, '[0],[1],[2]', 1, '用户管理-新增用户', '/sysuser/add', 25, 3, 0);
INSERT INTO `sys_api` VALUES (28, 2, '[0],[1],[2]', 1, '用户管理-修改用户信息', '/sysuser/update', 26, 3, 0);
INSERT INTO `sys_api` VALUES (29, 2, '[0],[1],[2]', 1, '用户管理-删除用户', '/sysuser/delete', 27, 3, 0);
INSERT INTO `sys_api` VALUES (30, 2, '[0],[1],[2]', 1, '为用户重置密码', '/sysuser/pwd/reset', 28, 3, 0);
INSERT INTO `sys_api` VALUES (31, 2, '[0],[1],[2]', 1, '判断用户是否使用默认密码', '/sysuser/pwd/isdefault', 29, 3, 0);
INSERT INTO `sys_api` VALUES (32, 2, '[0],[1],[2]', 1, '修改用户密码', '/sysuser/pwd/change', 30, 3, 0);
INSERT INTO `sys_api` VALUES (33, 2, '[0],[1],[2]', 1, '菜单栏数据接口(根据登录用户)', '/sysmenu/tree/user', 6, 3, 0);
INSERT INTO `sys_api` VALUES (34, 2, '[0],[1],[2]', 1, '获取系统全局配置参数', '/sysconfig/all', 31, 3, 0);
INSERT INTO `sys_api` VALUES (35, 2, '[0],[1],[2]', 1, '条件查询全局配置参数接口', '/sysconfig/query', 32, 3, 0);
INSERT INTO `sys_api` VALUES (36, 2, '[0],[1],[2]', 1, '新增配置参数接口', '/sysconfig/add', 33, 3, 0);
INSERT INTO `sys_api` VALUES (37, 2, '[0],[1],[2]', 1, '修改配置参数接口', '/sysconfig/update', 34, 3, 0);
INSERT INTO `sys_api` VALUES (38, 2, '[0],[1],[2]', 1, '删除配置参数接口', '/sysconfig/delete', 35, 3, 0);
INSERT INTO `sys_api` VALUES (39, 2, '[0],[1],[2]', 1, '配置参数从数据库刷新到内存', '/sysconfig/refresh', 36, 3, 0);
INSERT INTO `sys_api` VALUES (40, 2, '[0],[1],[2]', 1, '数据字典数据加载接口', '/sysdict/all', 37, 3, 0);
INSERT INTO `sys_api` VALUES (41, 2, '[0],[1],[2]', 1, '数据字典条件查询接口', '/sysdict/query', 38, 3, 0);
INSERT INTO `sys_api` VALUES (42, 2, '[0],[1],[2]', 1, '数据字典数据新增接口', '/sysdict/add', 39, 3, 0);
INSERT INTO `sys_api` VALUES (43, 2, '[0],[1],[2]', 1, '数据字典数据修改接口', '/sysdict/update', 40, 3, 0);
INSERT INTO `sys_api` VALUES (44, 2, '[0],[1],[2]', 1, '数据字典数据删除接口', '/sysdict/delete', 41, 3, 0);
INSERT INTO `sys_api` VALUES (47, 2, '[0],[1],[2]', 1, '用户管理-查看分配的角色', '/sysuser/checkedroles', 1, 3, 0);
INSERT INTO `sys_api` VALUES (48, 2, '[0],[1],[2]', 1, '用户管理-分配角色', '/sysuser/saveroles', 1, 3, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(32) NOT NULL COMMENT '参数名称(中文)',
  `param_key` varchar(64) NOT NULL COMMENT '参数编码唯一标识(英文及数字)',
  `param_value` varchar(64) NOT NULL COMMENT '参数值',
  `param_desc` varchar(64) DEFAULT NULL COMMENT '参数描述备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统全局配置参数';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, '用户初始密码', 'user.init.password', 'abcd1234', '系统新增用户初始化密码（登录后会提示用户自行修改）', '2020-02-29 13:26:58');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) NOT NULL COMMENT '分组名称',
  `group_code` varchar(64) NOT NULL COMMENT '分组编码',
  `item_name` varchar(16) NOT NULL COMMENT '字典项名称',
  `item_value` varchar(16) NOT NULL COMMENT '字典项Value',
  `item_desc` varchar(64) DEFAULT NULL COMMENT '字典项描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '字典项创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, '是否禁用', 'common.status', '未禁用', 'false', '通用数据记录的禁用状态', '2020-02-29 17:00:16');
INSERT INTO `sys_dict` VALUES (2, '是否禁用', 'common.status', '已禁用', 'true', '通用数据记录的禁用状态', '2020-02-29 17:00:26');
INSERT INTO `sys_dict` VALUES (3, '用户状态', 'sysuser.enabled', '已激活', 'true', '用户状态', '2020-02-29 18:42:08');
INSERT INTO `sys_dict` VALUES (4, '用户状态', 'sysuser.enabled', '已禁用', 'false', '用户状态', '2020-02-29 23:23:35');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_pid` int(11) NOT NULL COMMENT '父菜单ID',
  `menu_pids` varchar(64) NOT NULL COMMENT '当前菜单所有父菜单',
  `is_leaf` tinyint(1) NOT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `menu_name` varchar(16) NOT NULL COMMENT '菜单名称',
  `url` varchar(64) DEFAULT NULL COMMENT '跳转URL',
  `icon` varchar(45) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) NOT NULL COMMENT '菜单层级',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '[0]', 0, '系统根目录', '/', '', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (2, 1, '[0],[1]', 0, '系统管理', '/system', 'el-icon-setting', 1, 2, 0);
INSERT INTO `sys_menu` VALUES (3, 2, '[0],[1],[2]', 1, '用户管理', '/home/sysuser', 'el-icon-user', 1, 3, 0);
INSERT INTO `sys_menu` VALUES (4, 2, '[0],[1],[2]', 1, '角色管理', '/home/sysrole', 'el-icon-coordinate', 2, 3, 0);
INSERT INTO `sys_menu` VALUES (5, 2, '[0],[1],[2]', 1, '组织管理', '/home/sysorg', 'el-icon-s-custom', 3, 3, 0);
INSERT INTO `sys_menu` VALUES (6, 2, '[0],[1],[2]', 1, '菜单管理', '/home/sysmenu', 'el-icon-menu', 4, 3, 0);
INSERT INTO `sys_menu` VALUES (7, 2, '[0],[1],[2]', 1, '接口管理', '/home/sysapi', 'el-icon-help', 5, 3, 1);
INSERT INTO `sys_menu` VALUES (10, 1, '[0],[1]', 0, '其他菜单', '/other', 'el-icon-eleme', 2, 2, 0);
INSERT INTO `sys_menu` VALUES (11, 10, '[0],[1],[10]', 1, '首页', '/home/firstpage', 'el-icon-lock', 1, 3, 0);
INSERT INTO `sys_menu` VALUES (13, 2, '[0],[1],[2]', 1, '数据字典', '/home/sysdict', 'el-icon-fa fa-list-ol', 7, 3, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_pid` int(11) NOT NULL COMMENT '上级组织编码',
  `org_pids` varchar(64) NOT NULL COMMENT '所有的父节点id',
  `is_leaf` tinyint(1) NOT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `org_name` varchar(32) NOT NULL COMMENT '组织名',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `phone` varchar(13) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮件',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) NOT NULL COMMENT '组织层级',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统组织结构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` VALUES (1, 0, '[0]', 0, '杭州xxx公司', NULL, '13411111111', NULL, 1, 1, 0);
INSERT INTO `sys_org` VALUES (2, 1, '[0],[1]', 0, '研发团队', '杭州xxx', '13411111111', 'beijing21212@email.com', 1, 2, 0);
INSERT INTO `sys_org` VALUES (3, 2, '[0],[1],[2]', 1, '前端部', '北京海淀区中关村', '13411111111', 'hanxi@email.com', 1, 3, 0);
INSERT INTO `sys_org` VALUES (4, 2, '[0],[1],[2]', 1, '后端部', NULL, '13411111111', 'cesh@gmail.com', 2, 3, 0);
INSERT INTO `sys_org` VALUES (5, 1, '[0],[1]', 0, '其他团队', '上海黄浦区', '13411111111', 'shanghaixx@email.com', 2, 2, 0);
INSERT INTO `sys_org` VALUES (6, 5, '[0],[1],[5]', 1, '运维部', '上海黄浦区', '13411111111', NULL, 1, 3, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL DEFAULT '0' COMMENT '角色名称(汉字)',
  `role_desc` varchar(128) NOT NULL DEFAULT '0' COMMENT '角色描述',
  `role_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '角色的英文code.如：ADMIN',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '角色顺序',
  `status` tinyint(1) DEFAULT '0' COMMENT '是否禁用，0:启用(否）,1:禁用(是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', '系统管理员', 'admin', 1, 0);
INSERT INTO `sys_role` VALUES (2, '普通用户', '普通用户', 'common', 2, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_api`;
CREATE TABLE `sys_role_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `api_id` int(11) NOT NULL COMMENT '接口id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=561 DEFAULT CHARSET=utf8 COMMENT='角色接口权限关系表';

-- ----------------------------
-- Records of sys_role_api
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_api` VALUES (370, 2, 3);
INSERT INTO `sys_role_api` VALUES (371, 2, 4);
INSERT INTO `sys_role_api` VALUES (372, 2, 5);
INSERT INTO `sys_role_api` VALUES (373, 2, 6);
INSERT INTO `sys_role_api` VALUES (374, 2, 7);
INSERT INTO `sys_role_api` VALUES (375, 2, 8);
INSERT INTO `sys_role_api` VALUES (382, 2, 20);
INSERT INTO `sys_role_api` VALUES (515, 1, 1);
INSERT INTO `sys_role_api` VALUES (516, 1, 2);
INSERT INTO `sys_role_api` VALUES (517, 1, 3);
INSERT INTO `sys_role_api` VALUES (518, 1, 48);
INSERT INTO `sys_role_api` VALUES (519, 1, 47);
INSERT INTO `sys_role_api` VALUES (520, 1, 4);
INSERT INTO `sys_role_api` VALUES (521, 1, 5);
INSERT INTO `sys_role_api` VALUES (522, 1, 6);
INSERT INTO `sys_role_api` VALUES (523, 1, 7);
INSERT INTO `sys_role_api` VALUES (524, 1, 8);
INSERT INTO `sys_role_api` VALUES (525, 1, 33);
INSERT INTO `sys_role_api` VALUES (526, 1, 9);
INSERT INTO `sys_role_api` VALUES (527, 1, 10);
INSERT INTO `sys_role_api` VALUES (528, 1, 11);
INSERT INTO `sys_role_api` VALUES (529, 1, 12);
INSERT INTO `sys_role_api` VALUES (530, 1, 13);
INSERT INTO `sys_role_api` VALUES (531, 1, 14);
INSERT INTO `sys_role_api` VALUES (532, 1, 15);
INSERT INTO `sys_role_api` VALUES (533, 1, 16);
INSERT INTO `sys_role_api` VALUES (534, 1, 17);
INSERT INTO `sys_role_api` VALUES (535, 1, 18);
INSERT INTO `sys_role_api` VALUES (536, 1, 19);
INSERT INTO `sys_role_api` VALUES (537, 1, 20);
INSERT INTO `sys_role_api` VALUES (538, 1, 21);
INSERT INTO `sys_role_api` VALUES (539, 1, 22);
INSERT INTO `sys_role_api` VALUES (540, 1, 23);
INSERT INTO `sys_role_api` VALUES (541, 1, 24);
INSERT INTO `sys_role_api` VALUES (542, 1, 25);
INSERT INTO `sys_role_api` VALUES (543, 1, 26);
INSERT INTO `sys_role_api` VALUES (544, 1, 27);
INSERT INTO `sys_role_api` VALUES (545, 1, 28);
INSERT INTO `sys_role_api` VALUES (546, 1, 29);
INSERT INTO `sys_role_api` VALUES (547, 1, 30);
INSERT INTO `sys_role_api` VALUES (548, 1, 31);
INSERT INTO `sys_role_api` VALUES (549, 1, 32);
INSERT INTO `sys_role_api` VALUES (550, 1, 34);
INSERT INTO `sys_role_api` VALUES (551, 1, 35);
INSERT INTO `sys_role_api` VALUES (552, 1, 36);
INSERT INTO `sys_role_api` VALUES (553, 1, 37);
INSERT INTO `sys_role_api` VALUES (554, 1, 38);
INSERT INTO `sys_role_api` VALUES (555, 1, 39);
INSERT INTO `sys_role_api` VALUES (556, 1, 40);
INSERT INTO `sys_role_api` VALUES (557, 1, 41);
INSERT INTO `sys_role_api` VALUES (558, 1, 42);
INSERT INTO `sys_role_api` VALUES (559, 1, 43);
INSERT INTO `sys_role_api` VALUES (560, 1, 44);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8 COMMENT='角色菜单权限关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (197, 2, 7);
INSERT INTO `sys_role_menu` VALUES (198, 2, 12);
INSERT INTO `sys_role_menu` VALUES (210, 1, 1);
INSERT INTO `sys_role_menu` VALUES (211, 1, 2);
INSERT INTO `sys_role_menu` VALUES (212, 1, 3);
INSERT INTO `sys_role_menu` VALUES (213, 1, 4);
INSERT INTO `sys_role_menu` VALUES (214, 1, 5);
INSERT INTO `sys_role_menu` VALUES (215, 1, 6);
INSERT INTO `sys_role_menu` VALUES (216, 1, 7);
INSERT INTO `sys_role_menu` VALUES (217, 1, 12);
INSERT INTO `sys_role_menu` VALUES (218, 1, 10);
INSERT INTO `sys_role_menu` VALUES (219, 1, 11);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '0' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '0' COMMENT '密码',
  `org_id` int(11) NOT NULL COMMENT '组织id',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0无效用户，1是有效用户',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT 'email',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$eWxhrcPVnMMrnyrxWa./1.d19QuBMS33amEaVVjIETsxk4kes2HKG', 1, 1, '13453447185', 'aa@163.com', '2020-06-16 05:49:44');
INSERT INTO `sys_user` VALUES (2, 'test', '$2a$10$eWxhrcPVnMMrnyrxWa./1.d19QuBMS33amEaVVjIETsxk4kes2HKG', 3, 1, '13411111111', '', '2020-06-14 01:49:38');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色自增id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户自增id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (10, 1, 1);
INSERT INTO `sys_user_role` VALUES (13, 2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
