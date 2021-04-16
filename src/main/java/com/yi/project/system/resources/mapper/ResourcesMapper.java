package com.yi.project.system.resources.mapper;

import java.util.List;
import com.yi.project.system.resources.domain.Resources;

/**
 * 资源管理Mapper接口
 * 
 * @author yi
 * @date 2020-12-05
 */
public interface ResourcesMapper {
    /**
     * 查询资源管理
     * 
     * @param resourceId 资源管理ID
     * @return 资源管理
     */
    public Resources selectResourcesById(Long resourceId);

    /**
     * 查询资源管理列表
     * 
     * @param resources 资源管理
     * @return 资源管理集合
     */
    public List<Resources> selectResourcesList(Resources resources);

    /**
     * 新增资源管理
     * 
     * @param resources 资源管理
     * @return 结果
     */
    public int insertResources(Resources resources);

    /**
     * 修改资源管理
     * 
     * @param resources 资源管理
     * @return 结果
     */
    public int updateResources(Resources resources);

    /**
     * 删除资源管理
     * 
     * @param resourceId 资源管理ID
     * @return 结果
     */
    public int deleteResourcesById(Long resourceId);

    /**
     * 批量删除资源管理
     * 
     * @param resourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteResourcesByIds(String[] resourceIds);

    public int deleteFlagByIds(String[] resourceIds);

}
