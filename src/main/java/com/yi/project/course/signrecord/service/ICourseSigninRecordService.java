package com.yi.project.course.signrecord.service;

import java.util.Date;
import java.util.List;

import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.signin.domain.CourseSigninVo;
import com.yi.project.course.signrecord.domain.CourseSigninRecord;
import com.yi.project.course.signrecord.domain.CourseSigninRecordVo;

/**
 * 学生签到记录Service接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface ICourseSigninRecordService {
    /**
     * 为签到赋值 用户名、课程名、签到表名
     * @param courseSigninRecordVo 签到对象
     */
    public void initVoParam(CourseSigninRecordVo courseSigninRecordVo);

    /**
     * 查询学生签到记录
     * 
     * @param recordId 学生签到记录ID
     * @return 学生签到记录
     */
    public CourseSigninRecordVo selectCourseSigninRecordById(Long recordId);

    /**
     * 查询学生签到记录列表
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 学生签到记录集合
     */
    public List<CourseSigninRecordVo> selectCourseSigninRecordList(CourseSigninRecord courseSigninRecord,Long courseId);

    /**
     * 新增学生签到记录
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 结果
     */
    public int insertCourseSigninRecord(CourseSigninRecord courseSigninRecord);
    /**
     * 新增学生签到记录
     *
     * @return 结果
     */
    public String insertCourseSigninRecord();

    /**
     * 修改学生签到记录
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 结果
     */
    public int updateCourseSigninRecord(CourseSigninRecord courseSigninRecord);

    /**
     * 批量删除学生签到记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseSigninRecordByIds(String ids);

    /**
     * 删除学生签到记录信息
     * 
     * @param courseId 学生签到记录ID
     * @return 结果
     */
    public int deleteCourseSigninRecordById(Long courseId);


    public List<CourseSigninVo> getSigninInfo();

    /**
     *
     * @param signinId 签到表id
     * @param userID 用户id
     * @return 返回签到id
     */
    public Long isSignIn(Long signinId,Long userID);
}
