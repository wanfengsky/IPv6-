package com.yi.project.course.chapter.mapper;

import java.util.List;
import com.yi.project.course.chapter.domain.CourseChapter;

/**
 * 课程章节Mapper接口
 * 
 * @author yi
 * @date 2020-12-03
 */
public interface CourseChapterMapper {
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
     * 删除课程章节
     * 
     * @param chapterId 课程章节ID
     * @return 结果
     */
    public int deleteCourseChapterById(Long chapterId);

    /**
     * 批量删除课程章节
     * 
     * @param chapterIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseChapterByIds(Long[] chapterIds);

    public int deleteFlagByIds(Long[] chapterIds);
}
