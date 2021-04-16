package com.yi.common.utils.security;

import com.yi.common.constant.UserConstants;
import com.yi.project.system.role.domain.Role;
import com.yi.project.system.user.domain.UserRoleEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import com.yi.common.utils.StringUtils;
import com.yi.common.utils.bean.BeanUtils;
import com.yi.project.system.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * shiro 工具类
 */
@Component("shiro")
public class ShiroUtils {
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void logout() {
		getSubject().logout();
	}

	public static User getSysUser() {
		User user = null;
		Object obj = getSubject().getPrincipal();
		if (StringUtils.isNotNull(obj)) {
			user = new User();
			BeanUtils.copyBeanProp(user, obj);
		}
		return user;
	}

	public static void setSysUser(User user) {
		Subject subject = getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);
	}

	/** 当前登录用户是否为管理员角色 */
	public static boolean isAdmin() {
		return UserRoleEnum.ADMIN.is(getSysUser()) || UserRoleEnum.MANAGER.is(getSysUser());
	}

	public static boolean isTeacher() {
		return UserRoleEnum.TEACHER.is(getSysUser());
	}

	public static boolean isStudent() {
		return UserRoleEnum.STUDENT.is(getSysUser());
	}

	/**
	 * 获取当前用户ID，用来进行SQL查询
	 *
	 * @return 当前用户是Admin角色的时候返回空，可以查询所有的信息
	 */
	public static String currUserIdForSQL() {
		return isAdmin() ? null : getCurrUserId();
	}

	/**
	 * 获取当前用户ID，用来进行SQL查询
	 *
	 * @return 当前用户是Admin角色的时候返回空，可以查询所有的信息
	 */
	public static Long currUserId() {
		return isAdmin() ? null : getUserId();
	}

	public static Long getUserId() {
		return getSysUser().getUserId().longValue();
	}

	public static String getCurrUserId() {
		return String.valueOf(getSysUser().getUserId());
	}

	public static String getLoginName() {
		return getSysUser().getLoginName();
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

	public static String getSessionId() {
		return String.valueOf(getSubject().getSession().getId());
	}
}
