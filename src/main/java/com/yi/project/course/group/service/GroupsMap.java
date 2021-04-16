package com.yi.project.course.group.service;

import com.yi.project.common.ModelsMap;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.mapper.CourseGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用以转换组ID-名称
 */
@Component("groupsMap")
public class GroupsMap extends ModelsMap<CourseGroup> {
	@Autowired
	private CourseGroupMapper courseGroupMapper;

	@Override
	protected List<CourseGroup> selectList() {
		return courseGroupMapper.selectCourseGroupList(new CourseGroup());
	}

	@Override
	protected CourseGroup selectById(Long groupId) {
		return courseGroupMapper.selectCourseGroupById(groupId);
	}

	@Override
	protected Long getId(CourseGroup model) {
		return model.getGroupId();
	}

	@Override
	protected String getName(CourseGroup model) {
		return model.getGroupName();
	}
}
