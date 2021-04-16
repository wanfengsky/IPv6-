package com.yi.project.course.signin.controller;

import java.util.ArrayList;
import java.util.List;

import com.yi.project.course.signin.domain.CourseSigninVo;
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
import com.yi.project.course.signin.domain.CourseSignin;
import com.yi.project.course.signin.service.ICourseSigninService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

/**
 * 签到管理Controller
 * 
 * @author yi
 * @date 2020-12-03
 */
@Api("签到管理管理")
@Controller
@RequestMapping("/course/signin")
public class CourseSigninController extends BaseController {
    private String prefix = "course/signin";

    @Autowired
    private ICourseSigninService courseSigninService;

	@ApiOperation("签到管理管理页")
    @RequiresPermissions("course:signin:view")
    @GetMapping()
    public String signin() {
        return prefix + "/signin";
    }

    /**
     * 查询签到管理列表
     */
	@ApiOperation("签到管理列表查询")
    @RequiresPermissions("course:signin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CourseSignin courseSignin) {
        startPage();
        List<CourseSigninVo> list = courseSigninService.selectCourseSigninList(courseSignin);
        if(CollectionUtils.isNotEmpty(list)){
            return getDataTable(list);
        }
        return getDataTable(new ArrayList<CourseSigninVo>()) ;
    }

    /**
     * 获取学生当前最需要的签到表，根据签到表结束时间判断
     * @return
     */
    @ApiOperation("签到管理列表查询")
    @RequiresPermissions("course:signin:list")
    @PostMapping("/getNowSignIn")
    @ResponseBody
    public TableDataInfo getNowSignIn() {
        startPage();
        List<CourseSigninVo> list = courseSigninService.getNowSignInByStudent();
        if(CollectionUtils.isNotEmpty(list)){
            return getDataTable(list);
        }
        return getDataTable(new ArrayList<CourseSigninVo>()) ;
    }



    /**
     * 导出签到管理列表
     */
	@ApiOperation("签到管理列表导出")
    @RequiresPermissions("course:signin:export")
    @Log(title = "签到管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CourseSignin courseSignin) {
        List<CourseSigninVo> list = courseSigninService.selectCourseSigninList(courseSignin);
        ExcelUtil<CourseSigninVo> util = new ExcelUtil<CourseSigninVo>(CourseSigninVo.class);
        return util.exportExcel(list, "signin");
    }

    /**
     * 新增签到管理
     */
	@ApiOperation("签到管理新增界面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存签到管理
     */
	@ApiOperation("新增保存签到管理")
    @RequiresPermissions("course:signin:add")
    @Log(title = "签到管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CourseSignin courseSignin) {
        return toAjax(courseSigninService.insertCourseSignin(courseSignin));
    }

    /**
     * 修改签到管理
     */
	@ApiOperation("签到管理编辑界面")
    @GetMapping("/edit/{signinId}")
    public String edit(@PathVariable("signinId") Long signinId, ModelMap mmap) {
        CourseSigninVo courseSignin = courseSigninService.selectCourseSigninById(signinId);
        mmap.put("courseSignin", courseSignin);
        return prefix + "/edit";
    }

    /**
     * 修改保存签到管理
     */
	@ApiOperation("修改保存签到管理")
    @RequiresPermissions("course:signin:edit")
    @Log(title = "签到管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CourseSignin courseSignin) {
        return toAjax(courseSigninService.updateCourseSignin(courseSignin));
    }

    /**
     * 删除签到管理
     */
	@ApiOperation("删除签到管理数据")
    @RequiresPermissions("course:signin:remove")
    @Log(title = "签到管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(courseSigninService.deleteCourseSigninByIds(ids));
    }
}
