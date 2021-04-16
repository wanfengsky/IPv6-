package com.yi.project.course.signin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yi.common.constant.Constants;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.service.ICourseService;
import com.yi.project.course.signin.domain.CourseSigninVo;
import com.yi.project.course.signrecord.domain.CourseSigninRecord;
import com.yi.project.course.signrecord.service.ICourseSigninRecordService;
import com.yi.project.system.role.service.IRoleService;
import com.yi.project.system.user.domain.User;
import com.yi.project.system.user.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.course.signin.mapper.CourseSigninMapper;
import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.signin.service.ICourseSigninService;
import com.yi.common.utils.text.Convert;
import java.util.Date;
/**
 * 签到管理Service业务层处理
 * 
 * @author yi
 * @date 2020-12-03
 */
@Service
public class CourseSigninServiceImpl implements ICourseSigninService {
    @Autowired
    private CourseSigninMapper courseSigninMapper;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;
    @Autowired
    private ICourseSigninRecordService courseSigninRecordService;

    public void initVoParam(CourseSigninVo courseSigninVo){
        //1.为签到赋值 课程名
        Course c = courseService.selectCourseById(courseSigninVo.getCourseId());
        courseSigninVo.setCourseName(c.getCourseName());
        Long createById = 0L;

        try {
            createById = Long.parseLong(courseSigninVo.getCreateBy());
        } catch (Exception e){
            System.out.println("getCreateBy ");
        }
        if(createById != 0L){
            User u = userService.selectUserById(createById);
            courseSigninVo.setCreateBy(u.getUserName());
        }

        //2.如果是学生，判断是否签到
        if(ShiroUtils.isStudent()){
            Long signRecordId = courseSigninRecordService.isSignIn(courseSigninVo.getSigninId(),ShiroUtils.getUserId());

            if(signRecordId != null) {
                courseSigninVo.setSigninFlag(Constants.SIGN_IN_TYPE_SIGNED);
            } else {
                //3.判断是可签到还是  未签到(错过签到)
                Date nowDate = new Date();
                if(courseSigninVo.getStartTime().before(nowDate) && courseSigninVo.getEndTime().after(nowDate)) {
                    courseSigninVo.setSigninFlag(Constants.SIGN_IN_TYPE_SIGN);
                } else {
                    courseSigninVo.setSigninFlag(Constants.SIGN_IN_TYPE_NOSIGN);
                }

            }
        }


    }
    /**
     * 查询签到管理
     * 
     * @param signinId 签到管理ID
     * @return 签到管理
     */
    @Override
    public CourseSigninVo selectCourseSigninById(Long signinId) {
        CourseSigninVo courseSigninVo = courseSigninMapper.selectCourseSigninById(signinId);
        //1.为签到赋值 用户名和课程名
        initVoParam(courseSigninVo);
        return courseSigninVo;
    }


    /**
     * 查询签到管理列表
     * 
     * @param courseSignin 签到管理
     * @return 签到管理
     */
    @Override
    public List<CourseSigninVo> selectCourseSigninList(CourseSignin courseSignin) {

        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        //2.获取用户角色
        String roles = roleService.getRolesByUserId(userID);

        List<Long> courseIds = null;
        //3.如果是学生
        if(roles.equals("student")) {
            //4.获取学生的课程id集合
            courseIds = courseService.selectCourseIdByStudentId(userID);
            if(!CollectionUtils.isNotEmpty(courseIds)){
                return null;
            }
            //5.根据集合获取签到信息
            List<CourseSigninVo> courseSigninVos = selectSigninByCourseIds(courseIds);
            if(CollectionUtils.isNotEmpty(courseSigninVos)){
                for(CourseSigninVo c : courseSigninVos) {
                    initVoParam(c);
                }
            }
            return courseSigninVos;
        } else if(roles.equals("teacher")) {
            //6.如果是 教师 设置创建者id 为教师id
            courseSignin.setCreateBy(userID.toString());

        }
        courseSignin.setDelFlag("0");
        List<CourseSigninVo> courseSigninVos = courseSigninMapper.selectCourseSigninList(courseSignin);
        if(CollectionUtils.isNotEmpty(courseSigninVos)) {
            for (CourseSigninVo c : courseSigninVos) {
                initVoParam(c);
            }
        }
        return courseSigninVos;
    }

    /**
     * 新增签到管理
     * 
     * @param courseSignin 签到管理
     * @return 结果
     */
    @Override
    public int insertCourseSignin(CourseSignin courseSignin) {
        courseSignin.setCreateTime(DateUtils.getNowDate());
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        courseSignin.setCreateBy(userID.toString());
        return courseSigninMapper.insertCourseSignin(courseSignin);
    }

    /**
     * 修改签到管理
     * 
     * @param courseSignin 签到管理
     * @return 结果
     */
    @Override
    public int updateCourseSignin(CourseSignin courseSignin) {
        return courseSigninMapper.updateCourseSignin(courseSignin);
    }

    /**
     * 删除签到管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCourseSigninByIds(String ids) {
        return courseSigninMapper.deleteFlagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除签到管理信息
     * 
     * @param signinId 签到管理ID
     * @return 结果
     */
    @Override
    public int deleteCourseSigninById(Long signinId) {
        return courseSigninMapper.deleteCourseSigninById(signinId);
    }

    /**
     * 根据课程id查询签到管理列表
     *
     * @param courseIds 课程ids
     * @return 签到管理集合
     */
    public List<CourseSigninVo> selectSigninByCourseIds(List<Long> courseIds){
        if(CollectionUtils.isNotEmpty(courseIds)){
            return  courseSigninMapper.selectSigninByCourseIds(courseIds);
        }
        return null;
    }

    /**
     * 根据课程id
     * 课程签到表中的可在当前时段进行签到的  签到
     */
    public List<CourseSigninVo> getSigninByCourseId(Long courseId,Boolean signIn){

        return signIn ? courseSigninMapper.selectSigninByCourseId(courseId,DateUtils.getNowDate()):courseSigninMapper.selectSigninByCourseId(courseId,null);
    }

    /**
     * 获取学生当前最需要的签到表，根据签到表结束时间判断
     * @return
     */
    public List<CourseSigninVo> getNowSignInByStudent(){
        //1.获取学生可以签到的签到表信息
        List<CourseSigninVo> courseSignins = courseSigninRecordService.getSigninInfo();
        //2.如果签到表为空，返回无可用签到表
        if(null == courseSignins || courseSignins.size() ==0){
            return new ArrayList<CourseSigninVo>();
        }

        return courseSignins.subList(0,1);
    }
}
