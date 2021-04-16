package com.yi.project.course.group.controller;

import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.aspectj.lang.annotation.Log;
import com.yi.framework.aspectj.lang.enums.BusinessType;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.framework.web.page.TableDataInfo;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.service.ICourseGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 学生班级Controller
 *
 * @author yi
 * @date 2020-12-06
 */
@Api("学生班级管理")
@Controller
@RequestMapping("/course/group")
public class CourseGroupController extends BaseController {
	private String prefix = "course/group";

	@Autowired
	private ICourseGroupService courseGroupService;

	@ApiOperation("学生班级管理页")
	@RequiresPermissions("course:group:view")
	@GetMapping()
	public String group() {
		return prefix + "/group";
	}

	@ApiOperation("通用视图查看")
	@RequiresPermissions("course:group:view")
	@GetMapping("/view/{viewName}")
	public String groupView(@PathVariable("viewName") String viewName, ModelMap mmap, HttpServletRequest request) {
		request.getParameterMap().forEach((k, v) -> {
			mmap.put(k, v != null && v.length == 1 ? v[0] : v);
		});
		return prefix + "/" + viewName;
	}

	/**
	 * 查询学生班级列表
	 */
	@ApiOperation("学生班级列表查询")
	@RequiresPermissions("course:group:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CourseGroup courseGroup) {
		startPage();
		List<CourseGroup> list = courseGroupService.selectCourseGroupList(courseGroup);
		return getDataTable(list);
	}

	/**
	 * 查询学生班级列表
	 */
	@ApiOperation("生成班级二维码")
	@RequiresPermissions("course:group:view")
	@GetMapping("/groupQR/{groupId}")
	@ResponseBody
	public AjaxResult groupQR(@PathVariable Long groupId) {
		try {
			return new AjaxResult(AjaxResult.Type.SUCCESS,courseGroupService.groupQR(groupId));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(AjaxResult.Type.ERROR,"生成二维码失败："+e.getMessage());
		}
	}

	/**
	 * 导出学生班级列表
	 */
	@ApiOperation("学生班级列表导出")
	@RequiresPermissions("course:group:export")
	@Log(title = "学生班级", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(CourseGroup courseGroup) {
		List<CourseGroup> list = courseGroupService.selectCourseGroupList(courseGroup);
		ExcelUtil<CourseGroup> util = new ExcelUtil<CourseGroup>(CourseGroup.class);
		return util.exportExcel(list, "group");
	}

	/**
	 * 新增学生班级
	 */
	@ApiOperation("学生班级新增界面")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存学生班级
	 */
	@ApiOperation("新增保存学生班级")
	@RequiresPermissions("course:group:add")
	@Log(title = "学生班级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CourseGroup courseGroup) {
		return toAjax(courseGroupService.insertCourseGroup(courseGroup));
	}

	/**
	 * 修改学生班级
	 */
	@ApiOperation("学生班级编辑界面")
	@GetMapping("/edit/{groupId}")
	public String edit(@PathVariable("groupId") Long groupId, ModelMap mmap) {
		CourseGroup courseGroup = courseGroupService.selectCourseGroupById(groupId);
		mmap.put("courseGroup", courseGroup);
		return prefix + "/edit";
	}

	/**
	 * 修改保存学生班级
	 */
	@ApiOperation("修改保存学生班级")
	@RequiresPermissions("course:group:edit")
	@Log(title = "学生班级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CourseGroup courseGroup) {
		return toAjax(courseGroupService.updateCourseGroup(courseGroup));
	}

	/**
	 * 删除学生班级
	 */
	@ApiOperation("删除学生班级数据")
	@RequiresPermissions("course:group:remove")
	@Log(title = "学生班级", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(courseGroupService.deleteCourseGroupByIds(ids));
	}

	/**
	 * 课程下的班级列表
	 */
	@ApiOperation("课程下的班级列表")
	@RequiresPermissions("course:group:list")
	@GetMapping("/dict")
	@ResponseBody
	public AjaxResult groupsDict(Long courseId) {
		AjaxResult ajax = new AjaxResult();
		ajax.put("code", 200);
		ajax.put("value", courseGroupService.groupsDict(courseId));
		return ajax;
	}
}
