package com.yi.project.course.homework.service;

import java.util.List;

import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.homework.domain.Homework;
import com.yi.project.course.group.domain.HomeworkGroupQuery;
import com.yi.project.course.homework.domain.MyHomework;

/**
 * 作业管理Service接口
 *
 * @author yi
 * @date 2020-12-05
 */
public interface IHomeworkService {
	/**
	 * 查询作业管理
	 *
	 * @param homeworkId 作业管理ID
	 * @return 作业管理
	 */
	public Homework selectHomeworkById(Long homeworkId);

	/**
	 * 查询作业管理列表
	 *
	 * @param homework 作业管理
	 * @return 作业管理集合
	 */
	public List<Homework> selectHomeworkList(Homework homework);

	/**
	 * 查询登录人的作业列表
	 *
	 * @param homework 作业管理
	 * @return 作业管理
	 */
	List<MyHomework> selectMyHomeworks(MyHomework homework);

	/**
	 * 新增作业管理
	 *
	 * @param homework 作业管理
	 * @return 结果
	 */
	public int insertHomework(Homework homework);

	/**
	 * 修改作业管理
	 *
	 * @param homework 作业管理
	 * @return 结果
	 */
	public int updateHomework(Homework homework);

	/**
	 * 批量删除作业管理
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteHomeworkByIds(String ids);

	/**
	 * 删除作业管理信息
	 *
	 * @param homeworkId 作业管理ID
	 * @return 结果
	 */
	public int deleteHomeworkById(Long homeworkId);

}
