package com.yi.project.course.topic.controller;

import java.util.List;

import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.service.ICourseService;
import com.yi.project.course.topic.domain.TopicVo;
import com.yi.project.system.user.domain.User;
import com.yi.project.system.user.service.IUserService;
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
import com.yi.project.course.topic.domain.Topic;
import com.yi.project.course.topic.service.ITopicService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生话题Controller
 * 
 * @author yi
 * @date 2020-12-05
 */
@Api("学生话题管理")
@Controller
@RequestMapping("/course/topic")
public class TopicController extends BaseController {
    private String prefix = "course/topic";

    @Autowired
    private ITopicService topicService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IUserService userService;

	@ApiOperation("学生话题管理页")
    @RequiresPermissions("course:topic:view")
    @GetMapping()
    public String topic() {
        return prefix + "/topic";
    }

    /**
     * 查询学生话题列表
     */
	@ApiOperation("学生话题列表查询")
    @RequiresPermissions("course:topic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Topic topic) {
        startPage();
        //1. 设置话题为主话题
        topic.setTopicPid((long)0);
        List<TopicVo> list = topicService.selectTopicList(topic);
        return getDataTable(list);
    }
    /**
     * 查询学生话题回复列表
     */
    @ApiOperation("学生话题列表查询")
    @RequiresPermissions("course:topic:list")
    @PostMapping("/replyList")
    @ResponseBody
    public TableDataInfo replyList(Topic topic) {
        startPage();
        List<TopicVo> list = topicService.selectTopicList(topic);
        return getDataTable(list);
    }
    /**
     * 导出学生话题列表
     */
	@ApiOperation("学生话题列表导出")
    @RequiresPermissions("course:topic:export")
    @Log(title = "学生话题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Topic topic) {
        List<TopicVo> list = topicService.selectTopicList(topic);
        ExcelUtil<TopicVo> util = new ExcelUtil<TopicVo>(TopicVo.class);
        return util.exportExcel(list, "topic");
    }

    /**
     * 新增学生话题
     */
	@ApiOperation("学生话题新增界面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存学生话题
     */
	@ApiOperation("新增保存学生话题")
    @RequiresPermissions("course:topic:add")
    @Log(title = "学生话题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Topic topic) {

	    return toAjax(topicService.insertTopic(topic));
    }

    /**
     * 修改学生话题
     */
	@ApiOperation("学生话题编辑界面")
    @GetMapping("/edit/{topicId}")
    public String edit(@PathVariable("topicId") Long topicId, ModelMap mmap) {
        TopicVo topic = topicService.selectTopicById(topicId);
        Course course = courseService.selectCourseById(topic.getCourseId());
        mmap.put("topic", topic);
        mmap.put("course", course);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生话题
     */
	@ApiOperation("修改保存学生话题")
    @RequiresPermissions("course:topic:edit")
    @Log(title = "学生话题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Topic topic) {
        return toAjax(topicService.updateTopic(topic));
    }

    /**
     * 删除学生话题
     */
	@ApiOperation("删除学生话题数据")
    @RequiresPermissions("course:topic:remove")
    @Log(title = "学生话题", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(topicService.deleteTopicByIds(ids));
    }

    /**
     * 网络答疑下的课程组列表
     */
    @ApiOperation("网络答疑下的课程组列表")
    @RequiresPermissions("course:topic:list")
    @GetMapping("/courseDict")
    @ResponseBody
    public AjaxResult courseDict() {

        //获取当前用户信息
        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);




        //获取字典数据
        ajax.put("value", topicService.topicCourseDict());
        return ajax;
    }


    @ApiOperation("通用视图查看")
    @RequiresPermissions("course:topic:view")
    @GetMapping("/view/{viewName}")
    public String groupView(@PathVariable("viewName") String viewName, ModelMap mmap, HttpServletRequest request) {
        request.getParameterMap().forEach((k, v) -> {
            mmap.put(k, v != null && v.length == 1 ? v[0] : v);
        });

        TopicVo topic = topicService.selectTopicById(Long.parseLong(request.getParameter("topicId")));
        mmap.put("topic", topic);

        return prefix + "/" + viewName;
    }
}
