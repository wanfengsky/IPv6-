package com.yi.project.course.signin.mapper;

import java.util.Date;
import java.util.List;
import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.signin.domain.CourseSigninVo;
import org.apache.ibatis.annotations.Param;

/**
 * 签到管理Mapper接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface CourseSigninMapper {
    /**
     * 查询签到管理
     * 
     * @param signinId 签到管理ID
     * @return 签到管理
     */
    public CourseSigninVo selectCourseSigninById(Long signinId);

    /**
     * 查询签到管理列表
     * 
     * @param courseSignin 签到管理
     * @return 签到管理集合
     */
    public List<CourseSigninVo> selectCourseSigninList(CourseSignin courseSignin);

    /**
     * 根据课程ids查询签到管理列表
     *
     * @param courseIds 课程ids
     * @return 签到管理集合
     */
    public List<CourseSigninVo> selectSigninByCourseIds(List<Long> courseIds);

    /**
     * 根据课程id查询签到管理列表
     * 获取当前时间段，对时间进行判断
     * @param courseId 课程id
     * @param  currentTime 当前时间
     * @return 签到管理集合
     */
    public List<CourseSigninVo> selectSigninByCourseId(@Param("courseId")Long courseId, @Param("currentTime")Date currentTime);

    /**
     * 新增签到管理
     * 
     * @param courseSignin 签到管理
     * @return 结果
     */
    public int insertCourseSignin(CourseSignin courseSignin);

    /**
     * 修改签到管理
     * 
     * @param courseSignin 签到管理
     * @return 结果
     */
    public int updateCourseSignin(CourseSignin courseSignin);

    /**
     * 删除签到管理
     * 
     * @param signinId 签到管理ID
     * @return 结果
     */
    public int deleteCourseSigninById(Long signinId);

    /**
     * 批量删除签到管理
     * 
     * @param signinIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseSigninByIds(String[] signinIds);

    /**
     * 批量删除签到管理,把删除标记转为'2'
     *
     * @param signinIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlagByIds(String[] signinIds);
}
