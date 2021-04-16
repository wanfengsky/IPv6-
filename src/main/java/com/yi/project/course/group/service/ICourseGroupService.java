package com.yi.project.course.group.service;

import java.util.List;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.domain.HomeworkGroupQuery;

/**
 * 学生班级Service接口
 * 
 * @author yi
 * @date 2020-12-06
 */
public interface ICourseGroupService {
    /**
     * 查询学生班级
     * 
     * @param groupId 学生班级ID
     * @return 学生班级
     */
    CourseGroup selectCourseGroupById(Long groupId);

    /**
     * 查询学生班级列表
     * 
     * @param courseGroup 学生班级
     * @return 学生班级集合
     */
    List<CourseGroup> selectCourseGroupList(CourseGroup courseGroup);

	String groupQR(Long groupId) throws Exception;

	/**
     * 新增学生班级
     * 
     * @param courseGroup 学生班级
     * @return 结果
     */
    int insertCourseGroup(CourseGroup courseGroup);

    /**
     * 修改学生班级
     * 
     * @param courseGroup 学生班级
     * @return 结果
     */
    int updateCourseGroup(CourseGroup courseGroup);

    /**
     * 批量删除学生班级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCourseGroupByIds(String ids);

    /**
     * 删除学生班级信息
     * 
     * @param groupId 学生班级ID
     * @return 结果
     */
    int deleteCourseGroupById(Long groupId);

	List<CourseGroup> groupsDict(Long courseId);

    List<CourseGroup> homeworkGroups(Long homeworkId);

    /**
     * 作业班级列表
     *
     * @param queryVo
     * @return
     */
    List<CourseGroup> homeworkGroups(HomeworkGroupQuery queryVo);


    List<CourseGroup> groupsDict(String courseId);

    /***
     * 根据传入的班级ids，获取对应的课程ids
     * @param groupIds 班级ids
     * @return  课程ids
     */
	List<Long> getCouserIdsByGroupIds(List<Long> groupIds);
}
