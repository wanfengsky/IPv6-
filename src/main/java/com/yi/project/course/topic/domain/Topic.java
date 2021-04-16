package com.yi.project.course.topic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 学生话题对象 sku_topic
 * 
 * @author yi
 * @date 2020-12-05
 */
public class Topic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 话题编号 */
    private Long topicId;

    /** 父问题编号 */
    @Excel(name = "父问题编号")
    private Long topicPid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;

    /** 所属章节 */
    @Excel(name = "所属章节")
    private Long chapterId;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long userId;

    /** 创建者类型（1、话题主；2、教师；3、学生） */
    @Excel(name = "创建者类型", readConverterExp = "1=、话题主；2、教师；3、学生")
    private String userType;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getTopicId() {
        return topicId;
    }
    public void setTopicPid(Long topicPid) {
        this.topicPid = topicPid;
    }

    public Long getTopicPid() {
        return topicPid;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
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
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("topicId", getTopicId())
            .append("topicPid", getTopicPid())
            .append("title", getTitle())
            .append("content", getContent())
            .append("courseId", getCourseId())
            .append("chapterId", getChapterId())
            .append("userId", getUserId())
            .append("userType", getUserType())
            .append("createTime", getCreateTime())
            .toString();
    }
}
