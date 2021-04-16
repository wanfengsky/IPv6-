package com.yi.project.system.user.domain;

import com.yi.project.system.role.domain.Role;

import java.util.List;

/**
 * 用户角色枚举
 */
public enum UserRoleEnum {
	ADMIN(1L, "超级管理员"),
	NORMAL(2L, "普通角色"),
	TEACHER(100L, "教师"),
	STUDENT(101L, "学生"),
	MANAGER(102L, "管理员");

	private final Long roleId;
	private final String info;

	UserRoleEnum(Long roleId, String info) {
		this.roleId = roleId;
		this.info = info;
	}

	public boolean is(Long roleId) {
		return this.roleId.equals(roleId);
	}

	public boolean is(User user) {
		if (this.equals(ADMIN) && user.isAdmin()) {
			return true;
		}
		List<Role> roles = user.getRoles();
		if (roles != null) {
			for (Role role : roles) {
				if (this.roleId.equals(role.getRoleId())) {
					return true;
				}
			}
		}
		return false;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getInfo() {
		return info;
	}
}
