package com.yi.project.course.homework.service.impl;

import com.yi.common.constant.UserConstants;
import com.yi.common.exception.BusinessException;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.common.utils.text.Convert;
import com.yi.project.course.group.service.GroupsMap;
import com.yi.project.course.homework.domain.Homework;
import com.yi.project.course.homework.domain.HomeworkAllocat;
import com.yi.project.course.homework.domain.HomeworkAnswer;
import com.yi.project.course.homework.mapper.HomeworkAnswerMapper;
import com.yi.project.course.homework.mapper.HomeworkMapper;
import com.yi.project.course.homework.service.IHomeworkAnswerService;
import com.yi.project.system.user.service.UsersMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 作业提交Service业务层处理
 *
 * @author yi
 * @date 2020-12-05
 */
@Service
public class HomeworkAnswerServiceImpl implements IHomeworkAnswerService {
	@Autowired
	private HomeworkAnswerMapper homeworkAnswerMapper;
	@Autowired
	private HomeworkMapper homeworkMapper;
	@Autowired
	private UsersMap usersMap;
	@Autowired
	private GroupsMap groupsMap;

	/**
	 * 查询作业提交
	 *
	 * @param answerId 作业提交ID
	 * @return 作业提交
	 */
	@Override
	public HomeworkAnswer selectHomeworkAnswerById(Long answerId) {
		HomeworkAnswer homeworkAnswer = homeworkAnswerMapper.selectHomeworkAnswerById(answerId);
		// 用户名转换
		homeworkAnswer.setUserName(usersMap.getName(homeworkAnswer.getUserId()));
		return homeworkAnswer;
	}

	/**
	 * 查询作业提交列表
	 *
	 * @param homeworkAnswer 作业提交
	 * @return 作业提交
	 */
	@Override
	public List<HomeworkAnswer> selectHomeworkAnswerList(HomeworkAnswer homeworkAnswer) {
		homeworkAnswer.setOwner(ShiroUtils.currUserId());
		List<HomeworkAnswer> answers = homeworkAnswerMapper.selectHomeworkAnswerList(homeworkAnswer);
		if (CollectionUtils.isNotEmpty(answers)) {
			// 用户名、班级名转换
			answers.forEach(e -> {
				e.setUserName(usersMap.getName(e.getUserId()));
				e.setGroupName(groupsMap.getName(e.getGroupId()));
			});
		}
		return answers;
	}

	/**
	 * 新增作业提交
	 *
	 * @param homeworkAnswer 作业提交
	 * @return 结果
	 */
	@Override
	public int insertHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
		Date now = DateUtils.getNowDate();
		// 校验超期
		Homework homework = homeworkMapper.selectHomeworkById(homeworkAnswer.getHomeworkId());
		if (homework == null || !UserConstants.YES.equals(homework.getPublish())
				|| homework.getDeadline() == null || now.after(homework.getDeadline())) {
			throw new BusinessException("作业无效或已过期！");
		}

		homeworkAnswer.setSubmitTime(now);
		homeworkAnswer.setUserId(ShiroUtils.getUserId());
		int result = homeworkAnswerMapper.insertHomeworkAnswer(homeworkAnswer);
		if (result > 0) {
			homeworkMapper.updateHomeworkAllocat(new HomeworkAllocat() {{
				setHomeworkId(homeworkAnswer.getHomeworkId());
				setGroupId(homeworkAnswer.getGroupId());
				setUpdateTime(now);
				setUpdateBy(ShiroUtils.getSysUser().getUserName());
				setSummitAdd(1L);
			}});
		}
		return result;
	}

	/**
	 * 修改作业提交
	 *
	 * @param homeworkAnswer 作业提交
	 * @return 结果
	 */
	@Override
	public int updateHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
		return homeworkAnswerMapper.updateHomeworkAnswer(homeworkAnswer);
	}

	/**
	 * 删除作业提交对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteHomeworkAnswerByIds(String ids) {
		return homeworkAnswerMapper.deleteHomeworkAnswerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除作业提交信息
	 *
	 * @param answerId 作业提交ID
	 * @return 结果
	 */
	@Override
	public int deleteHomeworkAnswerById(Long answerId) {
		return homeworkAnswerMapper.deleteHomeworkAnswerById(answerId);
	}
}
