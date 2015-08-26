delete from sh_admin_user_has_sh_authority_group;
delete from sh_authority_group_has_sh_authority;
delete from sh_authority_group;
delete from sh_authority;
delete from sh_admin_user;
delete from sh_company;

INSERT INTO `sh_company` (`id`,`company_name`,`address`,`name`,`tel`,`enabled`,`create_time`,`update_time`) VALUES (1,'北京世和网络科技有限公司','北京市朝阳区融科望京中心B座1103','刘总','12345678913','1','2015-07-31 10:59:41','2015-07-31 10:59:41');

INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (1,1,'videoManage','题目类视频管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (2,1,'simulateIssuesManage','真题/模拟题库管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (3,1,'selfIssuesManage','自编/改编题库管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (4,1,'simulateStructureManage','真题/模拟题库结构');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (5,1,'knowledgeManage','知识点类视频管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (6,1,'knowledgeVideoManage','知识点视频管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (7,1,'knowledgeStructureManage','知识点结构管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (8,1,'infoManage','信息管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (9,1,'knowledgeTagManage','知识点管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (10,1,'lectureManage','讲解人管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (11,1,'adminUserManage','教师管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (12,1,'adminUserList','教师管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (13,1,'keywordTagManage','关键字标签管理');

INSERT INTO `sh_authority_group` (`id`,`sh_company_id`,`name`,`remark`) VALUES (1,1,'advancedAdmin','高级管理员');
INSERT INTO `sh_authority_group` (`id`,`sh_company_id`,`name`,`remark`) VALUES (2,1,'normalAdmin','管理员');

INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,1);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (2,1);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,2);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (2,2);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,3);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (2,3);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,5);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (2,5);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,6);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (2,6);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,8);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,10);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,11);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,12);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,13);

INSERT INTO `sh_admin_user` (`id`,`sh_company_id`,`realname`,`nickname`,`password`,`sex`,`address`,`email`,`tel`,`create_time`,`update_time`,`enabled`,`remark`,`position`,`head_pic`) VALUES (1,1,'管理员','admin','$2a$10$yKqARHL7UI3Si1C3ou0SK.w9pgcFOWhJRD6IuoUTZ3UnJDHDSR4fy','0','北京朝阳区望京中心','admin_hi@163.com','13611113333','2015-07-31 11:00:00','2015-08-26 18:20:23','1','数学讲师','老师','http://p2.gexing.com/touxiang/20120802/0922/5019d66eef7ed_200x200_3.jpg');
INSERT INTO `sh_admin_user` (`id`,`sh_company_id`,`realname`,`nickname`,`password`,`sex`,`address`,`email`,`tel`,`create_time`,`update_time`,`enabled`,`remark`,`position`,`head_pic`) VALUES (2,1,NULL,'root','$2a$10$Qsid/FR/9xuFGehZOzYtbeMfv8bqyIe5Pd7uSadHR2nhaAWXwz752',NULL,NULL,NULL,NULL,'2015-08-26 17:56:00','2015-08-26 18:10:19','1',NULL,NULL,NULL);

INSERT INTO `sh_admin_user_has_sh_authority_group` (`sh_admin_user_id`,`sh_authority_group_id`) VALUES (1,1);
INSERT INTO `sh_admin_user_has_sh_authority_group` (`sh_admin_user_id`,`sh_authority_group_id`) VALUES (2,1);

