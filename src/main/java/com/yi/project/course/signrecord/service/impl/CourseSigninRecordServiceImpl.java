package com.yi.project.course.signrecord.service.impl;

import java.util.*;

import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.service.ICourseService;
import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.signin.domain.CourseSigninVo;
import com.yi.project.course.signin.service.ICourseSigninService;
import com.yi.project.course.signrecord.domain.CourseSigninRecordVo;
import com.yi.project.system.role.service.IRoleService;
import com.yi.project.system.user.domain.User;
import com.yi.project.system.user.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.course.signrecord.mapper.CourseSigninRecordMapper;
import com.yi.project.course.signrecord.domain.CourseSigninRecord;
import com.yi.project.course.signrecord.service.ICourseSigninRecordService;
import com.yi.common.utils.text.Convert;

/**
 * 学生签到记录Service业务层处理
 * 
 * @author yi
 * @date 2020-12-03
 */
@Service
public class CourseSigninRecordServiceImpl implements ICourseSigninRecordService {
    @Autowired
    private CourseSigninRecordMapper courseSigninRecordMapper;
    @Autowired
    private ICourseSigninService courseSigninService;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IUserService userService;
    /**
     * 为签到赋值 用户名、课程名、签到表名
     * @param courseSigninRecordVo 签到对象
     */
    public void initVoParam(CourseSigninRecordVo courseSigninRecordVo){
        //1.获取签到记录表信息
        Long signinId = courseSigninRecordVo.getSigninId();
        CourseSigninVo courseSigninVo = courseSigninService.selectCourseSigninById(signinId);
        if(null == courseSigninVo){
            return;
        }
        courseSigninService.initVoParam(courseSigninVo);
        //2.从签到记录表获取 课程名、签到表名


        courseSigninRecordVo.setCourseName(courseSigninVo.getCourseName());
        courseSigninRecordVo.setSigninName(courseSigninVo.getSigninName());
        //3.获取  用户名
        User u = userService.selectUserById(courseSigninRecordVo.getUserId());
        courseSigninVo.setUserName(u.getUserName());
        courseSigninRecordVo.setUserName(u.getUserName());
    }
    /**
     * 查询学生签到记录
     * 
     * @param recordId 学生签到记录ID
     * @return 学生签到记录
     */
    @Override
    public CourseSigninRecordVo selectCourseSigninRecordById(Long recordId) {
        CourseSigninRecordVo courseSigninRecordVo = courseSigninRecordMapper.selectCourseSigninRecordById(recordId);
        //为签到赋值 用户名、课程名、签到表名
        initVoParam(courseSigninRecordVo);
        return courseSigninRecordVo;
    }

    /**
     * 查询学生签到记录列表
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 学生签到记录
     */
    @Override
    public List<CourseSigninRecordVo> selectCourseSigninRecordList(CourseSigninRecord courseSigninRecord,Long courseId) {
        courseSigninRecord.setDelFlag("0");
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        //2.获取用户角色
        String roles = roleService.getRolesByUserId(userID);
        List<Long> signinIds =  null;
        //3.如果是学生, 直接根据学生id，返回
        if(roles.equals("student")) {
            courseSigninRecord.setUserId(userID);
            //4.如果没有选择签到表,需要根据课程id,获取签到表记录
            if (courseSigninRecord.getSigninId() == null) {
                //5.如果选择了课程
                if (courseId != null) {
                    //6.根据课程id,获取签到记录表
                    signinIds = new ArrayList<Long>();
                    List<CourseSigninVo> courseSigninVos = courseSigninService.getSigninByCourseId(courseId,false);

                    if(CollectionUtils.isNotEmpty(courseSigninVos)){
                        for(CourseSigninVo c : courseSigninVos) {
                            signinIds.add(c.getSigninId());
                        }
                    }


                } else {
                    //7.获取学生的所有课程id
                    List<Long> courseIds = courseService.selectCourseIdByStudentId(userID);
                    signinIds = new ArrayList<Long>();
                    List<CourseSigninVo> courseSigninVos = courseSigninService.selectSigninByCourseIds(courseIds);
                    if(CollectionUtils.isNotEmpty(courseSigninVos)){
                        for(CourseSigninVo c : courseSigninVos) {
                            signinIds.add(c.getSigninId());
                        }
                    }
                }


            }


        } else if(roles.equals("teacher")) {
            //8.如果没有选择签到表
            if (courseSigninRecord.getSigninId() == null) {
                //9.如果选择了课程
                if (courseId != null) {
                    signinIds = new ArrayList<Long>();
                    List<CourseSigninVo> courseSigninVos = courseSigninService.getSigninByCourseId(courseId,false);
                    if(CollectionUtils.isNotEmpty(courseSigninVos)){
                        for(CourseSigninVo c : courseSigninVos) {
                            signinIds.add(c.getSigninId());
                        }
                    }
                } else {

                    //10.获取教师的所有课程id
                    List<Long> courseIds = courseService.selectCourseIdByTeacherId(userID);
                    signinIds = new ArrayList<Long>();
                    List<CourseSigninVo> courseSigninVos = courseSigninService.selectSigninByCourseIds(courseIds);
                    if(CollectionUtils.isNotEmpty(courseSigninVos)){
                        for(CourseSigninVo c : courseSigninVos) {
                            signinIds.add(c.getSigninId());
                        }
                    }
                }

            }
        } else {
            //管理员
            //8.如果没有选择签到表
            if (courseSigninRecord.getSigninId() == null) {
                //9.如果选择了课程
                signinIds = new ArrayList<Long>();
                if (courseId != null) {
                    signinIds = new ArrayList<Long>();
                    List<CourseSigninVo> courseSigninVos = courseSigninService.getSigninByCourseId(courseId,false);
                    if(CollectionUtils.isNotEmpty(courseSigninVos)){
                        for(CourseSigninVo c : courseSigninVos) {
                            signinIds.add(c.getSigninId());
                        }
                    }
                }

            }

        }

        if(signinIds!= null && signinIds.size() == 0){

            if(roles.equals("student") || roles.equals("teacher")){
                return new ArrayList<CourseSigninRecordVo>();
            }else {
                signinIds = null;
            }
        }
        List<CourseSigninRecordVo> courseSigninRecordVos = courseSigninRecordMapper.selectCourseSigninRecordList(courseSigninRecord,signinIds);
        //为签到赋值 用户名、课程名、签到表名
        for(CourseSigninRecordVo c : courseSigninRecordVos) {
            initVoParam(c);
        }

        return courseSigninRecordVos;
    }

