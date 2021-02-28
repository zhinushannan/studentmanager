package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.ClazzDao;
import com.zhinushannan.studentmanager.dao.CourseDao;
import com.zhinushannan.studentmanager.dao.TeacherDao;
import com.zhinushannan.studentmanager.dao.TimeTableDao;
import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.Course;
import com.zhinushannan.studentmanager.dataobject.Teacher;
import com.zhinushannan.studentmanager.dataobject.TimeTable;
import com.zhinushannan.studentmanager.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhinushannan
 * @date 2020/12/17 9:46
 * @subject
 */
@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired(required = false)
    private TimeTableDao timeTableDao;

    @Autowired(required = false)
    private ClazzDao clazzDao;

    @Autowired(required = false)
    private TeacherDao teacherDao;

    @Autowired(required = false)
    private CourseDao courseDao;



    /**
     * 根据班主任编号查询班主任所带班级的课表
     *
     * @param managerId 班主任编号
     * @return 课表（名称）
     */
    @Override
    public Map<Integer, List<String>> queryTimeTableNameByManager(Long managerId) {
        Map<Integer, List<TimeTable>> timetableId = queryTimeTableByManager(managerId);
        Map<Integer, List<String>> timetableName = new HashMap<>();
        Set<Integer> clazzIds = timetableId.keySet();
        for (Integer clazzId : clazzIds) {
            List<TimeTable> timeTables = timetableId.get(clazzId);
            List<String>names = new ArrayList<>();
            for (TimeTable timeTable : timeTables) {
                Course course = this.courseDao.selectCourseByCourseId(timeTable.getCourseId());
                if (null == course) {
                    names.add("无");
                } else {
                    names.add(course.getName());
                }
            }
            timetableName.put(clazzId, names);
        }
        return timetableName;
    }

    /**
     * 根据老师查询查询课表
     *
     * @param teacherId 老师的id
     * @return 老师的课表
     */
    @Override
    public List<TimeTableDetails> queryTimeTableDetailsByTeacher(Integer teacherId) {
        List<TimeTableDetails> timeTableDetails = new ArrayList<>();
        List<TimeTable> timeTables = this.queryTimeTableByTeacher(teacherId);

        for (TimeTable timeTable : timeTables) {
            if (timeTable.getId() == 0) {
                TimeTableDetails timeTableDetails1 = new TimeTableDetails("无", "无", "无", "无");
                timeTableDetails.add(timeTableDetails1);
            } else {
                Clazz clazz = this.clazzDao.selectClazzByClazzId(timeTable.getClazzId());
                Course course = this.courseDao.selectCourseByCourseId(timeTable.getCourseId());
                Teacher teacher = this.teacherDao.selectTeacherByTeacherId(timeTable.getTeacherId());
                TimeTableDetails timeTableDetails1 = new TimeTableDetails(clazz.getName(), course.getName(), teacher.getName(), timeTable.getRoom());
                timeTableDetails.add(timeTableDetails1);
            }

        }
        return timeTableDetails;
    }


    /**
     * 根据班级编号查询详细课表信息
     *
     * @param clazzId 班级编号
     * @return 详细的课程信息
     */
    @Override
    public List<TimeTableDetails> queryTimeTableDetailsByClazzId(Long managerId, Integer clazzId) {
        List<TimeTableDetails> timeTableDetails = new ArrayList<>();
        List<TimeTable> timeTables = this.queryTimeTableByManager(managerId).get(clazzId);

        for (TimeTable timeTable : timeTables) {
            if (timeTable.getId() == 0) {
                TimeTableDetails timeTableDetails1 = new TimeTableDetails("无", "无", "无", "无");
                timeTableDetails.add(timeTableDetails1);
            } else {
                Clazz clazz = this.clazzDao.selectClazzByClazzId(clazzId);
                Course course = this.courseDao.selectCourseByCourseId(timeTable.getCourseId());
                Teacher teacher = this.teacherDao.selectTeacherByTeacherId(timeTable.getTeacherId());
                TimeTableDetails timeTableDetails1 = new TimeTableDetails(clazz.getName(), course.getName(), teacher.getName(), timeTable.getRoom());
                timeTableDetails.add(timeTableDetails1);
            }

        }

        return timeTableDetails;
    }


    /**
     * 根据老师查询查询课表
     *
     * @param teacherId 老师的id
     * @return 老师的课表
     */
    private List<TimeTable> queryTimeTableByTeacher(Integer teacherId) {
        List<TimeTable> timeTables = new ArrayList<>();
        for (int week = 1; week <= 5; week++) {
            for (int time = 1; time <= 4; time++) {
                TimeTable timeTable = this.timeTableDao.selectTimeTableByWeekAndTimeAndTeacher(week, time, teacherId);
                if (null == timeTable) {
                    timeTables.add(new TimeTable(0, 0,0,0,week,time,""));
                } else {
                    timeTables.add(timeTable);
                }
            }
        }
        timeTables.sort((o1, o2) -> {
            if (o1.getWeek().equals(o2.getWeek())) {
                return o1.getTime() - o2.getTime();
            }
            return o1.getWeek() - o2.getWeek();
        });
        return timeTables;
    }


    /**
     * 根据班主任编号查询班主任所带班级的课表
     *
     * @param managerId 班主任编号
     * @return 键为班级编号，值为课表，课表排列方式为根据日期
     */
    private Map<Integer, List<TimeTable>> queryTimeTableByManager(Long managerId) {
        Map<Integer, List<TimeTable>> clazzTimeTable = new HashMap<>();
        List<Clazz> clazzes = this.clazzDao.selectClazzByManagerId(managerId);
        List<TimeTable> tempTimeTables;
        TimeTable tempTimeTable;
        for (Clazz clazz : clazzes) {
            tempTimeTables = new ArrayList<>();
            for (int week = 1; week <= 5; week++) {
                for (int time = 1; time <= 4; time++) {
                    tempTimeTable = this.timeTableDao.selectTimeTableByWeekAndTimeAndClazz(week, time, clazz.getId());
                    if (null == tempTimeTable) {
                        TimeTable nullTimeTable = new TimeTable(0, 0, clazz.getId(),0, week, time, "");
                        nullTimeTable.setWeek(week);
                        nullTimeTable.setTime(time);
                        tempTimeTable = nullTimeTable;
                    }
                    tempTimeTables.add(tempTimeTable);
                }
            }
            tempTimeTables.sort((o1, o2) -> {
                if (o1.getWeek().equals(o2.getWeek())) {
                    return o1.getTime() - o2.getTime();
                }
                return o1.getWeek() - o2.getWeek();
            });
            clazzTimeTable.put(clazz.getId(), tempTimeTables);
        }
        return clazzTimeTable;
    }


}
