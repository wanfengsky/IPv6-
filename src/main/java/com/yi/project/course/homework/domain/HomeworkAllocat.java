package com.yi.project.course.homework.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 作业分配对象 sku_homework_allocat
 * 
 * @author yi
 * @date 2020-12-12
 */
public class HomeworkAllocat extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 作业批次ID */
    private Long allocatId;

    /** 作业编号 */
    @Excel(name = "作业编号")
    private Long homeworkId;

    /** 分配组 */
    @Excel(name = "分配组")
    private Long groupId;

    /** 分发人数 */
    @Excel(name = "分发人数")
    private Long receiveCount;

    /** 提交总数 */
    @Excel(name = "作业提交总人数")
    private Long summitCount;

    /** 上交新增数量 */
    private Long summitAdd;

    public void setAllocatId(Long allocatId) {
        this.allocatId = allocatId;
    }

    public Long getAllocatId() {
        return allocatId;
    }
    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }
    public void setReceiveCount(Long receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Long getReceiveCount() {
        return receiveCount;
    }
    public void setSummitCount(Long summitCount) {
        this.summitCount = summitCount;
    }

    public Long getSummitCount() {
        return summitCount;
    }

    public Long getSummitAdd() {
        return summitAdd;
    }

    public void setSummitAdd(Long summitAdd) {
        this.summitAdd = summitAdd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("allocatId", getAllocatId())
            .append("homeworkId", getHomeworkId())
            .append("groupId", getGroupId())
            .append("receiveCount", getReceiveCount())
            .append("summitCount", getSummitCount())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("summitAdd", summitAdd)
            .toString();
    }
}
