package com.yi.project.course.course.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 课程对象 sku_course
 * 
 * @author yi
 * @date 2020-12-03
 */
public class Course extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程类型 */
    @Excel(name = "课程类型")
    private String courseType;

    /** 课程简介 */
    @Excel(name = "课程简介")
    private String courseInfo;

    /** 课程图片 */
    @Excel(name = "课程图片")
    private String courseImage;

    /** 课程视频 */
    @Excel(name = "课程视频")
    private String courseVideo;

    /** 任课教师 */
    @Excel(name = "任课教师")
    private Long userId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 查询使用：学生Id */
    private Long studentId;

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseType() {
        return courseType;
    }
    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getCourseInfo() {
        return courseInfo;
    }
    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseImage() {
        return courseImage;
    }
    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    public String getCourseVideo() {
        return courseVideo;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("courseType", getCourseType())
            .append("courseInfo", getCourseInfo())
            .append("courseImage", getCourseImage())
            .append("courseVideo", getCourseVideo())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
