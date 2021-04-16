package com.yi.project.course.chapter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 课程章节对象 sku_course_chapter
 * 
 * @author yi
 * @date 2020-12-03
 */
public class CourseChapter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 章节编号，自增 */
    private Long chapterId;

    /** 章节名称 */
    @Excel(name = "章节名称")
    private String chapterName;

    /** 章节简介 */
    @Excel(name = "章节简介")
    private String chapterInfo;

    /** 课程编号，外键 */
    @Excel(name = "课程编号，外键")
    private Long courseId;

    /** 发布标志（2否1是，未发布的资源学生不可见） */
    @Excel(name = "发布标志", readConverterExp = "2=否1是，未发布的资源学生不可见")
    private String publish;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getChapterId() {
        return chapterId;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterName() {
        return chapterName;
    }
    public void setChapterInfo(String chapterInfo) {
        this.chapterInfo = chapterInfo;
    }

    public String getChapterInfo() {
        return chapterInfo;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("chapterId", getChapterId())
            .append("chapterName", getChapterName())
            .append("chapterInfo", getChapterInfo())
            .append("courseId", getCourseId())
            .append("publish", getPublish())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
