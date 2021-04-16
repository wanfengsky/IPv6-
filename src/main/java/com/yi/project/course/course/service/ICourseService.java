package com.yi.project.course.course.service;

import java.util.List;
import com.yi.project.course.course.domain.Course;

/**
 * 课程Service接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface ICourseService {
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
     * 查询课程id列表
     *
     * @param course 课程
     * @return 课程id集合
     */
    public List<Long> selectCourseIdList(Course course);
    /**
     * 查询课程id列表
     *
     * @param userID 学生用户id
     * @return 课程id集合
     */
    public List<Long> selectCourseIdByStudentId(Long userID);

    /**
     * 查询课程id列表
     *
     * @param userID 教师用户id
     * @return 课程id集合
     */
    public List<Long> selectCourseIdByTeacherId(Long userID);
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
     * 批量删除课程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseByIds(String ids);

    /**
     * 删除课程信息
     * 
     * @param courseId 课程ID
     * @return 结果
     */
    public int deleteCourseById(Long courseId);
}
