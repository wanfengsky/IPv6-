-- //======================== Tables Data SQL ========================// --
-- //=========== comment eg. : yyyyMMdd ${table_name} desc ===========// --

-- 20201126 职称Dict user_title_define
INSERT INTO sys_dict_type(dict_id, dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
    VALUES (100, '用户职称定义', 'user_title_define', '0', 'admin', '2020-11-26 20:09:06', '', NULL, '用户职称定义');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (100, 1, '教授', '1', 'user_title_define', '', 'danger', 'N', '0', 'admin', '2020-11-26 20:11:13', 'admin', '2020-11-26 22:57:21', '职称：教授');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (101, 2, '副教授', '2', 'user_title_define', '', 'success', 'N', '0', 'admin', '2020-11-26 20:11:37', 'admin', '2020-11-26 22:58:24', '职称：副教授');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (102, 3, '讲师', '3', 'user_title_define', '', 'primary', 'N', '0', 'admin', '2020-11-26 22:56:31', 'admin', '2020-11-26 22:57:08', '职称：讲师');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (103, 4, '助教', '4', 'user_title_define', '', 'info', 'Y', '0', 'admin', '2020-11-26 22:56:55', 'admin', '2020-11-26 22:58:16', '职称：助教');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (104, 5, '教授级高工', '5', 'user_title_define', '', '', 'N', '0', 'admin', '2020-11-26 22:58:03', 'admin', '2020-11-26 22:59:55', '职称：教授级高工');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (105, 6, '高级工程师', '6', 'user_title_define', '', '', 'N', '0', 'admin', '2020-11-26 22:59:26', 'admin', '2020-11-26 23:00:01', '职称：高级工程师');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark) VALUES (106, 7, '中级工程师', '7', 'user_title_define', NULL, NULL, 'N', '0', 'admin', '2020-11-26 23:00:35', '', NULL, '职称：中级工程师');


-- 20201203 代码生成的菜单 课程、章节、签到、签到记录
-- course 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理', '2000', '1', '/course/course', 'C', '0', 'course:course:view', '#', 'admin', sysdate(), '', null, '课程菜单');
SELECT @parentId := LAST_INSERT_ID();
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程查询', @parentId, '1',  '#',  'F', '0', 'course:course:list',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程新增', @parentId, '2',  '#',  'F', '0', 'course:course:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程修改', @parentId, '3',  '#',  'F', '0', 'course:course:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程删除', @parentId, '4',  '#',  'F', '0', 'course:course:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程导出', @parentId, '5',  '#',  'F', '0', 'course:course:export',       '#', 'admin', sysdate(), '', null, '');
-- chapter菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程章节', '2000', '1', '/course/chapter', 'C', '0', 'course:chapter:view', '#', 'admin', sysdate(), '', null, '课程章节菜单');
SELECT @parentId := LAST_INSERT_ID();
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程章节查询', @parentId, '1',  '#',  'F', '0', 'course:chapter:list',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程章节新增', @parentId, '2',  '#',  'F', '0', 'course:chapter:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程章节修改', @parentId, '3',  '#',  'F', '0', 'course:chapter:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程章节删除', @parentId, '4',  '#',  'F', '0', 'course:chapter:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程章节导出', @parentId, '5',  '#',  'F', '0', 'course:chapter:export',       '#', 'admin', sysdate(), '', null, '');
-- signin 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到管理', '2003', '1', '/course/signin', 'C', '0', 'course:signin:view', '#', 'admin', sysdate(), '', null, '签到管理菜单');
SELECT @parentId := LAST_INSERT_ID();
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到管理查询', @parentId, '1',  '#',  'F', '0', 'course:signin:list',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到管理新增', @parentId, '2',  '#',  'F', '0', 'course:signin:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到管理修改', @parentId, '3',  '#',  'F', '0', 'course:signin:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到管理删除', @parentId, '4',  '#',  'F', '0', 'course:signin:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到管理导出', @parentId, '5',  '#',  'F', '0', 'course:signin:export',       '#', 'admin', sysdate(), '', null, '');
-- signrecord 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到记录', '2003', '1', '/course/signrecord', 'C', '0', 'course:signrecord:view', '#', 'admin', sysdate(), '', null, '学生签到记录菜单');
SELECT @parentId := LAST_INSERT_ID();
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到记录查询', @parentId, '1',  '#',  'F', '0', 'course:signrecord:list',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到记录新增', @parentId, '2',  '#',  'F', '0', 'course:signrecord:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到记录修改', @parentId, '3',  '#',  'F', '0', 'course:signrecord:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到记录删除', @parentId, '4',  '#',  'F', '0', 'course:signrecord:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('签到记录导出', @parentId, '5',  '#',  'F', '0', 'course:signrecord:export',       '#', 'admin', sysdate(), '', null, '');


