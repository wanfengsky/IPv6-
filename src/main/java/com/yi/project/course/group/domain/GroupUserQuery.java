package com.yi.project.course.group.domain;

import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 班级学生查询对象
 *
 * @author yi
 * @date 2020-12-05
 */
public class GroupUserQuery extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 班级学生关联ID */
	private Long groupUserId;
	/** 班级编号 */
	private Long groupId;
	/** 班级编号 */
	private Long deptId;
	/** 部门名称 */
	private String deptName;
	/** 学生id */
	private Long userId;
	/** 用户名称 */

	private String userName;
	private String loginName;
	/** 作为查询条件，是否不在班级内 */
	private boolean notIn;

	public GroupUserQuery() {
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public GroupUserQuery(Long groupId, Long userId) {
		this.groupId = groupId;
		this.userId = userId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setGroupUserId(Long groupUserId) {
		this.groupUserId = groupUserId;
	}

	public Long getGroupUserId() {
		return groupUserId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public boolean isNotIn() {
		return notIn;
	}

	public void setNotIn(boolean notIn) {
		this.notIn = notIn;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("groupUserId", getGroupUserId())
				.append("groupId", getGroupId())
				.append("userId", getUserId())
				.append("notIn", isNotIn())
				.append("createBy", getCreateBy())
				.append("createTime", getCreateTime())
				.toString();
	}
}
