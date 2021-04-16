package com.yi.project.course.homework.domain;

import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 作业分配对象 sku_homework_allocat
 *
 * @author yi
 * @date 2020-12-12
 */
public class MyHomework extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 作业批次ID */
	private Long allocatId;
	@Excel(name = "分配用户编号")
	private Long userId;
	/** 作业编号 */
	@Excel(name = "作业编号")
	private Long homeworkId;
	/** 分配组 */
	@Excel(name = "分配组ID")
	private Long groupId;
	/** 分配组 */
	@Excel(name = "分配组名")
	private String groupName;
	/** 分发人数 */
	@Excel(name = "分发人数")
	private Long receiveCount;
	/** 提交总数 */
	@Excel(name = "作业提交总人数")
	private Long summitCount;

	@Excel(name = "答案ID")
	private Long answerId;
	@Excel(name = "作业分数")
	private Long score;
	@Excel(name = "是否优秀")
	private String excellent;
	@Excel(name = "是否完成")
	private String isOver;
	@Excel(name = "提交时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date submitTime;

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
	@Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
	private Date deadline;

	public void setAllocatId(Long allocatId) {
		this.allocatId = allocatId;
	}

	public Long getAllocatId() {
		return allocatId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getExcellent() {
		return excellent;
	}

	public void setExcellent(String excellent) {
		this.excellent = excellent;
	}

	public String getIsOver() {
		return isOver;
	}

	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("allocatId", allocatId)
				.append("userId", userId)
				.append("homeworkId", homeworkId)
				.append("groupId", groupId)
				.append("groupName", groupName)
				.append("receiveCount", receiveCount)
				.append("summitCount", summitCount)
				.append("answerId", answerId)
				.append("score", score)
				.append("excellent", excellent)
				.append("isOver", isOver)
				.append("submitTime", submitTime)
				.append("homeworkName", homeworkName)
				.append("chapterId", chapterId)
				.append("chapterName", chapterName)
				.append("courseId", courseId)
				.append("courseName", courseName)
				.append("deadline", deadline)
				.toString();
	}
}
