package com.yi.project.course.group.mapper;

import java.util.List;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.domain.HomeworkGroupQuery;

/**
 * 学生班级Mapper接口
 * 
 * @author yi
 * @date 2020-12-06
 */
public interface CourseGroupMapper {
    /**
     * 查询学生班级
     * 
     * @param groupId 学生班级ID
     * @return 学生班级
     */
    public CourseGroup selectCourseGroupById(Long groupId);

    /**
     * 查询学生班级列表
     * 
     * @param courseGroup 学生班级
     * @return 学生班级集合
     */
    public List<CourseGroup> selectCourseGroupList(CourseGroup courseGroup);

    /**
     * 新增学生班级
     * 
     * @param courseGroup 学生班级
     * @return 结果
     */
    public int insertCourseGroup(CourseGroup courseGroup);

    /**
     * 修改学生班级
     * 
     * @param courseGroup 学生班级
     * @return 结果
     */
    public int updateCourseGroup(CourseGroup courseGroup);

    /**
     * 删除学生班级
     * 
     * @param groupId 学生班级ID
     * @return 结果
     */
    public int deleteCourseGroupById(Long groupId);

    /**
     * 批量删除学生班级
     * 
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseGroupByIds(Long[] groupIds);

    /** 作业班级列表 */
    List<CourseGroup> selectGroupsForHomework(HomeworkGroupQuery query);

    /***
     * 根据传入的班级ids，获取对应的课程ids
     * @param groupIds 班级ids
     * @return  课程ids
     */
    public List<Long> getCouserIdsByGroupIds(List<Long> groupIds);
}
