package com.yi.project.course.course.domain;

/**
 * 课程类型【字典】（1代表普通课程，2代表公开课程）
 */
public enum CourseType {
	NORMAL("1", "普通课程"),
	PUBLIC("2", "公开课程");

	private final String code;
	private final String info;

	CourseType(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public boolean is(String code) {
		return this.code.equals(code);
	}

	public String getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}
}
