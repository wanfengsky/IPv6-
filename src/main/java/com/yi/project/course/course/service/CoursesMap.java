package com.yi.project.course.course.service;

import com.yi.project.common.ModelsMap;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("coursesMap")
public class CoursesMap extends ModelsMap<Course> {
	@Autowired
	private CourseMapper courseMapper;

	@Override
	protected List<Course> selectList() {
		return courseMapper.selectCourseList(new Course());
	}

	@Override
	protected Course selectById(Long id) {
		return courseMapper.selectCourseById(id);
	}

	@Override
	protected Long getId(Course model) {
		return model.getCourseId();
	}

	@Override
	protected String getName(Course model) {
		return model.getCourseName();
	}
}
