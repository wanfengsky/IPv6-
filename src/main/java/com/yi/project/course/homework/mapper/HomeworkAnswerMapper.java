package com.yi.project.course.homework.mapper;

import java.util.List;
import com.yi.project.course.homework.domain.HomeworkAnswer;

/**
 * 作业提交Mapper接口
 * 
 * @author yi
 * @date 2020-12-05
 */
public interface HomeworkAnswerMapper {
    /**
     * 查询作业提交
     * 
     * @param answerId 作业提交ID
     * @return 作业提交
     */
    public HomeworkAnswer selectHomeworkAnswerById(Long answerId);

    /**
     * 查询作业提交列表
     * 
     * @param homeworkAnswer 作业提交
     * @return 作业提交集合
     */
    public List<HomeworkAnswer> selectHomeworkAnswerList(HomeworkAnswer homeworkAnswer);

    /**
     * 新增作业提交
     * 
     * @param homeworkAnswer 作业提交
     * @return 结果
     */
    public int insertHomeworkAnswer(HomeworkAnswer homeworkAnswer);

    /**
     * 修改作业提交
     * 
     * @param homeworkAnswer 作业提交
     * @return 结果
     */
    public int updateHomeworkAnswer(HomeworkAnswer homeworkAnswer);

    /**
     * 删除作业提交
     * 
     * @param answerId 作业提交ID
     * @return 结果
     */
    public int deleteHomeworkAnswerById(Long answerId);

    /**
     * 批量删除作业提交
     * 
     * @param answerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHomeworkAnswerByIds(String[] answerIds);
}
