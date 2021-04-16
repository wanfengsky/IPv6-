package com.yi.project.course.course.controller;

import java.util.List;

import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.service.ICourseService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author yi
 * @date 2020-12-03
 */
@Api("课程管理")
@Controller
@RequestMapping("/course/course")
public class CourseController extends BaseController {
    private String prefix = "course/course";

    @Autowired
    private ICourseService courseService;

	@ApiOperation("课程管理页")
    @RequiresPermissions("course:course:view")
    @GetMapping()
    public String course() {
        return prefix + "/course";
    }

    @ApiOperation("课程管理页")
    @RequiresPermissions("course:course:view")
    @GetMapping("courseList")
    public String courseList() {
        return prefix + "/courseList";
    }

    @ApiOperation("课程详情页")
    @RequiresPermissions("course:course:view")
    @GetMapping("/view/{courseId}")
    public String view(@PathVariable("courseId") Long courseId, ModelMap mmap) {
        Course course = courseService.selectCourseById(courseId);
        mmap.put("course", course);
        return prefix + "/view";
    }
    /**
     * 查询课程列表
     */
	@ApiOperation("课程列表查询")
    @RequiresPermissions("course:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Course course) {
        startPage();


        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 根据课程id获取课程名
     */
    @ApiOperation("课程列表查询")
    @RequiresPermissions("course:course:list")
    @GetMapping("/getName")
    @ResponseBody
    public AjaxResult getName(Long courseId) {

        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);
        ajax.put("value", courseService.selectCourseById(courseId).getCourseName());
        return ajax;

    }

    /**
     * 导出课程列表
     */
	@ApiOperation("课程列表导出")
    @RequiresPermissions("course:course:export")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Course course) {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        return util.exportExcel(list, "course");
    }

    /**
     * 新增课程
     */
	@ApiOperation("课程新增界面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存课程
     */
	@ApiOperation("新增保存课程")
    @RequiresPermissions("course:course:add")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Course course) {


	    return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程
     */
	@ApiOperation("课程编辑界面")
    @GetMapping("/edit/{courseId}")
    public String edit(@PathVariable("courseId") Long courseId, ModelMap mmap) {
        Course course = courseService.selectCourseById(courseId);
        mmap.put("course", course);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程
     */
	@ApiOperation("修改保存课程")
    @RequiresPermissions("course:course:edit")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Course course) {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程
     */
	@ApiOperation("删除课程数据")
    @RequiresPermissions("course:course:remove")
    @Log(title = "课程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(courseService.deleteCourseByIds(ids));
    }
}
