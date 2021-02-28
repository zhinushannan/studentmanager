package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Student;

import java.util.List;
import java.util.Map;

/**
 * @author zhinushannan
 * @date 2020/12/18 9:37
 * @subject
 */
public interface StudentService {

    /**
     * 根据辅导员编号查询学生
     * @param managerId 键为班级编号，值为该班级对应的学生
     * @return 辅导员下的学生
     */
    Map<Integer, List<Student>> queryStudentByClazzManager(Long managerId);

    /**
     * 根据学生学号查询学生信息
     * @param studentId 学号id
     * @return 学生信息
     */
    Student queryStudentById(Long studentId);

    /**
     * 根据班级编号查询学生信息
     * @param clazzId 班级编号
     * @return 班级的学生信息
     */
    List<Student>queryStudentByClazz(Integer clazzId);

    /**
     * 更新学生信息
     * @return 返回学生修改后的信息
     */
    boolean updateStudentInfo(Student student);

    /**
     * 修改学生密码
     * @param student
     * @return
     */
    boolean changeStudentPassword(Student student);

}
