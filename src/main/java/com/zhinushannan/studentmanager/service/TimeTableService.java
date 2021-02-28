package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Course;
import com.zhinushannan.studentmanager.dataobject.TimeTable;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhinushannan
 * @date 2020/12/17 9:46
 * @subject
 */
public interface TimeTableService {

    /**
     * 根据班主任编号查询班主任所带班级的课表
     *
     * @param managerId 班主任编号
     * @return 课表（名称）
     */
    Map<Integer, List<String>> queryTimeTableNameByManager(Long managerId);

    /**
     * 根据老师查询查询课表
     *
     * @param teacherId 老师的id
     * @return 老师的课表
     */
    List<TimeTableDetails> queryTimeTableDetailsByTeacher(Integer teacherId);

    /**
     * 根据班级编号查询详细课表信息
     * @param clazzId 班级编号
     * @return 详细的课程信息
     */
    List<TimeTableDetails> queryTimeTableDetailsByClazzId(Long managerId, Integer clazzId);

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    class TimeTableDetails{

        private String clazzName;

        private String courseName;

        private String teacherName;

        private String room;

    }

}
