package com.yi.project.course.signin.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 签到管理对象 sku_course_signin
 * 
 * @author yi
 * @date 2020-12-03
 */
public class CourseSignin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 签到表编号 */
    @Excel(name = "签到表编号")
    private Long signinId;

    /** 签到表名 */
    @Excel(name = "签到表名")
    private String signinName;

    /** 课程 */
    @Excel(name = "课程")
    private Long courseId;

    /** 章节 */
    @Excel(name = "章节")
    private Long chapterId;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setSigninId(Long signinId) {
        this.signinId = signinId;
    }

    public Long getSigninId() {
        return signinId;
    }
    public void setSigninName(String signinName) {
        this.signinName = signinName;
    }

    public String getSigninName() {
        return signinName;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getChapterId() {
        return chapterId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("signinId", getSigninId())
            .append("signinName", getSigninName())
            .append("courseId", getCourseId())
            .append("chapterId", getChapterId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
