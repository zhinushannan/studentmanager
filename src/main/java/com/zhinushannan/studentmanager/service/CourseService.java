package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/25 17:52
 * @subject
 */
@Service
public interface CourseService {

    /**
     * 根据老师的id查询课程信息
     * @param teacherId 老师的id
     * @return 返回课程信息的集合
     */
    List<Course> queryCourseByTeacherId(Integer teacherId);

}
