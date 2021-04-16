package com.yi.project.course.signrecord.controller;

import java.util.List;

import com.yi.project.course.signin.service.ICourseSigninService;
import com.yi.project.course.signrecord.domain.CourseSigninRecordVo;
import com.yi.project.course.topic.domain.TopicVo;
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
import com.yi.project.course.signrecord.domain.CourseSigninRecord;
import com.yi.project.course.signrecord.service.ICourseSigninRecordService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生签到记录Controller
 * 
 * @author yi
 * @date 2020-12-03
 */
@Api("学生签到记录管理")
@Controller
@RequestMapping("/course/signrecord")
public class CourseSigninRecordController extends BaseController {
    private String prefix = "course/signrecord";

    @Autowired
    private ICourseSigninRecordService courseSigninRecordService;
    @Autowired
    private ICourseSigninService courseSigninService;
	@ApiOperation("学生签到记录管理页")
    @RequiresPermissions("course:signrecord:view")
    @GetMapping()
    public String signrecord() {
        return prefix + "/signrecord";
    }

    /**
     * 查询学生签到记录列表
     */
	@ApiOperation("学生签到记录列表查询")
    @RequiresPermissions("course:signrecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CourseSigninRecord courseSigninRecord,Long courseId) {
        startPage();
        List<CourseSigninRecordVo> list = courseSigninRecordService.selectCourseSigninRecordList(courseSigninRecord,courseId);
        return getDataTable(list);
    }

    /**
     * 导出学生签到记录列表
     */
	@ApiOperation("学生签到记录列表导出")
    @RequiresPermissions("course:signrecord:export")
    @Log(title = "学生签到记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CourseSigninRecord courseSigninRecord) {
        List<CourseSigninRecordVo> list = courseSigninRecordService.selectCourseSigninRecordList(courseSigninRecord,null);
        ExcelUtil<CourseSigninRecordVo> util = new ExcelUtil<CourseSigninRecordVo>(CourseSigninRecordVo.class);
        return util.exportExcel(list, "signrecord");
    }

    /**
     * 新增学生签到记录
     */
	@ApiOperation("学生签到记录新增界面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存学生签到记录
     */
	@ApiOperation("新增保存学生签到记录")
    @RequiresPermissions("course:signrecord:add")
    @Log(title = "学生签到记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CourseSigninRecord courseSigninRecord) {
        return toAjax(courseSigninRecordService.insertCourseSigninRecord(courseSigninRecord));
    }

    /**
     * 首页学生签到，根据时间，签到符合时间的签到表，返回签到表信息
     */
    @ApiOperation("保存学生签到记录")
    @RequiresPermissions("course:signrecord:add")
    @Log(title = "学生签到记录", businessType = BusinessType.INSERT)
    @PostMapping("/signin")
    @ResponseBody
    public AjaxResult signin() {
        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);

        //获取签到信息
        ajax.put("value", courseSigninRecordService.insertCourseSigninRecord());
        return ajax;
    }

    /**
     * 修改学生签到记录
     */
	@ApiOperation("学生签到记录编辑界面")
    @GetMapping("/edit/{recordId}")
    public String edit(@PathVariable("recordId") Long recordId, ModelMap mmap) {
        CourseSigninRecordVo courseSigninRecord = courseSigninRecordService.selectCourseSigninRecordById(recordId);
        mmap.put("courseSigninRecord", courseSigninRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生签到记录
     */
	@ApiOperation("修改保存学生签到记录")
    @RequiresPermissions("course:signrecord:edit")
    @Log(title = "学生签到记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CourseSigninRecord courseSigninRecord) {
        return toAjax(courseSigninRecordService.updateCourseSigninRecord(courseSigninRecord));
    }

    /**
     * 删除学生签到记录
     */
	@ApiOperation("删除学生签到记录数据")
    @RequiresPermissions("course:signrecord:remove")
    @Log(title = "学生签到记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(courseSigninRecordService.deleteCourseSigninRecordByIds(ids));
    }

    /**
     * 根据课程id
     * 获取课程签到表中的可在当前时段进行签到的  签到名
     */
    @ApiOperation("网络答疑下的课程组列表")
    @RequiresPermissions("course:signrecord:list")
    @GetMapping("/getSignin")
    @ResponseBody
    public AjaxResult getSignin(Long courseId) {

        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);

        //获取签到信息
        ajax.put("value", courseSigninService.getSigninByCourseId(courseId,true));
        return ajax;
    }

    /**
     *
     * 获取学生的签到表
     */
    @ApiOperation("网络答疑下的课程组列表")
    @RequiresPermissions("course:signrecord:list")
    @GetMapping("/getSigninInfo")
    @ResponseBody
    public AjaxResult getSigninInfo() {

        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);

        //获取签到信息
        ajax.put("value", courseSigninRecordService.getSigninInfo());
        return ajax;
    }

    /**
     * 根据课程id
     * 获取课程签到表中的可在当前时段进行签到的  签到名
     */
    @ApiOperation("签到列表")
    @RequiresPermissions("course:signrecord:list")
    @GetMapping("/getSigninList")
    @ResponseBody
    public AjaxResult getSigninList(Long courseId) {

        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);

        //获取签到信息
        ajax.put("value", courseSigninService.getSigninByCourseId(courseId,false));
        return ajax;
    }

    @ApiOperation("通用视图查看")
    @RequiresPermissions("course:signrecord:view")
    @GetMapping("/view/{viewName}")
    public String groupView(@PathVariable("viewName") String viewName, ModelMap mmap, HttpServletRequest request) {
        request.getParameterMap().forEach((k, v) -> {
            mmap.put(k, v != null && v.length == 1 ? v[0] : v);
        });


        return prefix + "/" + viewName;
    }
}
