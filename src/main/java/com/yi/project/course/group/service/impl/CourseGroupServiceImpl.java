package com.yi.project.course.group.service.impl;

import java.io.File;
import java.util.List;

import com.yi.common.utils.DateUtils;
import com.yi.common.utils.StringUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.common.utils.zxing.MatrixImageUtils;
import com.yi.project.course.course.service.CoursesMap;
import com.yi.project.course.group.domain.HomeworkGroupQuery;
import com.yi.project.course.group.service.GroupsMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.yi.project.course.group.mapper.CourseGroupMapper;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.service.ICourseGroupService;
import com.yi.common.utils.text.Convert;

/**
 * 学生班级Service业务层处理
 *
 * @author yi
 * @date 2020-12-06
 */
@Service("group")
public class CourseGroupServiceImpl implements ICourseGroupService {
	@Autowired
	private CourseGroupMapper courseGroupMapper;
	@Autowired
	private GroupsMap groupsMap;
	@Autowired
	private CoursesMap coursesMap;
	@Value("${skuman.serverPath}")
	private String serverPath;
	@Value("${skuman.logoPath}")
	private String logoPath;

	/**
	 * 查询学生班级
	 *
	 * @param groupId 学生班级ID
	 * @return 学生班级
	 */
	@Override
	public CourseGroup selectCourseGroupById(Long groupId) {
		return courseGroupMapper.selectCourseGroupById(groupId);
	}

	/**
	 * 查询学生班级列表
	 *
	 * @param query 学生班级
	 * @return 学生班级
	 */
	@Override
	public List<CourseGroup> selectCourseGroupList(CourseGroup query) {
		if (ShiroUtils.isStudent()) {
			query.setStudentId(ShiroUtils.getUserId());
		} else {
			query.setCreateBy(ShiroUtils.currUserIdForSQL());
		}
		List<CourseGroup> courseGroups = courseGroupMapper.selectCourseGroupList(query);
		if(CollectionUtils.isNotEmpty(courseGroups)){
			courseGroups.forEach(e->{
				e.setCourseName(coursesMap.getName(e.getCourseId()));
			});
		}
		return courseGroups;
	}

	/**
	 * 生成加入分组的二维码
	 *
	 * @param groupId
	 * @return
	 */
	@Override
	public String groupQR(Long groupId) throws Exception {
		if (StringUtils.isNotEmpty(logoPath) && new File(logoPath).exists()) {
			return MatrixImageUtils.encode(serverPath + "/course/group/user/addGroup/" + groupId, logoPath).toBase64();
		} else {
			return MatrixImageUtils.encode(serverPath + "/course/group/user/addGroup/" + groupId).toBase64();
		}
	}

	/**
	 * 新增学生班级
	 *
	 * @param courseGroup 学生班级
	 * @return 结果
	 */
	@Override
	public int insertCourseGroup(CourseGroup courseGroup) {
		courseGroup.setCreateBy(ShiroUtils.getCurrUserId());
		courseGroup.setCreateTime(DateUtils.getNowDate());
		int result = courseGroupMapper.insertCourseGroup(courseGroup);
		groupsMap.refresh(courseGroup);
		return result;
	}

	/**
	 * 修改学生班级
	 *
	 * @param courseGroup 学生班级
	 * @return 结果
	 */
	@Override
	public int updateCourseGroup(CourseGroup courseGroup) {
		groupsMap.refresh(courseGroup);
		return courseGroupMapper.updateCourseGroup(courseGroup);
	}

	/**
	 * 删除学生班级对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteCourseGroupByIds(String ids) {
		Long[] idArr = Convert.toLongArray(ids);
		groupsMap.flush(idArr);
		return courseGroupMapper.deleteCourseGroupByIds(idArr);
	}

	/**
	 * 删除学生班级信息
	 *
	 * @param groupId 学生班级ID
	 * @return 结果
	 */
	@Override
	public int deleteCourseGroupById(Long groupId) {
		groupsMap.flush(groupId);
		return courseGroupMapper.deleteCourseGroupById(groupId);
	}

	@Override
	public List<CourseGroup> groupsDict(Long courseId) {
		CourseGroup query = new CourseGroup();
		query.setCourseId(courseId);
		query.setCreateBy(ShiroUtils.currUserIdForSQL());
		query.getParams().put("validTime", DateUtils.getTime());
		return courseGroupMapper.selectCourseGroupList(query);
	}

	@Override
	public List<CourseGroup> groupsDict(String courseId) {
		return groupsDict(Long.parseLong(courseId));
	}


    /**
     * 根据传入的班级ids，获取对应的课程ids
     * @param groupIds 班级ids
     * @return
     */
    public List<Long> getCouserIdsByGroupIds(List<Long> groupIds){
        return courseGroupMapper.getCouserIdsByGroupIds(groupIds);
    }

	@Override
	public List<CourseGroup> homeworkGroups(Long homeworkId) {
		HomeworkGroupQuery query = new HomeworkGroupQuery();
		query.setHomeworkId(homeworkId);
		return homeworkGroups(query);
	}

	@Override
	public List<CourseGroup> homeworkGroups(HomeworkGroupQuery query) {
		if (query.isValid()) {
			query.getParams().put("validTime", DateUtils.getTime());
		}
		return courseGroupMapper.selectGroupsForHomework(query);
	}
}
