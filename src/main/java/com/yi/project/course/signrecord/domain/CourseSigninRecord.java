package com.yi.project.course.signrecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 学生签到记录对象 sku_course_signin_record
 * 
 * @author yi
 * @date 2020-12-03
 */
public class CourseSigninRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 签到记录编号 */
    @Excel(name = "签到记录编号")
    private Long recordId;

    /** 签到表编号 */
    @Excel(name = "签到表编号")
    private Long signinId;

    /** 学生 */
    @Excel(name = "学生")
    private Long userId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getRecordId() {
        return recordId;
    }
    public void setSigninId(Long signinId) {
        this.signinId = signinId;
    }

    public Long getSigninId() {
        return signinId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("signinId", getSigninId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
