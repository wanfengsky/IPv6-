package com.yi.project.course.group.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yi.framework.aspectj.lang.annotation.Excel;
import com.yi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 班级学生对象 sku_group_user
 *
 * @author yi
 * @date 2020-12-05
 */
public class GroupUserVo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 班级学生关联ID */
	private Long groupUserId;
	/** 班级编号 */
	@Excel(name = "班级编号")
	private Long groupId;
	/** 学生id */
	@Excel(name = "学生id")
	private Long userId;

	/** 部门ID */
	private Long deptId;
	/** 部门名称 */
	private String deptName;
	/** 登录名称 */
	@Excel(name = "登录名称")
	private String loginName;
	/** 用户名称 */
	@Excel(name = "用户名称")
	private String userName;
	/** 用户类型 */
	private String userType;
	/** 用户邮箱 */
	@Excel(name = "用户邮箱")
	private String email;
	/** 手机号码 */
	@Excel(name = "手机号码")
	private String phonenumber;
	/** + 生日 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;
	/** 用户性别 */
	@Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
	private String sex;
	/** 帐号状态（0正常 1停用） */
	@Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
	private String status;

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("groupUserId", getGroupUserId())
				.append("groupId", getGroupId())
				.append("userId", getUserId())
				.append("createBy", getCreateBy())
				.append("createTime", getCreateTime())
				.append("deptId", deptId)
				.append("deptName", deptName)
				.append("loginName", loginName)
				.append("userName", userName)
				.append("userType", userType)
				.append("email", email)
				.append("phonenumber", phonenumber)
				.append("birthday", birthday)
				.append("sex", sex)
				.append("status", status)
				.toString();
	}
}
