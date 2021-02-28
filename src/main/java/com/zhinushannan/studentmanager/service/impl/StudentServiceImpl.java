package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.ClazzDao;
import com.zhinushannan.studentmanager.dao.StudentDao;
import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.Student;
import com.zhinushannan.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhinushannan
 * @date 2020/12/18 9:37
 * @subject
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private ClazzDao clazzDao;

    @Autowired(required = false)
    private StudentDao studentDao;

    /**
     * 根据辅导员编号查询学生
     *
     * @param managerId 键为班级编号，值为该班级对应的学生
     * @return 辅导员下的学生
     */
    @Override
    public Map<Integer, List<Student>> queryStudentByClazzManager(Long managerId) {
        Map<Integer, List<Student>> clazzStudent = new HashMap<>();
        List<Clazz> clazzes = this.clazzDao.selectClazzByManagerId(managerId);
        for (Clazz clazz : clazzes) {
            clazzStudent.put(clazz.getId(), this.studentDao.selectStudentsByClazzId(clazz.getId()));
        }
        return clazzStudent;
    }

    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId 学号id
     * @return 学生信息
     */
    @Override
    public Student queryStudentById(Long studentId) {
        return this.studentDao.selectStudentByStudentId(studentId);
    }

    /**
     * 根据班级编号查询学生信息
     *
     * @param clazzId 班级编号
     * @return 班级的学生信息
     */
    @Override
    public List<Student> queryStudentByClazz(Integer clazzId) {
        return this.studentDao.selectStudentsByClazzId(clazzId);
    }

    /**
     * 更新学生信息
     *
     * @return 返回学生修改后的信息
     */
    @Override
    public boolean updateStudentInfo(Student student) {
        return this.studentDao.update(student) == 1;
    }

    /**
     * 修改学生密码
     *
     * @param student
     * @return
     */
    @Override
    public boolean changeStudentPassword(Student student) {
        return this.studentDao.update(student) == 1;
    }
}
