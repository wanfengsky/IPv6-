package com.yi.project.course.homework.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;

/**
 * 作业提交对象 sku_homework_answer
 *
 * @author yi
 * @date 2020-12-05
 */
public class HomeworkAnswer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 答案编号 */
	private Long answerId;

	/** 作业编号 */
	@Excel(name = "作业编号")
	private Long homeworkId;
	@Excel
	private String homeworkName;

	/** 组编号 */
	@Excel(name = "组编号")
	private Long groupId;
	/** 组名称 */
	@Excel(name = "组名称")
	private String groupName;

	/** 学生编号 */
	@Excel(name = "学生编号")
	private Long userId;
	/** 学生名字 */
	@Excel(name = "学生名字")
	private String userName;

	/** 提交时间 */
	@Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date submitTime;

	/** 作业内容 */
	@Excel(name = "作业内容")
	private String answerContent;

	/** 作业评语 */
	@Excel(name = "作业评语")
	private String comment;

	/** 作业分数 */
	@Excel(name = "作业分数")
	private Long score;

	/** 优秀 */
	@Excel(name = "优秀")
	private String excellent;

	/** 删除标志 */
	private String delFlag;

	/** 查询使用，仅查询当前用户的作业 */
	private Long owner;

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getAnswerId() {
		return answerId;
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

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getScore() {
		return score;
	}

	public void setExcellent(String excellent) {
		this.excellent = excellent;
	}

	public String getExcellent() {
		return excellent;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("answerId", getAnswerId())
				.append("homeworkId", getHomeworkId())
				.append("homeworkName", homeworkName)
				.append("groupId", getGroupId())
				.append("groupName", groupName)
				.append("userId", getUserId())
				.append("userName", userName)
				.append("submitTime", getSubmitTime())
				.append("answerContent", getAnswerContent())
				.append("comment", getComment())
				.append("score", getScore())
				.append("excellent", getExcellent())
				.append("delFlag", getDelFlag())
				.toString();
	}
}
