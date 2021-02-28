package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.Teacher;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/18 15:30
 * @subject
 */
public interface TeacherService {

    /**
     * 根据教师的id查询教师信息
     * @param teacherId 教师id
     * @return 教师信息
     */
    Teacher queryTeacherById(Integer teacherId);

    /**
     * 获取和任课老师相关的班级
     * @param teacherId 任课老师的id
     * @return 与之相关的班级信息
     */
    List<Clazz> queryClazzByTeacher(Integer teacherId);


    boolean updateTeacher(Teacher teacher);
}
