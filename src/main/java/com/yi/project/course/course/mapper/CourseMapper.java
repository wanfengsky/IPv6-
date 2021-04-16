package com.yi.project.course.course.mapper;

import java.util.List;
import com.yi.project.course.course.domain.Course;

/**
 * 课程Mapper接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface CourseMapper {
    /**
     * 查询课程
     * 
     * @param courseId 课程ID
     * @return 课程
     */
    public Course selectCourseById(Long courseId);

    /**
     * 查询课程列表
     * 
     * @param course 课程
     * @return 课程集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增课程
     * 
     * @param course 课程
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程
     * 
     * @param course 课程
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 删除课程
     * 
     * @param courseId 课程ID
     * @return 结果
     */
    public int deleteCourseById(Long courseId);

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseByIds(Long[] courseIds);

    public int deleteFlagByIds(Long[] courseIds);
    /**
     * 查询课程id列表
     *
     * @param course 课程
     * @return 课程id集合
     */
    public List<Long> selectCourseIdList(Course course);
    /**
     * 查询课程id列表
     *
     * @param userID 教师用户id
     * @return 课程id集合
     */
    public List<Long> selectCourseIdByTeacherId(Long userID);
}