    /**
     * 新增学生签到记录
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 结果
     */
    @Override
    public int insertCourseSigninRecord(CourseSigninRecord courseSigninRecord) {

        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        //2.签到过
        if(isSignIn(courseSigninRecord.getSigninId(),userID) != null){
            return 0;
        }
        courseSigninRecord.setUserId(userID);

        courseSigninRecord.setCreateTime(DateUtils.getNowDate());
        return courseSigninRecordMapper.insertCourseSigninRecord(courseSigninRecord);
    }

    /**
     * 首页学生签到，根据时间，签到符合时间的签到表，返回签到表信息
     * @return 签到表信息
     */
    @Override
    public String insertCourseSigninRecord() {

        //1.获取学生可以签到的签到表信息
        List<CourseSigninVo> courseSignins = getSigninInfo();
        //2.如果签到表为空，返回无可用签到表
        if(null == courseSignins || courseSignins.size() ==0){
            return "当前无可用签到表";
        }

        //3.创建签到记录，根据第一个签到表信息，为签到记录赋值
        CourseSigninRecord courseSigninRecord =  new CourseSigninRecord();
        courseSigninRecord.setSigninId(courseSignins.get(0).getSigninId());

        courseSigninRecord.setCreateTime(DateUtils.getNowDate());
        //4.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        courseSigninRecord.setUserId(userID);
        courseSigninRecordMapper.insertCourseSigninRecord(courseSigninRecord);
        return "签到成功\\n签到课程为: "+courseSignins.get(0).getSigninName()+"\\n签到表名为: "+courseSignins.get(0).getSigninName();
    }

    /**
     * 修改学生签到记录
     * 
     * @param courseSigninRecord 学生签到记录
     * @return 结果
     */
    @Override
    public int updateCourseSigninRecord(CourseSigninRecord courseSigninRecord) {
        return courseSigninRecordMapper.updateCourseSigninRecord(courseSigninRecord);
    }

    /**
     * 删除学生签到记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCourseSigninRecordByIds(String ids) {
        return courseSigninRecordMapper.deleteFlagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生签到记录信息
     * 
     * @param recordId 学生签到记录ID
     * @return 结果
     */
    @Override
    public int deleteCourseSigninRecordById(Long recordId) {
        return courseSigninRecordMapper.deleteCourseSigninRecordById(recordId);
    }
    public List<CourseSigninVo> getSigninInfo(){
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        //2.获取学生的所有课程id
        List<Long> courseIds = courseService.selectCourseIdByStudentId(userID);
        List<CourseSigninVo> courseSigninVos= new ArrayList<CourseSigninVo>();
        //3.判空
        if(null == courseIds || courseIds.size()<1)
        {
            return null;
        }
        for(Long courseId :courseIds) {
            courseSigninVos.addAll(courseSigninService.getSigninByCourseId(courseId,true));
        }
        //4.判断是否签到过，清除签到过的id

        Iterator<CourseSigninVo> iter = courseSigninVos.iterator();
        while (iter.hasNext()) {
            CourseSigninVo cs = iter.next();
            Long signRecordId = isSignIn(cs.getSigninId(),userID);
            if(signRecordId != null) {
                iter.remove();

            }
        }


        //5.赋值名称
        for(CourseSigninVo c : courseSigninVos) {
            courseSigninService.initVoParam(c);
        }

        return courseSigninVos;
    }

    /**
     *
     * @param signinId 签到表id
     * @param userID 用户id
     * @return 返回签到id
     */
    public Long isSignIn(Long signinId,Long userID){
        return courseSigninRecordMapper.isSignIn(signinId,userID);
    }

}
