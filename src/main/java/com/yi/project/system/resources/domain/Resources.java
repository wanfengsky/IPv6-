package com.yi.project.system.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 资源管理对象 sku_resource
 * 
 * @author yi
 * @date 2020-12-05
 */
public class Resources extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 资源编号 */
    private Long resourceId;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String resourceName;

    /** 资源简介 */
    @Excel(name = "资源简介")
    private String resourceInfo;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String resourceType;

    /** 资源路径 */
    @Excel(name = "资源路径")
    private String resourcePath;

    /** 业务ID */
    @Excel(name = "业务ID")
    private Long businzId;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businzType;

    /** 业务标识 */
    @Excel(name = "业务标识")
    private String businzTag;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String delFlag;

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return resourceId;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
    public void setResourceInfo(String resourceInfo) {
        this.resourceInfo = resourceInfo;
    }

    public String getResourceInfo() {
        return resourceInfo;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
    public void setBusinzId(Long businzId) {
        this.businzId = businzId;
    }

    public Long getBusinzId() {
        return businzId;
    }
    public void setBusinzType(String businzType) {
        this.businzType = businzType;
    }

    public String getBusinzType() {
        return businzType;
    }
    public void setBusinzTag(String businzTag) {
        this.businzTag = businzTag;
    }

    public String getBusinzTag() {
        return businzTag;
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
            .append("resourceId", getResourceId())
            .append("resourceName", getResourceName())
            .append("resourceInfo", getResourceInfo())
            .append("resourceType", getResourceType())
            .append("resourcePath", getResourcePath())
            .append("businzId", getBusinzId())
            .append("businzType", getBusinzType())
            .append("businzTag", getBusinzTag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
