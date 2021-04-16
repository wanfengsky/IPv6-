package com.yi.project.course.group.domain;

import java.util.List;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yi.common.utils.DateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 学生班级对象 sku_course_group
 * 
 * @author yi
 * @date 2020-12-06
 */
public class CourseGroup extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 班级编号，自增 */
    private Long groupId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String groupName;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;
    @Excel(name = "课程名称")
    private String courseName;

    /** 班级生效时间 */
    @Excel(name = "班级生效时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    /** 班级失效时间 */
    @Excel(name = "班级失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    /** 班级内人数 */
    private int userCnt;

    /** 是否有效 */
    public boolean isValid() {
        Date now = DateUtils.getNowDate();
        return (startTime == null || startTime.before(now)) && (endTime == null || endTime.after(now));
    }

    /** 查询使用：学生Id */
    private Long studentId;

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public int getUserCnt() {
        return userCnt;
    }

    public void setUserCnt(int userCnt) {
        this.userCnt = userCnt;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("courseId", getCourseId())
            .append("courseName", courseName)
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .append("userCnt", userCnt)
            .append("valid", isValid())
            .toString();
    }
}
