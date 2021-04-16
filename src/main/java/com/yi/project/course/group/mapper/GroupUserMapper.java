package com.yi.project.course.group.mapper;



import com.yi.project.course.group.domain.GroupUserQuery;
import com.yi.project.course.group.domain.GroupUserVo;

import java.util.List;

/**
 * 班级学生Mapper接口
 *
 * @author yi
 * @date 2020-12-05
 */
public interface GroupUserMapper {
	/**
	 * 查询班级学生
	 *
	 * @param groupUserId 班级学生ID
	 * @return 班级学生
	 */
	GroupUserVo selectGroupUserById(Long groupUserId);

	/**
	 * 查询班级学生列表
	 *
	 * @param groupUser 班级学生
	 * @return 班级学生集合
	 */
	List<GroupUserVo> selectGroupUserList(GroupUserQuery groupUser);

	/**
	 * 新增班级学生
	 *
	 * @param groupUser 班级学生
	 * @return 结果
	 */
	int insertGroupUsers(List<GroupUserQuery> groupUser);

	/**
	 * 批量删除班级学生
	 *
	 * @param groupUserIds 需要删除的数据ID
	 * @return 结果
	 */
	int deleteGroupUserByIds(String[] groupUserIds);

	/**
	 *根据学生id，获取组ids
	 * @param userID
	 * @return
	 */
	List<Long> selectGroupsByUserId(Long userID);
}
