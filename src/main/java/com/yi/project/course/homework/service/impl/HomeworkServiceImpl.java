package com.yi.project.course.homework.service.impl;

import java.util.List;

import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.project.course.chapter.service.ChaptersMap;
import com.yi.project.course.course.service.CoursesMap;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.service.GroupsMap;
import com.yi.project.course.homework.domain.HomeworkAllocat;
import com.yi.project.course.group.domain.HomeworkGroupQuery;
import com.yi.project.course.homework.domain.MyHomework;
import com.yi.project.system.user.service.UsersMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.yi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.yi.project.course.homework.mapper.HomeworkMapper;
import com.yi.project.course.homework.domain.Homework;
import com.yi.project.course.homework.service.IHomeworkService;
import com.yi.common.utils.text.Convert;

/**
 * 作业管理Service业务层处理
 *
 * @author yi
 * @date 2020-12-05
 */
@Service
public class HomeworkServiceImpl implements IHomeworkService {
	@Autowired
	private HomeworkMapper homeworkMapper;
	@Autowired
	private GroupsMap groupsMap;
	@Autowired
	private CoursesMap coursesMap;
	@Autowired
	private ChaptersMap chaptersMap;
	@Autowired
	private UsersMap usersMap;

	/**
	 * 查询作业管理
	 *
	 * @param homeworkId 作业管理ID
	 * @return 作业管理
	 */
	@Override
	public Homework selectHomeworkById(Long homeworkId) {
		return homeworkMapper.selectHomeworkById(homeworkId);
	}

	/**
	 * 查询作业管理列表
	 *
	 * @param homework 作业管理
	 * @return 作业管理
	 */
	@Override
	public List<Homework> selectHomeworkList(Homework homework) {
		List<Homework> homeworks = homeworkMapper.selectHomeworkList(homework);
		if (CollectionUtils.isNotEmpty(homeworks)) {
			homeworks.forEach(e -> {
				e.setCourseName(coursesMap.getName(e.getCourseId()));
				e.setChapterName(chaptersMap.getName(e.getChapterId()));
				e.setCreateBy(usersMap.getName(Long.parseLong(e.getCreateBy())));
			});
		}
		return homeworks;
	}

	/**
	 * 查询登录人的作业列表
	 *
	 * @param homework 作业管理
	 * @return 作业管理
	 */
	@Override
	public List<MyHomework> selectMyHomeworks(MyHomework homework) {
		homework.setUserId(ShiroUtils.getUserId());
		List<MyHomework> homeworks = homeworkMapper.selectMyHomeworks(homework);
		if (CollectionUtils.isNotEmpty(homeworks)) {
			homeworks.forEach(e -> {
				e.setGroupName(groupsMap.getName(e.getGroupId()));
				e.setCourseName(coursesMap.getName(e.getCourseId()));
				e.setChapterName(chaptersMap.getName(e.getChapterId()));
				e.setCreateBy(usersMap.getName(Long.parseLong(e.getCreateBy())));
			});
		}
		return homeworks;
	}

	/**
	 * 新增作业管理
	 *
	 * @param homework 作业管理
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertHomework(Homework homework) {
		homework.setCreateBy(ShiroUtils.getCurrUserId());
		homework.setCreateTime(DateUtils.getNowDate());
		int rows = homeworkMapper.insertHomework(homework);
		insertHomeworkAllocat(homework);
		return rows;
	}

	/**
	 * 修改作业管理
	 *
	 * @param homework 作业管理
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateHomework(Homework homework) {
		homeworkMapper.deleteHomeworkAllocatByHomeworkId(homework.getHomeworkId());
		insertHomeworkAllocat(homework);
		return homeworkMapper.updateHomework(homework);
	}

	/**
	 * 删除作业管理对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteHomeworkByIds(String ids) {
		homeworkMapper.deleteHomeworkAllocatByHomeworkIds(Convert.toStrArray(ids));
		return homeworkMapper.deleteHomeworkByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除作业管理信息
	 *
	 * @param homeworkId 作业管理ID
	 * @return 结果
	 */
	@Override
	public int deleteHomeworkById(Long homeworkId) {
		homeworkMapper.deleteHomeworkAllocatByHomeworkId(homeworkId);
		return homeworkMapper.deleteHomeworkById(homeworkId);
	}

	/**
	 * 新增作业分配信息
	 *
	 * @param homework 作业管理对象
	 */
	public void insertHomeworkAllocat(Homework homework) {
		List<HomeworkAllocat> commntList = homework.getHomeworkAllocatList();
		Long homeworkId = homework.getHomeworkId();
		if (CollectionUtils.isNotEmpty(commntList)) {
			List<HomeworkAllocat> list = new ArrayList<>();
			commntList.stream().filter(e -> e.getSummitCount() == null || e.getSummitCount() == 0).forEach(e -> {
				e.setHomeworkId(homeworkId);
				e.setCreateBy(ShiroUtils.getCurrUserId());
				e.setCreateTime(DateUtils.getNowDate());
				e.setSummitCount(0L);
				list.add(e);
			});
			if(!list.isEmpty()) {
				homeworkMapper.batchHomeworkAllocat(list);
			}
		}
	}
}
