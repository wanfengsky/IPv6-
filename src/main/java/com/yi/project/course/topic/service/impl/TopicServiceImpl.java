package com.yi.project.course.topic.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yi.common.constant.Constants;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.service.ICourseService;
import com.yi.project.course.group.domain.GroupUserQuery;
import com.yi.project.course.group.domain.GroupUserVo;
import com.yi.project.course.group.service.ICourseGroupService;
import com.yi.project.course.group.service.IGroupUserService;
import com.yi.project.course.topic.domain.TopicVo;
import com.yi.project.system.dict.service.IDictDataService;
import com.yi.project.system.role.domain.Role;
import com.yi.project.system.role.service.IRoleService;
import com.yi.project.system.user.domain.User;
import com.yi.project.system.user.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.course.topic.mapper.TopicMapper;
import com.yi.project.course.topic.domain.Topic;
import com.yi.project.course.topic.service.ITopicService;
import com.yi.common.utils.text.Convert;

/**
 * 学生话题Service业务层处理
 * 
 * @author yi
 * @date 2020-12-05
 */
@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IDictDataService dictDataService;
    /**
     * 查询学生话题
     * 
     * @param topicId 学生话题ID
     * @return 学生话题
     */
    @Override
    public TopicVo selectTopicById(Long topicId) {
        TopicVo topic = topicMapper.selectTopicById(topicId);
        //1.为话题赋值 用户名、头像和课程名
        User u = userService.selectUserById(topic.getUserId());
        topic.setUserName(u.getUserName());
        topic.setAvatar(u.getAvatar());
        Course c = courseService.selectCourseById(topic.getCourseId());
        topic.setCourseName(c.getCourseName());
        return topic;
    }

    /**
     * 查询学生话题列表
     * 
     * @param topic 学生话题
     * @return 学生话题
     */
    @Override
    public List<TopicVo> selectTopicList(Topic topic) {
        //1. 选择未删除的话题
        topic.setDelFlag("0");

        List<TopicVo> topicVos = topicMapper.selectTopicList(topic);

        //2.为话题赋值 用户名、头像和课程名
        for(TopicVo t : topicVos){
            User u = userService.selectUserById(t.getUserId());
            t.setUserName(u.getUserName());
            t.setAvatar(u.getAvatar());
            Course c = courseService.selectCourseById(t.getCourseId());
            t.setCourseName(c.getCourseName());
        }
        return topicVos;
    }

    /**
     * 新增学生话题
     * 
     * @param topic 学生话题
     * @return 结果
     */
    @Override
    public int insertTopic(Topic topic) {
        TopicVo topicVo =  null;
        //1.题主
        if(null == topic.getTopicPid()){
            topic.setTopicPid((long)0);
            topic.setUserType(Constants.TOPIC_USER_TYPE_ADMIN);
        } else {
            //2. 回复者 获取题目的标题和所属课程id
            topicVo = selectTopicById(topic.getTopicPid());
            topic.setTitle(topicVo.getTitle());
            topic.setCourseId(topicVo.getCourseId());
        }
        //3.获取时间
        topic.setCreateTime(DateUtils.getNowDate());
        //4.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        topic.setUserId(userID);
        //5. 判断是否为题主
        if(null == topic.getUserType() && null != topicVo && topicVo.getUserId() == userID){
            topic.setUserType(Constants.TOPIC_USER_TYPE_ADMIN);
        }
        //6.获取用户角色
       String role = roleService.getRolesByUserId(userID);

        //7.设置用户类型
        String userType = Constants.TOPIC_USER_TYPE_TEACHER;

        if(role.equals("admin")){
            userType = Constants.TOPIC_USER_TYPE_TEACHER;
        } else if(role.equals("teacher")) {
            userType = Constants.TOPIC_USER_TYPE_TEACHER;
        } else if(role.equals("student")) {
            userType = Constants.TOPIC_USER_TYPE_STUDENT;
        }

       if(null == topic.getUserType()){
           topic.setUserType(userType);
       }

        return topicMapper.insertTopic(topic);
    }

    /**
     * 修改学生话题
     * 
     * @param topic 学生话题
     * @return 结果
     */
    @Override
    public int updateTopic(Topic topic) {
        return topicMapper.updateTopic(topic);
    }

    /**
     * 删除学生话题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTopicByIds(String ids) {
        return topicMapper.deleteFlagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生话题信息
     * 
     * @param topicId 学生话题ID
     * @return 结果
     */
    @Override
    public int deleteTopicById(Long topicId) {
        return topicMapper.deleteTopicById(topicId);
    }

    public List<Course> topicCourseDict(){
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        //2.获取用户角色
        String roles = roleService.getRolesByUserId(userID);


        List<Long> courseIds = null;
        //3.如果是 是教师或者学生
        if(roles.equals("student")) {
            courseIds = courseService.selectCourseIdByStudentId(userID);

        } else if(roles.equals("teacher")) {
            //6.获取教师所讲授的所有课程id
            Course course = new Course();
            course.setDelFlag("0");
            course.setUserId(userID);
            courseIds = courseService.selectCourseIdList(course);
        } else {
            //7.管理员 找到所有的课程id
            Course course = new Course();
            course.setDelFlag("0");
            courseIds = courseService.selectCourseIdList(course);

        }

        //8. 根据课程id,获取课程信息
        List<Course> courses = new ArrayList<Course>();
        if(CollectionUtils.isNotEmpty(courseIds)){
            for(Long id : courseIds) {
                courses.add(courseService.selectCourseById(id));
            }
        }



        return courses;
    }

}
