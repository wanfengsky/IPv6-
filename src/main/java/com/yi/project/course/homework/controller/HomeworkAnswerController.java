package com.yi.project.course.homework.controller;

import java.util.List;
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
import com.yi.project.course.homework.domain.HomeworkAnswer;
import com.yi.project.course.homework.service.IHomeworkAnswerService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 作业提交Controller
 * 
 * @author yi
 * @date 2020-12-05
 */
@Api("作业提交管理")
@Controller
@RequestMapping("/course/hanswer")
public class HomeworkAnswerController extends BaseController {
    private String prefix = "course/hanswer";

    @Autowired
    private IHomeworkAnswerService homeworkAnswerService;

	@ApiOperation("作业提交管理页")
    @RequiresPermissions("course:hanswer:view")
    @GetMapping()
    public String hanswer(ModelMap mmap, HttpServletRequest request) {
        request.getParameterMap().forEach((k, v) -> {
            mmap.put(k, v != null && v.length == 1 ? v[0] : v);
        });
        return prefix + "/hanswer";
    }

    @ApiOperation("通用视图查看")
    @RequiresPermissions("course:hanswer:view")
    @GetMapping("/view/{viewName}")
    public String homeworkView(@PathVariable("viewName") String viewName, ModelMap mmap, HttpServletRequest request) {
        request.getParameterMap().forEach((k, v) -> {
            mmap.put(k, v != null && v.length == 1 ? v[0] : v);
        });
        return prefix + "/" + viewName;
    }

    /**
     * 查询作业提交列表
     */
	@ApiOperation("作业提交列表查询")
    @RequiresPermissions("course:hanswer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HomeworkAnswer homeworkAnswer) {
        startPage();
        List<HomeworkAnswer> list = homeworkAnswerService.selectHomeworkAnswerList(homeworkAnswer);
        return getDataTable(list);
    }

    /**
     * 导出作业提交列表
     */
	@ApiOperation("作业提交列表导出")
    @RequiresPermissions("course:hanswer:export")
    @Log(title = "作业提交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HomeworkAnswer homeworkAnswer) {
        List<HomeworkAnswer> list = homeworkAnswerService.selectHomeworkAnswerList(homeworkAnswer);
        ExcelUtil<HomeworkAnswer> util = new ExcelUtil<HomeworkAnswer>(HomeworkAnswer.class);
        return util.exportExcel(list, "hanswer");
    }

    /**
     * 新增作业提交
     */
	@ApiOperation("作业提交新增界面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存作业提交
     */
	@ApiOperation("新增保存作业提交")
    @RequiresPermissions("course:hanswer:add")
    @Log(title = "作业提交", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HomeworkAnswer homeworkAnswer) {
        return toAjax(homeworkAnswerService.insertHomeworkAnswer(homeworkAnswer));
    }

    /**
     * 修改作业提交
     */
	@ApiOperation("作业提交编辑界面")
    @GetMapping("/edit/{answerId}")
    public String edit(@PathVariable("answerId") Long answerId, ModelMap mmap) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerService.selectHomeworkAnswerById(answerId);
        mmap.put("homeworkAnswer", homeworkAnswer);
        return prefix + "/edit";
    }

    /**
     * 修改保存作业提交
     */
	@ApiOperation("修改保存作业提交")
    @RequiresPermissions("course:hanswer:edit")
    @Log(title = "作业提交", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HomeworkAnswer homeworkAnswer) {
        return toAjax(homeworkAnswerService.updateHomeworkAnswer(homeworkAnswer));
    }

    /**
     * 删除作业提交
     */
	@ApiOperation("删除作业提交数据")
    @RequiresPermissions("course:hanswer:remove")
    @Log(title = "作业提交", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(homeworkAnswerService.deleteHomeworkAnswerByIds(ids));
    }
}
