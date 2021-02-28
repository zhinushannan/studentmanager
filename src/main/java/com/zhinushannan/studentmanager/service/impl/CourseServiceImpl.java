package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.CourseDao;
import com.zhinushannan.studentmanager.dao.TimeTableDao;
import com.zhinushannan.studentmanager.dataobject.Course;
import com.zhinushannan.studentmanager.dataobject.TimeTable;
import com.zhinushannan.studentmanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhinushannan
 * @date 2020/12/25 17:53
 * @subject
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired(required = false)
    private CourseDao courseDao;

    @Autowired(required = false)
    private TimeTableDao timeTableDao;

    /**
     * 根据老师的id查询课程信息
     *
     * @param teacherId 老师的id
     * @return 返回课程信息的集合
     */
    @Override
    public List<Course> queryCourseByTeacherId(Integer teacherId) {
        List<TimeTable> timeTables = this.timeTableDao.selectTimeTablesByTeacher(teacherId);
        Set<Course>courses = new HashSet<>();
        for (TimeTable timeTable : timeTables) {
            courses.add(this.courseDao.selectCourseByCourseId(timeTable.getCourseId()));
        }
        List<Course>result = new ArrayList<>();
        for (Course course : courses) {
            result.add(course);
        }
        return result;
    }
}
