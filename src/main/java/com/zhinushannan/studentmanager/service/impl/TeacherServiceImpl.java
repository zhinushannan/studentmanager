package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.TeacherDao;
import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.Teacher;
import com.zhinushannan.studentmanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/18 15:31
 * @subject
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired(required = false)
    private TeacherDao teacherDao;

    /**
     * 根据教师的id查询教师信息
     *
     * @param teacherId 教师id
     * @return 教师信息
     */
    @Override
    public Teacher queryTeacherById(Integer teacherId) {
        return this.teacherDao.selectTeacherByTeacherId(teacherId);
    }

    /**
     * 获取和任课老师相关的班级
     *
     * @param teacherId 任课老师的id
     * @return 与之相关的班级信息
     */
    @Override
    public List<Clazz> queryClazzByTeacher(Integer teacherId) {
        return null;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return this.teacherDao.update(teacher) == 1;
    }
}
