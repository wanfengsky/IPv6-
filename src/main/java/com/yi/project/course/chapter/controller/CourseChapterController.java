package com.yi.project.course.chapter.controller;

import java.util.List;

import com.yi.common.utils.security.ShiroUtils;
import com.yi.framework.web.domain.Ztree;
import com.yi.project.system.dept.domain.Dept;
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
import com.yi.project.course.chapter.domain.CourseChapter;
import com.yi.project.course.chapter.service.ICourseChapterService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

/**
 * 课程章节Controller
 * 
 * @author yi
 * @date 2020-12-03
 */
@Api("课程章节管理")
@Controller
@RequestMapping("/course/chapter")
public class CourseChapterController extends BaseController {
    private String prefix = "course/chapter";

    @Autowired
    private ICourseChapterService courseChapterService;

	@ApiOperation("课程章节管理页")
    @RequiresPermissions("course:chapter:view")
    @GetMapping()
    public String chapter() {
        return prefix + "/chapter";
    }

    /**
     * 查询课程章节列表
     */
	@ApiOperation("课程章节列表查询")
    @RequiresPermissions("course:chapter:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CourseChapter courseChapter) {
        startPage();
        List<CourseChapter> list = courseChapterService.selectCourseChapterList(courseChapter);
        return getDataTable(list);
    }


    /**
     * 查询课程章节列表
     */
    @ApiOperation("课程章节列表查询")
    @RequiresPermissions("course:chapter:list")
    @GetMapping("/chapterList")
    public String chapterList(Long courseId, ModelMap mmap) {
        mmap.put("courseId", courseId);
        return prefix + "/chapterList";
    }

    /**
     * 查询课程章节列表
     */
    @ApiOperation("课程章节列表查询")
    @RequiresPermissions("course:chapter:list")
    @GetMapping("/chapterCenter")
    public String chapterCenter(Long courseId,Long chapterId, ModelMap mmap) {
        mmap.put("chapterId", chapterId);
        mmap.put("courseId", courseId);
        return prefix + "/chapterCenter";
    }



    /**
     * 导出课程章节列表
     */
	@ApiOperation("课程章节列表导出")
    @RequiresPermissions("course:chapter:export")
    @Log(title = "课程章节", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CourseChapter courseChapter) {
        List<CourseChapter> list = courseChapterService.selectCourseChapterList(courseChapter);
        ExcelUtil<CourseChapter> util = new ExcelUtil<CourseChapter>(CourseChapter.class);
        return util.exportExcel(list, "chapter");
    }

    /**
     * 新增课程章节
     */
	@ApiOperation("课程章节新增界面")
    @GetMapping("/add")
    public String add(Long courseId, ModelMap mmap) {
        mmap.put("courseId", courseId);
	    return prefix + "/add";
    }

    /**
     * 新增保存课程章节
     */
	@ApiOperation("新增保存课程章节")
    @RequiresPermissions("course:chapter:add")
    @Log(title = "课程章节", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CourseChapter courseChapter) {

        return toAjax(courseChapterService.insertCourseChapter(courseChapter));
    }

    /**
     * 修改课程章节
     */
	@ApiOperation("课程章节编辑界面")
    @GetMapping("/edit/{chapterId}")
    public String edit(@PathVariable("chapterId") Long chapterId, ModelMap mmap) {
        CourseChapter courseChapter = courseChapterService.selectCourseChapterById(chapterId);
        mmap.put("courseChapter", courseChapter);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程章节
     */
	@ApiOperation("修改保存课程章节")
    @RequiresPermissions("course:chapter:edit")
    @Log(title = "课程章节", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CourseChapter courseChapter) {
        return toAjax(courseChapterService.updateCourseChapter(courseChapter));
    }

    /**
     * 删除课程章节
     */
	@ApiOperation("删除课程章节数据")
    @RequiresPermissions("course:chapter:remove")
    @Log(title = "课程章节", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(courseChapterService.deleteCourseChapterByIds(ids));
    }

    /**
     * 课程章节字典
     */
    @ApiOperation("课程章节字典")
    @RequiresPermissions("course:chapter:view")
    @GetMapping("/dict/{courseId}")
    @ResponseBody
    public AjaxResult chapterDict(@PathVariable Long courseId) {
        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);
        ajax.put("value", courseChapterService.chapterDict(courseId));
        return ajax;
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData(Long courseId)
    {

        List<Ztree> ztrees = courseChapterService.selectCourseTree(courseId);
        return ztrees;
    }
}
