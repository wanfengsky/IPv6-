package com.yi.project.course.topic.mapper;

import java.util.List;
import com.yi.project.course.topic.domain.Topic;
import com.yi.project.course.topic.domain.TopicVo;

/**
 * 学生话题Mapper接口
 * 
 * @author yi
 * @date 2020-12-05
 */
public interface TopicMapper {
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
     * 删除学生话题
     * 
     * @param topicId 学生话题ID
     * @return 结果
     */
    public int deleteTopicById(Long topicId);

    /**
     * 批量删除学生话题
     * 
     * @param topicIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTopicByIds(String[] topicIds);
    /**
     * 批量删除学生话题,把删除标记转为'2'
     *
     * @param topicIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlagByIds(String[] topicIds);

    /***
     * 获取和话题相关的所有的课程id，管理员用
     * @return
     */
    public List<Long> getCourseIds();
}
