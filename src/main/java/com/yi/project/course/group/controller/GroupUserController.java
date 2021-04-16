package com.yi.project.course.group.controller;

import com.yi.common.utils.poi.ExcelUtil;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.common.utils.text.Convert;
import com.yi.framework.aspectj.lang.annotation.Log;
import com.yi.framework.aspectj.lang.enums.BusinessType;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.framework.web.page.TableDataInfo;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.domain.CourseType;
import com.yi.project.course.course.service.CoursesMap;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.domain.GroupUserQuery;
import com.yi.project.course.group.domain.GroupUserVo;
import com.yi.project.course.group.service.ICourseGroupService;
import com.yi.project.course.group.service.IGroupUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 班级学生Controller
 *
 * @author yi
 * @date 2020-12-05
 */
@Api("班级学生管理")
@Controller
@RequestMapping("/course/group/user")
public class GroupUserController extends BaseController {
	@Autowired
	private IGroupUserService groupUserService;
	@Autowired
	private ICourseGroupService courseGroupService;
	@Autowired
	private CoursesMap coursesMap;

	/**
	 * 查询班级学生列表
	 */
	@ApiOperation("班级学生列表查询")
	@RequiresPermissions("course:groupuser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GroupUserQuery groupUser) {
		startPage();
		List<GroupUserVo> list = groupUserService.selectGroupUserList(groupUser);
		return getDataTable(list);
	}

	/**
	 * 导出班级学生列表
	 */
	@ApiOperation("班级学生列表导出")
	@RequiresPermissions("course:groupuser:export")
	@Log(title = "班级学生", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(GroupUserQuery groupUser) {
		List<GroupUserVo> list = groupUserService.selectGroupUserList(groupUser);
		ExcelUtil<GroupUserVo> util = new ExcelUtil<>(GroupUserVo.class);
		return util.exportExcel(list, "groupuser");
	}

	/**
	 * 新增保存班级学生
	 */
	@ApiOperation("新增保存班级学生")
	@RequiresPermissions("course:groupuser:add")
	@Log(title = "班级学生", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Long groupId, String userIds) {
		List<GroupUserQuery> list = new ArrayList<>();
		Arrays.stream(Convert.toLongArray(userIds)).forEach(uid -> list.add(new GroupUserQuery(groupId, uid)));
		return toAjax(groupUserService.insertGroupUsers(list));
	}

	/**
	 * 加入班级
	 *
	 * @return
	 */
	@ApiOperation("加入班級")
	@Log(title = "加入班级", businessType = BusinessType.INSERT)
	@PostMapping("/addGroup/{groupId}")
	@ResponseBody
	public AjaxResult addInGroup(@PathVariable Long groupId) {
		CourseGroup courseGroup = courseGroupService.selectCourseGroupById(groupId);
		if (courseGroup == null) {
			return new AjaxResult(AjaxResult.Type.ERROR, "加入班级失败：班级ID不存在！");
		} else {
			Course course = coursesMap.get(courseGroup.getCourseId());
			if (!CourseType.PUBLIC.is(course.getCourseType())) {
				return new AjaxResult(AjaxResult.Type.ERROR, "加入班级失败：只允许加入公开课班级！");
			}
		}
		try {
			if (0 < groupUserService.insertGroupUsers(Arrays.asList(new GroupUserQuery(groupId, ShiroUtils.getUserId())))) {
				return new AjaxResult(AjaxResult.Type.SUCCESS, "成功加入班级[" + courseGroup.getGroupName() + "]！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(AjaxResult.Type.ERROR, "加入班级失败：请查看是否已为班级成员！");
	}

	/**
	 * 加入班级返回我的班级页面
	 */
	@ApiOperation("加入班級返回我的班级页面")
	@Log(title = "加入班级", businessType = BusinessType.INSERT)
	@GetMapping("/addGroup/{groupId}")
	public String addGroup(@PathVariable Long groupId, ModelMap mmap) {
		AjaxResult result = addInGroup(groupId);
		mmap.put("addedResult", result);
		return "course/group/group";
	}

	/**
	 * 删除班级学生
	 */
	@ApiOperation("删除班级学生数据")
	@RequiresPermissions("course:groupuser:remove")
	@Log(title = "班级学生", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(groupUserService.deleteGroupUserByIds(ids));
	}
}
