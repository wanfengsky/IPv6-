package com.yi.project.course.chapter.service;

import java.util.List;

import com.yi.framework.web.domain.Ztree;
import com.yi.project.course.chapter.domain.CourseChapter;

/**
 * 课程章节Service接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface ICourseChapterService {
    /**
     * 查询课程章节
     * 
     * @param chapterId 课程章节ID
     * @return 课程章节
     */
    public CourseChapter selectCourseChapterById(Long chapterId);

    /**
     * 查询课程章节列表
     * 
     * @param courseChapter 课程章节
     * @return 课程章节集合
     */
    public List<CourseChapter> selectCourseChapterList(CourseChapter courseChapter);

    /**
     * 新增课程章节
     * 
     * @param courseChapter 课程章节
     * @return 结果
     */
    public int insertCourseChapter(CourseChapter courseChapter);

    /**
     * 修改课程章节
     * 
     * @param courseChapter 课程章节
     * @return 结果
     */
    public int updateCourseChapter(CourseChapter courseChapter);

    /**
     * 批量删除课程章节
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseChapterByIds(String ids);

    /**
     * 删除课程章节信息
     * 
     * @param chapterId 课程章节ID
     * @return 结果
     */
    public int deleteCourseChapterById(Long chapterId);

	List<CourseChapter> chapterDict(Object courseId);
    List<CourseChapter> chapterDict(Long courseId);
	/**
     * 查询课程章节管理树
     *
     * @param courseId 课程id
     * @return 所有课程章节信息
     */
    public List<Ztree> selectCourseTree(Long courseId);
}
