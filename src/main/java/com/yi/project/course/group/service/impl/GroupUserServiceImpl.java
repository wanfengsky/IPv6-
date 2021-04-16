package com.yi.project.course.group.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.project.course.group.domain.GroupUserVo;
import com.yi.project.system.dept.domain.Dept;
import com.yi.project.system.dept.service.IDeptService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.course.group.mapper.GroupUserMapper;
import com.yi.project.course.group.domain.GroupUserQuery;
import com.yi.project.course.group.service.IGroupUserService;
import com.yi.common.utils.text.Convert;

/**
 * 班级学生Service业务层处理
 * 
 * @author yi
 * @date 2020-12-05
 */
@Service
public class GroupUserServiceImpl implements IGroupUserService {
    @Autowired
    private GroupUserMapper groupUserMapper;
    @Autowired
    private IDeptService deptService;

    /**
     * 查询班级学生
     * 
     * @param groupUserId 班级学生ID
     * @return 班级学生
     */
    @Override
    public GroupUserVo selectGroupUserById(Long groupUserId) {
        return groupUserMapper.selectGroupUserById(groupUserId);
    }

    /**
     * 查询班级学生列表
     * 
     * @param groupUser 班级学生
     * @return 班级学生
     */
    @Override
    public List<GroupUserVo> selectGroupUserList(GroupUserQuery groupUser) {
        //1. 如果按班级查找
        if(StringUtils.isNotBlank(groupUser.getDeptName())){
            List<GroupUserVo> groupUserVos= new ArrayList<GroupUserVo>();
            //2. 获取班级信息
            Dept dept = new Dept();
            dept.setDeptName(groupUser.getDeptName());
            List<Dept> depts = deptService.selectDeptList(dept);

            if(CollectionUtils.isNotEmpty(depts)){
                //3. 循环查找
                for(Dept d : depts) {
                    groupUser.setDeptId(d.getDeptId());
                    groupUser.setNotIn(true);
                    List<GroupUserVo> groupUserVoList = groupUserMapper.selectGroupUserList(groupUser);
                    groupUserVos.addAll(groupUserVoList);
                }


                return groupUserVos;
            }


        }

        return groupUserMapper.selectGroupUserList(groupUser);

    }

    /**
     * 新增班级学生
     * 
     * @param groupUsers 班级学生
     * @return 结果
     */
    @Override
    public int insertGroupUsers(List<GroupUserQuery> groupUsers) {
        groupUsers.forEach(e -> {
            e.setCreateBy(ShiroUtils.getCurrUserId());
            e.setCreateTime(DateUtils.getNowDate());
        });
        return groupUserMapper.insertGroupUsers(groupUsers);
    }

    /**
     * 删除班级学生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGroupUserByIds(String ids) {
        return groupUserMapper.deleteGroupUserByIds(Convert.toStrArray(ids));
    }

    /**
     *根据学生id，获取组ids
     * @param userID
     * @return
     */
    public List<Long> selectGroupsByUserId(Long userID){
        return groupUserMapper.selectGroupsByUserId(userID);
    }
}
