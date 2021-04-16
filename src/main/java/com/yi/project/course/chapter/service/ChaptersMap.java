package com.yi.project.course.chapter.service;

import com.yi.project.common.ModelsMap;
import com.yi.project.course.chapter.domain.CourseChapter;
import com.yi.project.course.chapter.mapper.CourseChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("chaptersMap")
public class ChaptersMap extends ModelsMap<CourseChapter> {
	@Autowired
	private CourseChapterMapper courseChapterMapper;

	@Override
	protected List<CourseChapter> selectList() {
		return courseChapterMapper.selectCourseChapterList(new CourseChapter());
	}

	@Override
	protected CourseChapter selectById(Long id) {
		return courseChapterMapper.selectCourseChapterById(id);
	}

	@Override
	protected Long getId(CourseChapter model) {
		return model.getChapterId();
	}

	@Override
	protected String getName(CourseChapter model) {
		return model.getChapterName();
	}
}
