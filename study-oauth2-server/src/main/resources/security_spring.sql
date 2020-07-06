/*Table structure for table `user_account_role` */

DROP TABLE IF EXISTS `user_account_role`;

CREATE TABLE `user_account_role` (
  `account_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `user_account_role_1` (`role_id`),
  CONSTRAINT `user_account_role_1` FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`id`),
  CONSTRAINT `user_account_role_2` FOREIGN KEY (`account_id`) REFERENCES `user_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_account_role` */

insert  into `user_account_role`(`account_id`,`role_id`) values (1,1),(2,2);

/*Table structure for table `user_accounts` */

DROP TABLE IF EXISTS `user_accounts`;

CREATE TABLE `user_accounts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(256) NOT NULL COMMENT '由用户名+手机号+邮箱生成的登录账号',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `email` varchar(256) DEFAULT NULL COMMENT '邮件',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `salt` varchar(256) DEFAULT NULL COMMENT '盐',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定（0表示未锁定，可用，1表示不可用）',
  `company_code` bigint(20) unsigned DEFAULT NULL COMMENT '公司id',
  `create_time` datetime DEFAULT NULL COMMENT '// 创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '// 修改时间',
  `account_type` int(10) unsigned NOT NULL COMMENT '账号类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `user_accounts` */

insert  into `user_accounts`(`id`,`uuid`,`username`,`phone`,`email`,`password`,`salt`,`locked`,`company_code`,`create_time`,`modify_time`,`account_type`) values (1,'123','admin','15156565656','15156565656@163.com','$2a$10$G19hmyZuyzeFcBbw/5V9U.ooxmudLPaCNws8ZVsb/cqWDE2XZWNEi',NULL,0,1,'2019-03-14 09:51:43','2019-03-14 09:51:47',0),(2,'123','user','12155556666','12155556666@163.com','$2a$10$AYa/fMCvbPDZN3pFcwRAI.Tdq2esT3nQ1f53lGbCEqEU2wbJ9XDbu',NULL,0,1,'2019-03-14 09:52:48','2019-03-14 09:52:51',1);

/*Table structure for table `user_permissions` */

DROP TABLE IF EXISTS `user_permissions`;

CREATE TABLE `user_permissions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(256) NOT NULL COMMENT '请求路径',
  `method` varchar(256) NOT NULL COMMENT '请求风格',
  `describes` varchar(256) NOT NULL COMMENT '功能描述',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `parent_id` bigint(20) NOT NULL COMMENT '上级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `user_permissions` */

insert  into `user_permissions`(`id`,`url`,`method`,`describes`,`locked`,`create_time`,`modify_time`,`parent_id`) values (1,'/user/findAll','','查询所有',0,NULL,NULL,0),(2,'/user/findById','','根据id查询',0,NULL,NULL,0);

/*Table structure for table `user_role_permission` */

DROP TABLE IF EXISTS `user_role_permission`;

CREATE TABLE `user_role_permission` (
  `role_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  `company_code` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`,`company_code`),
  KEY `user_role_permission_1` (`permission_id`),
  CONSTRAINT `user_role_permission_1` FOREIGN KEY (`permission_id`) REFERENCES `user_permissions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_permission_2` FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role_permission` */

insert  into `user_role_permission`(`role_id`,`permission_id`,`company_code`) values (1,1,0),(1,2,0),(2,2,0);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(256) NOT NULL COMMENT '角色名称',
  `describes` varchar(256) NOT NULL COMMENT '角色描述',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `roles_type` tinyint(1) unsigned NOT NULL DEFAULT '2' COMMENT ' // 0 admin 1默认 2自定义',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user_roles` */

insert  into `user_roles`(`id`,`rolename`,`describes`,`locked`,`create_time`,`modify_time`,`roles_type`) values (1,'ROLE_admin','管理员',0,'2019-03-14 16:31:10','2019-03-14 10:42:36',1),(2,'ROLE_zadmin','用户',0,'2019-03-14 09:54:11','2019-03-14 09:54:13',2);


DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;