--20201204 sys_dept 插入部门信息
	-- 先清理所有的数据，再重新写入
DELETE FROM `skuman`.`sys_dept`;
INSERT INTO `skuman`.`sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('100', '0', '0', '张家口学院', '0', '诸葛亮', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-11-22 19:29:07', 'admin', '2020-12-04 21:39:34');

INSERT INTO `skuman`.`sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('214', '100', '0,100', '数学与信息科学学院', '15', '诸葛亮', '15888888888', 'test@qq.com', '0', '0', 'admin', '2020-11-22 19:29:07', '', NULL);

-- 20201204 Dict 资源业务标识 资源类型 业务类型 话题创建者类型
INSERT INTO `skuman`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('101', '资源类型', 'sku_resource_type', '0', 'admin', '2020-12-04 22:32:48', '', NULL, '资源类型（1.视频、2.图片、3.音频、4.文件）');
INSERT INTO `skuman`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('102', '资源业务标识', 'sku_businz_tag', '0', 'admin', '2020-12-04 22:36:00', 'admin', '2020-12-04 22:39:18', '在章节中就是（1代表课件 2代表讲义 3代表视频4代表其他）');
INSERT INTO `skuman`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('103', '业务类型', 'sku_businz_type', '0', 'admin', '2020-12-04 22:40:03', '', NULL, '所属业务类型【枚举】1、课程；2、章节；3、作业');
INSERT INTO `skuman`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('104', '话题创建者类型', 'sku_topic_user_type', '0', 'admin', '2020-12-04 22:56:11', '', NULL, NULL);
-- 20201204  Dict data
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('107', '1', '视频', '1', 'sku_resource_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:34:09', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('108', '2', '图片', '2', 'sku_resource_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:34:29', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('109', '3', '音频', '3', 'sku_resource_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:34:49', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('110', '4', '文件', '4', 'sku_resource_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:35:02', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('111', '1', '课件', '1', 'sku_businz_tag', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:36:44', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('112', '2', '讲义', '2', 'sku_businz_tag', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:36:55', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('113', '3', '视频', '3', 'sku_businz_tag', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:37:09', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('114', '4', '其他', '4', 'sku_businz_tag', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:37:22', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('115', '1', '课程', '1', 'sku_businz_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:40:44', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('116', '2', '章节', '2', 'sku_businz_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:40:52', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('117', '3', '作业', '3', 'sku_businz_type', NULL, 'default', 'Y', '0', 'admin', '2020-12-04 22:41:01', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('118', '1', '题主', '1', 'sku_topic_user_type', NULL, 'primary', 'Y', '0', 'admin', '2020-12-04 22:57:06', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('119', '2', '教师', '2', 'sku_topic_user_type', NULL, 'success', 'Y', '0', 'admin', '2020-12-04 22:57:21', '', NULL, NULL);
INSERT INTO `skuman`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('120', '3', '学生', '3', 'sku_topic_user_type', NULL, 'info', 'Y', '0', 'admin', '2020-12-04 22:57:35', '', NULL, NULL);


-- 20201205 Dict 课程类型
INSERT INTO `skuman`.`sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (105, '课程类型', 'sku_course_type', '0', 'admin', '2020-12-03 21:01:17', '', NULL, '课程类型枚举');
INSERT INTO `skuman`.`sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (121, 1, '普通课程', '1', 'sku_course_type', '', 'primary', 'Y', '0', 'admin', '2020-12-03 21:02:19', 'admin', '2020-12-03 21:02:34', '');
INSERT INTO `skuman`.`sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (122, 2, '公开课程', '2', 'sku_course_type', NULL, 'warning', 'Y', '0', 'admin', '2020-12-03 21:03:03', '', NULL, NULL);

-- 20201205 生成代码的菜单 话题、资源
-- topic 话题菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生话题', '2006', '1', '/course/topic', 'C', '0', 'course:topic:view', '#', 'admin', sysdate(), '', null, '学生话题菜单');
SELECT @parentId := LAST_INSERT_ID();
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生话题查询', @parentId, '1',  '#',  'F', '0', 'course:topic:list',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生话题新增', @parentId, '2',  '#',  'F', '0', 'course:topic:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生话题修改', @parentId, '3',  '#',  'F', '0', 'course:topic:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生话题删除', @parentId, '4',  '#',  'F', '0', 'course:topic:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生话题导出', @parentId, '5',  '#',  'F', '0', 'course:topic:export',       '#', 'admin', sysdate(), '', null, '');
-- resources 资源菜单SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资源管理', '2000', '1', '/system/resources', 'C', '0', 'system:resources:view', '#', 'admin', sysdate(), '', null, '资源管理菜单');
SELECT @parentId := LAST_INSERT_ID();
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资源管理查询', @parentId, '1',  '#',  'F', '0', 'system:resources:list',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资源管理新增', @parentId, '2',  '#',  'F', '0', 'system:resources:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资源管理修改', @parentId, '3',  '#',  'F', '0', 'system:resources:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资源管理删除', @parentId, '4',  '#',  'F', '0', 'system:resources:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资源管理导出', @parentId, '5',  '#',  'F', '0', 'system:resources:export',       '#', 'admin', sysdate(), '', null, '');

-- 20201227 角色权限整理 ----------------------------------------------------------------------------------------
-- 删除角色菜单关联表班级、班级成员、作业相关信息
DELETE FROM sys_role_menu WHERE menu_id IN (
	SELECT menu_id FROM sys_menu
	WHERE perms LIKE 'course:group:%'
			OR perms LIKE 'course:groupuser:%'
			OR perms LIKE 'course:homework:%'
			OR perms LIKE 'course:hallocat:%'
			OR perms LIKE 'course:hanswer:%'
);

-- 删除菜单表班级、班级成员、作业相关信息
DELETE FROM sys_menu WHERE perms LIKE 'course:group:%'
		OR perms LIKE 'course:groupuser:%'
		OR perms LIKE 'course:homework:%'
		OR perms LIKE 'course:hallocat:%'
		OR perms LIKE 'course:hanswer:%'

-- 插入菜单表
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2044, '班级管理', 0, 2, '/course/group', 'menuItem', 'C', '0', 'course:group:view', 'fa fa-users', 'admin', '2020-12-05 16:01:11', 'admin', '2020-12-27 23:01:23', '班级菜单');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2045, '班级查询', 2044, 1, '#', '', 'F', '0', 'course:group:list', '#', 'admin', '2020-12-05 16:01:11', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2046, '班级新增', 2044, 2, '#', '', 'F', '0', 'course:group:add', '#', 'admin', '2020-12-05 16:01:11', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2047, '班级修改', 2044, 3, '#', '', 'F', '0', 'course:group:edit', '#', 'admin', '2020-12-05 16:01:11', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2048, '班级删除', 2044, 4, '#', '', 'F', '0', 'course:group:remove', '#', 'admin', '2020-12-05 16:01:11', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2049, '班级导出', 2044, 5, '#', '', 'F', '0', 'course:group:export', '#', 'admin', '2020-12-05 16:01:11', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2050, '班级学生', 2044, 1, '#', '', 'F', '0', 'course:groupuser:view', '#', 'admin', '2020-12-05 16:01:11', 'admin', '2020-12-25 21:57:44', '班级学生菜单');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2051, '班级学生查询', 2050, 1, '#', '', 'F', '0', 'course:groupuser:list', '#', 'admin', '2020-12-05 16:01:11', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2052, '班级学生新增', 2050, 2, '#', '', 'F', '0', 'course:groupuser:add', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2053, '班级学生修改', 2050, 3, '#', '', 'F', '0', 'course:groupuser:edit', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2054, '班级学生删除', 2050, 4, '#', '', 'F', '0', 'course:groupuser:remove', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2055, '班级学生导出', 2050, 5, '#', '', 'F', '0', 'course:groupuser:export', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2056, '作业管理', 0, 4, '/course/homework', 'menuItem', 'C', '0', 'course:homework:view', 'fa fa-file-code-o', 'admin', '2020-12-05 16:01:12', 'admin', '2020-12-27 23:02:41', '作业管理菜单');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2057, '作业查询', 2056, 1, '#', '', 'F', '0', 'course:homework:list', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2058, '作业新增', 2056, 2, '#', '', 'F', '0', 'course:homework:add', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2059, '作业修改', 2056, 3, '#', '', 'F', '0', 'course:homework:edit', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2060, '作业删除', 2056, 4, '#', '', 'F', '0', 'course:homework:remove', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2061, '作业导出', 2056, 5, '#', '', 'F', '0', 'course:homework:export', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2062, '作业分配', 2056, 0, '#', '', 'F', '0', 'course:hallocat:view', '#', 'admin', '2020-12-05 16:01:12', 'admin', '2020-12-27 22:43:56', '作业分配菜单');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2063, '作业分配查询', 2062, 1, '#', '', 'F', '0', 'course:hallocat:list', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2064, '作业分配新增', 2062, 2, '#', '', 'F', '0', 'course:hallocat:add', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2065, '作业分配修改', 2062, 3, '#', '', 'F', '0', 'course:hallocat:edit', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2066, '作业分配删除', 2062, 4, '#', '', 'F', '0', 'course:hallocat:remove', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2067, '作业分配导出', 2062, 5, '#', '', 'F', '0', 'course:hallocat:export', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2068, '我的作业', 0, 5, '/course/hanswer', 'menuItem', 'C', '0', 'course:hanswer:view', 'fa fa-file-code-o', 'admin', '2020-12-05 16:01:12', 'admin', '2020-12-27 23:02:54', '作业答案菜单');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2069, '我的作业答案查询', 2068, 1, '#', '', 'F', '0', 'course:hanswer:list', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2070, '我的作业答案新增', 2068, 2, '#', '', 'F', '0', 'course:hanswer:add', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2071, '我的作业答案修改', 2068, 3, '#', '', 'F', '0', 'course:hanswer:edit', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2073, '我的作业答案导出', 2068, 5, '#', '', 'F', '0', 'course:hanswer:export', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2075, '我的班级', 0, 3, '/course/group', 'menuItem', 'C', '0', 'course:group:view', 'fa fa-group', 'admin', '2020-12-26 15:32:37', 'admin', '2020-12-27 23:02:33', '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2076, '我的班级列表查询', 2075, 1, '#', '', 'F', '0', 'course:group:list', '#', 'admin', '2020-12-26 15:57:01', 'admin', '2020-12-27 22:47:15', '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2077, '我的班级成员查询', 2075, 2, '#', '', 'F', '0', 'course:groupuser:list', '#', 'admin', '2020-12-26 16:00:02', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2078, '作业答案管理', 2056, 0, '#', 'menuItem', 'F', '0', 'course:hanswer:view', 'fa fa-file-code-o', 'admin', '2020-12-05 16:01:12', 'admin', '2020-12-27 22:54:00', '作业答案菜单');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2079, '作业答案查询', 2078, 1, '#', '', 'F', '0', 'course:hanswer:list', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2080, '作业答案新增', 2078, 2, '#', '', 'F', '0', 'course:hanswer:add', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2081, '作业答案修改', 2078, 3, '#', '', 'F', '0', 'course:hanswer:edit', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2082, '作业答案删除', 2078, 4, '#', '', 'F', '0', 'course:hanswer:remove', '#', 'admin', '2020-12-05 16:01:12', '', NULL, '');

-- 插入角色权限关系
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2044);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2045);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2046);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2047);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2048);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2049);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2050);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2051);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2052);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2053);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2054);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2055);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2056);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2057);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2058);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2059);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2060);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2061);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2062);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2063);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2064);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2065);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2066);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2067);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2078);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2079);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2081);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (100, 2082);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2068);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2069);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2070);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2071);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2073);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2075);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2076);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2077);

-- 20201228 新增学生我的作业查看权限
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2083, '我的作业查看', 2068, 5, '#', 'menuItem', 'F', '0', 'course:homework:view', '#', 'admin', '2020-12-28 21:57:24', '', NULL, '');
INSERT INTO `skuman`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2084, '我的作业查询', 2068, 6, '#', 'menuItem', 'F', '0', 'course:homework:list', '#', 'admin', '2020-12-28 21:58:02', '', NULL, '');
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2083);
INSERT INTO `skuman`.`sys_role_menu`(`role_id`, `menu_id`) VALUES (101, 2084);