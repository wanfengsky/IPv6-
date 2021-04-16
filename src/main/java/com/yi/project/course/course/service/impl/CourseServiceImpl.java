package com.yi.project.course.course.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.project.course.course.service.CoursesMap;
import com.yi.project.course.group.domain.GroupUserQuery;
import com.yi.project.course.group.domain.GroupUserVo;
import com.yi.project.course.group.service.ICourseGroupService;
import com.yi.project.course.group.service.IGroupUserService;
import com.yi.project.system.role.domain.Role;
import com.yi.project.system.role.service.IRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.course.course.mapper.CourseMapper;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.service.ICourseService;
import com.yi.common.utils.text.Convert;

/**
 * 课程Service业务层处理
 * 
 * @author yi
 * @date 2020-12-03
 */
@Service("course")
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IGroupUserService groupUserService;
    @Autowired
    private ICourseGroupService courseGroupService;
    @Autowired
    private CoursesMap coursesMap;

    /**
     * 查询课程
     * 
     * @param courseId 课程ID
     * @return 课程
     */
    @Override
    public Course selectCourseById(Long courseId) {
        return courseMapper.selectCourseById(courseId);
    }

    public List<Long> selectCourseIdByStudentId(Long userID){
        List<Long> courseIds = null;
        //1.根据用户id，找到学生用户所有的 班级id
        GroupUserQuery groupUser = new GroupUserQuery();
        groupUser.setUserId(userID);

        List<Long> groupIds  = groupUserService.selectGroupsByUserId(userID);
        //2.如果用户班级为空，说明学生没有选择课程，返回null
        if(null == groupIds || groupIds.size()<1){
            return courseIds;
        }

        //2.根据班级id,获取课程id
        courseIds = courseGroupService.getCouserIdsByGroupIds(groupIds);

        return  courseIds;
    }

    /**
     * 查询课程id列表
     *
     * @param userID 教师用户id
     * @return 课程id集合
     */
    public List<Long> selectCourseIdByTeacherId(Long userID){
        return  courseMapper.selectCourseIdByTeacherId(userID);
    }
    /**
     * 查询课程列表
     * 
     * @param course 课程
     * @return 课程
     */
    @Override
    public List<Course> selectCourseList(Course course) {
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        //2.获取当前用户角色
        String userRole = roleService.getRolesByUserId(userID);

        //3.如果是教师，设置课程id,如果是学生，查找学生的选课表，根据选课表找到课程id TODO 区分学生和教师
        if(userRole.equals("teacher")){
            course.setUserId(userID);
        } else if(userRole.equals("student")) {
            //4.如果是学生，选获取学生选课表，再获取相应的课程信息
            List<Long> courseIds = selectCourseIdByStudentId(userID);
            List<Course> courses = new ArrayList<Course>();
            if(CollectionUtils.isNotEmpty(courseIds)) {
                for (Long id : courseIds) {
                    Course c = courseMapper.selectCourseById(id);
                    courses.add(c);
                }
            }
            return  courses;
        }

        course.setDelFlag("0");
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增课程
     * 
     * @param course 课程
     * @return 结果
     */
    @Override
    public int insertCourse(Course course) {
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();

        //2.设置用户id和创建者id

        course.setUserId(userID);
        course.setCreateBy(userID.toString());
        //3.设置时间
        course.setCreateTime(DateUtils.getNowDate());
        int result = courseMapper.insertCourse(course);
        coursesMap.refresh(course);
        return result;
    }

    /**
     * 修改课程
     * 
     * @param course 课程
     * @return 结果
     */
    @Override
    public int updateCourse(Course course) {
        coursesMap.refresh(course);
        return courseMapper.updateCourse(course);
    }

    /**
     * 删除课程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(String ids) {
        Long[] idArr = Convert.toLongArray(ids);
        coursesMap.flush(idArr);
        return courseMapper.deleteFlagByIds(idArr);
    }

    /**
     * 删除课程信息
     * 
     * @param courseId 课程ID
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long courseId) {
        coursesMap.flush(courseId);
        return courseMapper.deleteCourseById(courseId);
    }
    /**
     * 查询课程id列表
     *
     * @param course 课程
     * @return 课程id集合
     */
    public List<Long> selectCourseIdList(Course course){
        return courseMapper.selectCourseIdList(course);
    }

    /** 课程字典，用于下拉框查询条件 */
    public List<Course> courseDict(){
        Course query = new Course();
        if (ShiroUtils.isStudent()) {
            query.setStudentId(ShiroUtils.getUserId());
        } else {
            query.setUserId(ShiroUtils.currUserId());
        }
        query.setDelFlag("0");
        return courseMapper.selectCourseList(query);
    }
}
