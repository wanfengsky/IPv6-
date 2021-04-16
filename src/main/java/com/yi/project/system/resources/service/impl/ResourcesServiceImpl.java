package com.yi.project.system.resources.service.impl;

import java.util.List;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.system.resources.mapper.ResourcesMapper;
import com.yi.project.system.resources.domain.Resources;
import com.yi.project.system.resources.service.IResourcesService;
import com.yi.common.utils.text.Convert;

/**
 * 资源管理Service业务层处理
 * 
 * @author yi
 * @date 2020-12-05
 */
@Service
public class ResourcesServiceImpl implements IResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;

    /**
     * 查询资源管理
     * 
     * @param resourceId 资源管理ID
     * @return 资源管理
     */
    @Override
    public Resources selectResourcesById(Long resourceId) {
        return resourcesMapper.selectResourcesById(resourceId);
    }

    /**
     * 查询资源管理列表
     * 
     * @param resources 资源管理
     * @return 资源管理
     */
    @Override
    public List<Resources> selectResourcesList(Resources resources) {

        return resourcesMapper.selectResourcesList(resources);
    }

    /**
     * 新增资源管理
     * 
     * @param resources 资源管理
     * @return 结果
     */
    @Override
    public int insertResources(Resources resources) {
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        resources.setCreateBy(userID.toString());
        resources.setCreateTime(DateUtils.getNowDate());
        return resourcesMapper.insertResources(resources);
    }

    /**
     * 修改资源管理
     * 
     * @param resources 资源管理
     * @return 结果
     */
    @Override
    public int updateResources(Resources resources) {
        return resourcesMapper.updateResources(resources);
    }

    /**
     * 删除资源管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteResourcesByIds(String ids) {
        return resourcesMapper.deleteFlagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资源管理信息
     * 
     * @param resourceId 资源管理ID
     * @return 结果
     */
    @Override
    public int deleteResourcesById(Long resourceId) {
        return resourcesMapper.deleteResourcesById(resourceId);
    }
}
