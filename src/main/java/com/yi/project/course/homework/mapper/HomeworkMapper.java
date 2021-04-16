package com.yi.project.course.homework.mapper;

import com.yi.project.course.homework.domain.Homework;
import com.yi.project.course.homework.domain.HomeworkAllocat;
import com.yi.project.course.homework.domain.MyHomework;

import java.util.List;

/**
 * 作业管理Mapper接口
 * 
 * @author yi
 * @date 2020-12-05
 */
public interface HomeworkMapper {
    /**
     * 查询作业管理
     * 
     * @param homeworkId 作业管理ID
     * @return 作业管理
     */
    public Homework selectHomeworkById(Long homeworkId);

    /**
     * 查询作业管理列表
     * 
     * @param homework 作业管理
     * @return 作业管理集合
     */
    public List<Homework> selectHomeworkList(Homework homework);

    /**
     * 查询登录人的作业列表
     *
     * @param homework 作业管理
     * @return 作业管理集合
     */
    public List<MyHomework> selectMyHomeworks(MyHomework homework);

    /**
     * 新增作业管理
     * 
     * @param homework 作业管理
     * @return 结果
     */
    public int insertHomework(Homework homework);

    /**
     * 修改作业管理
     * 
     * @param homework 作业管理
     * @return 结果
     */
    public int updateHomework(Homework homework);

    /**
     * 删除作业管理
     * 
     * @param homeworkId 作业管理ID
     * @return 结果
     */
    public int deleteHomeworkById(Long homeworkId);

    /**
     * 批量删除作业管理
     * 
     * @param homeworkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHomeworkByIds(String[] homeworkIds);

    /**
     * 批量删除作业分配
     * 
     * @param homeworkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHomeworkAllocatByHomeworkIds(String[] homeworkIds);
    
    /**
     * 批量新增作业分配
     * 
     * @param homeworkAllocatList 作业分配列表
     * @return 结果
     */
    public int batchHomeworkAllocat(List<HomeworkAllocat> homeworkAllocatList);
    

    /**
     * 通过作业管理ID删除作业分配信息
     * 
     * @param homeworkId 角色ID
     * @return 结果
     */
    public int deleteHomeworkAllocatByHomeworkId(Long homeworkId);

    /** 更新分配信息 */
    int updateHomeworkAllocat(HomeworkAllocat model);

    /**
     * 通过作业管理ID删除作业分配信息
     *
     * @param homeworkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlagByIds(String[] homeworkIds);

}
