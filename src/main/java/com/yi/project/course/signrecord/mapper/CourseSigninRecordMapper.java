package com.yi.project.course.signrecord.mapper;

import java.util.List;
import java.util.Map;

import com.yi.project.course.signrecord.domain.CourseSigninRecord;
import com.yi.project.course.signrecord.domain.CourseSigninRecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * 学生签到记录Mapper接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface CourseSigninRecordMapper {
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
     * @param params 学生签到记录
     * @return 学生签到记录集合
     */
    public List<CourseSigninRecordVo> selectCourseSigninRecordList(@Param("courseSigninRecord") CourseSigninRecord courseSigninRecord,@Param("signinIds")List<Long> signinIds);

    /**
     * 新增学生签到记录
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 结果
     */
    public int insertCourseSigninRecord(CourseSigninRecord courseSigninRecord);

    /**
     * 修改学生签到记录
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 结果
     */
    public int updateCourseSigninRecord(CourseSigninRecord courseSigninRecord);

    /**
     * 删除学生签到记录
     * 
     * @param recordId 学生签到记录ID
     * @return 结果
     */
    public int deleteCourseSigninRecordById(Long recordId);

    /**
     * 批量删除学生签到记录
     * 
     * @param recordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseSigninRecordByIds(String[] recordIds);

    /**
     * 批量删除学生签到记录,把删除标记转为'2'
     *
     * @param recordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlagByIds(String[] recordIds);

    /**
     *
     * @param signinId 签到表id
     * @param userID 用户id
     * @return 返回签到id
     */
    public Long isSignIn(@Param("signinId")Long signinId,@Param("userID")Long userID);
}
