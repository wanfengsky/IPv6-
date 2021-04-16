package com.yi.project.course.topic.service;

import java.util.List;

import com.yi.project.course.course.domain.Course;
import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.topic.domain.Topic;
import com.yi.project.course.topic.domain.TopicVo;

/**
 * 学生话题Service接口
 * 
 * @author yi
 * @date 2020-12-05
 */
public interface ITopicService {
    /**
     * 查询学生话题
     * 
     * @param topicId 学生话题ID
     * @return 学生话题
     */
    public TopicVo selectTopicById(Long topicId);

    /**
     * 查询学生话题列表
     * 
     * @param topic 学生话题
     * @return 学生话题集合
     */
    public List<TopicVo> selectTopicList(Topic topic);

    /**
     * 新增学生话题
     * 
     * @param topic 学生话题
     * @return 结果
     */
    public int insertTopic(Topic topic);

    /**
     * 修改学生话题
     * 
     * @param topic 学生话题
     * @return 结果
     */
    public int updateTopic(Topic topic);

    /**
     * 批量删除学生话题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTopicByIds(String ids);

    /**
     * 删除学生话题信息
     * 
     * @param topicId 学生话题ID
     * @return 结果
     */
    public int deleteTopicById(Long topicId);

    /***
     * 根据用户id，获取当前用户参与的课程信息
     * @return
     */
    public List<Course> topicCourseDict();


}
