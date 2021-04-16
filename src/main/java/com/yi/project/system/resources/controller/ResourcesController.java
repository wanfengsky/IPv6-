package com.yi.project.system.resources.controller;

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
import com.yi.project.system.resources.domain.Resources;
import com.yi.project.system.resources.service.IResourcesService;
import com.yi.framework.web.controller.BaseController;
import com.yi.framework.web.domain.AjaxResult;
import com.yi.common.utils.poi.ExcelUtil;
import com.yi.framework.web.page.TableDataInfo;

/**
 * 资源管理Controller
 * 
 * @author yi
 * @date 2020-12-05
 */
@Api("资源管理管理")
@Controller
@RequestMapping("/system/resources")
public class ResourcesController extends BaseController {
    private String prefix = "system/resources";

    @Autowired
    private IResourcesService resourcesService;

	@ApiOperation("资源管理管理页")
    @RequiresPermissions("system:resources:view")
    @GetMapping()
    public String resources() {
        return prefix + "/resources";
    }

    /**
     * 查询资源管理列表
     */
	@ApiOperation("资源管理列表查询")
    @RequiresPermissions("system:resources:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Resources resources) {
        startPage();
        resources.setDelFlag("0");
        List<Resources> list = resourcesService.selectResourcesList(resources);
        return getDataTable(list);
    }

    /**
     * 在章节中查询资源管理列表
     */
    @ApiOperation("资源管理管理页")
    @RequiresPermissions("system:resources:view")
    @GetMapping("/resourcesList")
    public String resourcesList(String businzType,String businzTag,Long businzId, ModelMap mmap) {
        mmap.put("businzType", businzType);
        mmap.put("businzTag", businzTag);
        mmap.put("businzId", businzId);
        return prefix + "/resourcesList";
    }

    /**
     * 导出资源管理列表
     */
	@ApiOperation("资源管理列表导出")
    @RequiresPermissions("system:resources:export")
    @Log(title = "资源管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Resources resources) {
        List<Resources> list = resourcesService.selectResourcesList(resources);
        ExcelUtil<Resources> util = new ExcelUtil<Resources>(Resources.class);
        return util.exportExcel(list, "resources");
    }

    /**
     * 新增资源管理
     */
	@ApiOperation("资源管理新增界面")
    @GetMapping("/add")
    public String add(String businzType,String businzTag,Long businzId, ModelMap mmap) {
        mmap.put("businzType", businzType);
        mmap.put("businzTag", businzTag);
        mmap.put("businzId", businzId);
        return prefix + "/add";
    }

    /**
     * 新增保存资源管理
     */
	@ApiOperation("新增保存资源管理")
    @RequiresPermissions("system:resources:add")
    @Log(title = "资源管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Resources resources) {

	    return toAjax(resourcesService.insertResources(resources));
    }

    /**
     * 修改资源管理
     */
	@ApiOperation("资源管理编辑界面")
    @GetMapping("/edit/{resourceId}")
    public String edit(@PathVariable("resourceId") Long resourceId, ModelMap mmap) {
        Resources resources = resourcesService.selectResourcesById(resourceId);
        mmap.put("resources", resources);
        return prefix + "/edit";
    }

    /**
     * 查看资源
     */
    @ApiOperation("查看资源界面")
    @GetMapping("/view")
    public String view(Long resourceId, ModelMap mmap) {
        Resources resources = resourcesService.selectResourcesById(resourceId);
        mmap.put("resources", resources);
        return prefix + "/resourcesView";
    }


    /**
     * 修改保存资源管理
     */
	@ApiOperation("修改保存资源管理")
    @RequiresPermissions("system:resources:edit")
    @Log(title = "资源管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Resources resources) {
        return toAjax(resourcesService.updateResources(resources));
    }

    /**
     * 删除资源管理
     */
	@ApiOperation("删除资源管理数据")
    @RequiresPermissions("system:resources:remove")
    @Log(title = "资源管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(resourcesService.deleteResourcesByIds(ids));
    }
}
