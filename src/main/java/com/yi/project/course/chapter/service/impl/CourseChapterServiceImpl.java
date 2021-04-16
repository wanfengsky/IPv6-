package com.yi.project.course.chapter.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yi.common.constant.Constants;
import com.yi.common.constant.UserConstants;
import com.yi.common.utils.DateUtils;
import com.yi.common.utils.StringUtils;
import com.yi.common.utils.security.ShiroUtils;
import com.yi.framework.web.domain.Ztree;
import com.yi.project.course.chapter.service.ChaptersMap;
import com.yi.project.course.course.domain.Course;
import com.yi.project.course.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yi.project.course.chapter.mapper.CourseChapterMapper;
import com.yi.project.course.chapter.domain.CourseChapter;
import com.yi.project.course.chapter.service.ICourseChapterService;
import com.yi.common.utils.text.Convert;

/**
 * 课程章节Service业务层处理
 * 
 * @author yi
 * @date 2020-12-03
 */
@Service("chapter")
public class CourseChapterServiceImpl implements ICourseChapterService {
    @Autowired
    private CourseChapterMapper courseChapterMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ChaptersMap chaptersMap;

    /**
     * 查询课程章节
     * 
     * @param chapterId 课程章节ID
     * @return 课程章节
     */
    @Override
    public CourseChapter selectCourseChapterById(Long chapterId) {
        return courseChapterMapper.selectCourseChapterById(chapterId);
    }

    /**
     * 查询课程章节列表
     * 
     * @param courseChapter 课程章节
     * @return 课程章节
     */
    @Override
    public List<CourseChapter> selectCourseChapterList(CourseChapter courseChapter) {
        courseChapter.setDelFlag("0");
        return courseChapterMapper.selectCourseChapterList(courseChapter);
    }

    /**
     * 新增课程章节
     * 
     * @param courseChapter 课程章节
     * @return 结果
     */
    @Override
    public int insertCourseChapter(CourseChapter courseChapter) {
        //1.获取当前用户id
        Long userID = ShiroUtils.getUserId();
        courseChapter.setCreateBy(userID.toString());

        courseChapter.setCreateTime(DateUtils.getNowDate());
        int result = courseChapterMapper.insertCourseChapter(courseChapter);
        chaptersMap.refresh(courseChapter);
        return (int)result;
    }

    /**
     * 修改课程章节
     * 
     * @param courseChapter 课程章节
     * @return 结果
     */
    @Override
    public int updateCourseChapter(CourseChapter courseChapter) {
        chaptersMap.refresh(courseChapter);
        return courseChapterMapper.updateCourseChapter(courseChapter);
    }

    /**
     * 删除课程章节对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCourseChapterByIds(String ids) {
        Long[] idArr = Convert.toLongArray(ids);
        chaptersMap.flush(idArr);
        return courseChapterMapper.deleteFlagByIds(idArr);
    }

    /**
     * 删除课程章节信息
     * 
     * @param chapterId 课程章节ID
     * @return 结果
     */
    @Override
    public int deleteCourseChapterById(Long chapterId) {
        chaptersMap.flush(chapterId);
        return courseChapterMapper.deleteCourseChapterById(chapterId);
    }
    @Override
    public List<CourseChapter> chapterDict(Long courseId) {
        return chapterDict((Object) courseId);
    }
    /**
     *  章节字典
     * @param courseId 课程ID
     * @return
     */
    @Override
    public List<CourseChapter> chapterDict(Object courseId) {
        CourseChapter query = new CourseChapter();
        if(courseId!=null){
            if(courseId instanceof Number){
                query.setCourseId(((Number) courseId).longValue());
            } else {
                query.setCourseId(Long.parseLong(courseId.toString()));
            }
        } else{
            query.setCourseId(0L);
        }
        query.setDelFlag("0");
        return courseChapterMapper.selectCourseChapterList(query);
    }

    /**
     * 对象转章节树
     *
     * @param courseChapterList 章节列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<CourseChapter> courseChapterList)
    {
        return initZtree(courseChapterList, null);
    }

    /**
     * 对象转章节树
     *
     * @param courseChapterList 章节列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<CourseChapter> courseChapterList, List<String> roleDeptList)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();

        for (CourseChapter courseChapter : courseChapterList)
        {
            //1.添加章节节点
            Ztree ztree = new Ztree();
            ztree.setId(courseChapter.getChapterId());
            ztree.setpId(courseChapter.getCourseId() +Constants.TREE_ID_PREFIX);
            ztree.setName(courseChapter.getChapterName());
            ztree.setTitle(courseChapter.getChapterName());
            ztrees.add(ztree);
            //2.添加章节子节点 课件、讲义、视频、作业、其他
            Ztree ztree1 = new Ztree();
            ztree1.setpId(courseChapter.getChapterId());
            ztree1.setId((long)1+Constants.TREE_LEFT_ID_PREFIX);
            ztree1.setName("课件");
            ztree1.setTitle(Constants.BUSINZ_TAG_PPT);
            ztrees.add(ztree1);

            Ztree ztree2 = new Ztree();
            ztree2.setpId(courseChapter.getChapterId());
            ztree2.setName("讲义");
            ztree2.setTitle(Constants.BUSINZ_TAG_NOTE);
            ztree2.setId((long)2+Constants.TREE_LEFT_ID_PREFIX);
            ztrees.add(ztree2);

            Ztree ztree3 = new Ztree();
            ztree3.setpId(courseChapter.getChapterId());
            ztree3.setName("视频");
            ztree3.setTitle(Constants.BUSINZ_TAG_VIDEO);
            ztree3.setId((long)3+Constants.TREE_LEFT_ID_PREFIX);
            ztrees.add(ztree3);

            Ztree ztree4 = new Ztree();
            ztree4.setpId(courseChapter.getChapterId());
            ztree4.setName("作业");
            ztree4.setTitle(Constants.BUSINZ_TAG_WORK);
            ztree4.setId((long)4+Constants.TREE_LEFT_ID_PREFIX);
            ztrees.add(ztree4);

            Ztree ztree5 = new Ztree();
            ztree5.setpId(courseChapter.getChapterId());
            ztree5.setName("其他");
            ztree5.setTitle(Constants.BUSINZ_TAG_OTHER);
            ztree5.setId((long)5+Constants.TREE_LEFT_ID_PREFIX);
            ztrees.add(ztree5);

        }
        return ztrees;
    }


    public List<Ztree>  selectCourseTree(Long courseId){

        if(courseId == null){
            return null;
        }
        Course course = courseMapper.selectCourseById(courseId);
        List<Ztree> ztrees = new ArrayList<Ztree>();

        //1.获取 课程信息作为 根节点
        Ztree  z = new  Ztree();
        z.setpId(new Long((long)0));
        z.setId(courseId+Constants.TREE_ID_PREFIX);
        z.setName(course.getCourseName());
        z.setTitle(course.getCourseName());
        ztrees.add(z);

        //2.获取章节信息
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setDelFlag("0");
        courseChapter.setCourseId(courseId);
        List<CourseChapter> courseChapterList = courseChapterMapper.selectCourseChapterList(courseChapter);
        List<Ztree> zt = initZtree(courseChapterList);

        //3.合并
        ztrees.addAll(zt);
        return ztrees;
    }
}
