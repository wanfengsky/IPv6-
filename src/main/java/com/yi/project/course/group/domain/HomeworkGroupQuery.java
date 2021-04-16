package com.yi.project.course.group.domain;

import com.yi.framework.web.domain.BaseEntity;

public class HomeworkGroupQuery extends BaseEntity {
	/** 作业编号 */
	private Long homeworkId;
	/** 组编号 */
	private Long groupId;
	/** 学生编号 */
	private Long userId;
	/** 是否仅查询有效的 */
	private boolean valid;

	public Long getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Long homeworkId) {
		this.homeworkId = homeworkId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
