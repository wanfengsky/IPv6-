package com.yi.project.system.user.service;

import com.yi.project.common.ModelsMap;
import com.yi.project.system.user.domain.User;
import com.yi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用以转换用户ID-名称
 */
@Component("usersMap")
public class UsersMap extends ModelsMap<User> {
	@Autowired
	private UserMapper userMapperInner;

	@Override
	protected List<User> selectList() {
		return userMapperInner.selectAllUserInfos();
	}

	@Override
	protected User selectById(Long userId) {
		return userMapperInner.selectUserById(userId);
	}

	@Override
	protected Long getId(User model) {
		return model.getUserId();
	}

	@Override
	protected String getName(User model) {
		return model.getUserName();
	}
}
