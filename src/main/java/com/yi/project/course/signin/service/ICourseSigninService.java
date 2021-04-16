package com.yi.project.course.signin.service;

import java.util.Date;
import java.util.List;
import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.signin.domain.CourseSigninVo;

/**
 * 签到管理Service接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface ICourseSigninService {

    /**
     * 为签到赋值 用户名和课程名
     * @param courseSigninVo 签到对象
     */
    public void initVoParam(CourseSigninVo courseSigninVo);

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
     * 批量删除签到管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseSigninByIds(String ids);

    /**
     * 删除签到管理信息
     * 
     * @param signinId 签到管理ID
     * @return 结果
     */
    public int deleteCourseSigninById(Long signinId);

    /**
     * 根据课程id查询签到管理列表
     *
     * @param courseIds 课程ids
     * @return 签到管理集合
     */
    public List<CourseSigninVo> selectSigninByCourseIds(List<Long> courseIds);

    /**
     * 根据课程id
     * 课程签到表中的可在当前时段进行签到的  签到
     */
    public List<CourseSigninVo> getSigninByCourseId(Long courseId,Boolean signIn);

    /**
     * 获取学生当前最需要的签到表，根据签到表结束时间判断
     * @return
     */
    public List<CourseSigninVo> getNowSignInByStudent();
}
