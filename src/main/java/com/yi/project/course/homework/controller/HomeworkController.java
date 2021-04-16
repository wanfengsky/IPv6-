package com.yi.project.course.homework.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.yi.project.course.group.domain.CourseGroup;
import com.yi.project.course.group.service.ICourseGroupService;
import com.yi.project.course.group.domain.HomeworkGroupQuery;
import com.yi.project.course.homework.domain.HomeworkAnswer;
import com.yi.project.course.homework.domain.MyHomework;
import com.yi.project.course.homework.service.IHomeworkAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yi.framework.aspectj.lang.annotation.Log;
import com.yi.framework.aspectj.lang.enums.BusinessType;
import com.yi.project.course.homework.domain.Homework;
import com.yi.project.course.homework.service.IHomeworkService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 作业管理Controller
 *
 * @author yi
 * @date 2020-12-05
 */
@Api("作业管理管理")
@Controller
@RequestMapping("/course/homework")
public class HomeworkController extends BaseController {
	private String prefix = "course/homework";
	@Autowired
	private IHomeworkService homeworkService;
	@Autowired
	private ICourseGroupService courseGroupService;
	@Autowired
	private IHomeworkAnswerService homeworkAnswerService;

	@ApiOperation("作业管理管理页")
	@RequiresPermissions("course:homework:view")
	@GetMapping()
	public String homework() {
		return prefix + "/homework";
	}

	@ApiOperation("通用视图查看")
	@RequiresPermissions("course:homework:view")
	@GetMapping("/view/{viewName}")
	public String homeworkView(@PathVariable("viewName") String viewName, ModelMap mmap, HttpServletRequest request) {
		request.getParameterMap().forEach((k, v) -> {
			mmap.put(k, v != null && v.length == 1 ? v[0] : v);
		});
		return prefix + "/" + viewName;
	}

	/**
	 * 修改作业管理
	 */
	@ApiOperation("作业提交界面")
	@RequiresPermissions("course:hanswer:add")
	@GetMapping("/toAnswer")
	public String toAnswer(Long homeworkId, Long groupId, Long answerId, ModelMap mmap) {
		Homework homework = homeworkService.selectHomeworkById(homeworkId);
		mmap.put("groupId", groupId);
		mmap.put("homework", homework);
		if (answerId != null) {
			HomeworkAnswer answer = homeworkAnswerService.selectHomeworkAnswerById(answerId);
			mmap.put("answer", answer);
			if (answer!=null){
				return "course/hanswer/info";
			}
		}
		return "course/hanswer/add";
	}

	/**
	 * 查询作业管理列表
	 */
	@ApiOperation("作业管理列表查询")
	@RequiresPermissions("course:homework:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Homework homework) {
		startPage();
		List<Homework> list = homeworkService.selectHomeworkList(homework);
		return getDataTable(list);
	}

	/**
	 * 查询作业管理列表
	 */
	@ApiOperation("作业管理列表查询")
	@RequiresPermissions("course:homework:list")
	@PostMapping("/listForMe")
	@ResponseBody
	public TableDataInfo listForMe(MyHomework homework) {
		startPage();
		List<MyHomework> list = homeworkService.selectMyHomeworks(homework);
		return getDataTable(list);
	}

	/**
	 * 导出作业管理列表
	 */
	@ApiOperation("作业管理列表导出")
	@RequiresPermissions("course:homework:export")
	@Log(title = "作业管理", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Homework homework) {
		List<Homework> list = homeworkService.selectHomeworkList(homework);
		ExcelUtil<Homework> util = new ExcelUtil<Homework>(Homework.class);
		return util.exportExcel(list, "homework");
	}

	/**
	 * 新增作业管理
	 */
	@ApiOperation("作业管理新增界面")
	@GetMapping("/add")
	public String add(Long courseId, Long chapterId, ModelMap mmap) {
		mmap.put("courseId", courseId);
		mmap.put("chapterId", chapterId);
		return prefix + "/add";
	}

	/**
	 * 新增保存作业管理
	 */
	@ApiOperation("新增保存作业管理")
	@RequiresPermissions("course:homework:add")
	@Log(title = "作业管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Homework homework) {
		return toAjax(homeworkService.insertHomework(homework));
	}

	/**
	 * 修改作业管理
	 */
	@ApiOperation("作业管理编辑界面")
	@GetMapping("/edit/{homeworkId}")
	public String edit(@PathVariable("homeworkId") Long homeworkId, ModelMap mmap) {
		Homework homework = homeworkService.selectHomeworkById(homeworkId);
		mmap.put("homework", homework);
		List<CourseGroup> courseGroups = courseGroupService.groupsDict(homework.getCourseId());
		mmap.put("groups", CollectionUtils.isEmpty(courseGroups) ? "[]" : JSON.toJSONString(courseGroups));

		return prefix + "/edit";
	}

	/**
	 * 修改保存作业管理
	 */
	@ApiOperation("修改保存作业管理")
	@RequiresPermissions("course:homework:edit")
	@Log(title = "作业管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Homework homework) {
		return toAjax(homeworkService.updateHomework(homework));
	}

	/**
	 * 删除作业管理
	 */
	@ApiOperation("删除作业管理数据")
	@RequiresPermissions("course:homework:remove")
	@Log(title = "作业管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(homeworkService.deleteHomeworkByIds(ids));
	}

	/**
	 * 作业班级列表
	 */
	@ApiOperation("作业班级列表")
	@RequiresPermissions("course:homework:view")
	@GetMapping("/homeworkGroups")
	@ResponseBody
	public AjaxResult homeworkGroups(HomeworkGroupQuery queryVo) {
		AjaxResult ajax = new AjaxResult();
		ajax.put("code", 200);
		ajax.put("value", courseGroupService.homeworkGroups(queryVo));
		return ajax;
	}
}
