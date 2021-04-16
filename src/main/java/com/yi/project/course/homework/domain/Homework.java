package com.yi.project.course.homework.domain;

import java.util.List;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 作业管理对象 sku_homework
 * 
 * @author yi
 * @date 2020-12-05
 */
public class Homework extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 作业编号 */
    private Long homeworkId;
    /** 作业名 */
    @Excel(name = "作业名")
    private String homeworkName;
    /** 章节编号 */
    @Excel(name = "章节编号")
    private Long chapterId;
    @Excel(name = "章节名称")
    private String chapterName;
    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;
    @Excel(name = "课程名称")
    private String courseName;
    /** 截止时间 */
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;
    /** 作业内容 */
    private String content;
    /** 发布标志 */
    @Excel(name = "发布标志")
    private String publish;
    /** 删除标志 */
    private String delFlag;
    /** 作业分配信息 */
    private List<HomeworkAllocat> homeworkAllocatList;

    @Excel(name = "总分发人数")
    private Long receiveCount;
    @Excel(name = "总提交总人数")
    private Long summitCount;
    @Excel(name = "最后提交人")
    private String lastUpdate;
    @Excel(name = "最后提交时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }
    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getHomeworkName() {
        return homeworkName;
    }
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getChapterId() {
        return chapterId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getPublish() {
        return publish;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public List<HomeworkAllocat> getHomeworkAllocatList() {
        return homeworkAllocatList;
    }

    public void setHomeworkAllocatList(List<HomeworkAllocat> homeworkAllocatList) {
        this.homeworkAllocatList = homeworkAllocatList;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Long receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Long getSummitCount() {
        return summitCount;
    }

    public void setSummitCount(Long summitCount) {
        this.summitCount = summitCount;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("homeworkId", getHomeworkId())
            .append("homeworkName", getHomeworkName())
            .append("chapterId", getChapterId())
            .append("chapterName", chapterName)
            .append("courseId", courseId)
            .append("courseName", courseName)
            .append("deadline", getDeadline())
            .append("content", getContent())
            .append("publish", getPublish())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .append("receiveCount", receiveCount)
            .append("summitCount", summitCount)
            .append("lastUpdate", lastUpdate)
            .append("lastUpdateTime", lastUpdateTime)
            .append("homeworkAllocatList", getHomeworkAllocatList())
            .toString();
    }
}
