package com.zhinushannan.studentmanager;

import com.zhinushannan.studentmanager.dao.StudentDao;
import com.zhinushannan.studentmanager.dataobject.Student;
import com.zhinushannan.studentmanager.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class StudentmanagerApplicationTests {

    @Autowired(required = false)
    private StudentDao studentDao;

    @Test
    void testEmailUtil(){
        Integer code = (int)((Math.random() * 9 + 1) * 100000);
        System.out.println(code);
        EmailUtil.sendMail("2764406290@qq.com", code.toString());
    }

    @Test
    void testStudentDao(){
        List<Student> students = this.studentDao.selectStudentsByClazzId(7190766);
        Student student = this.studentDao.selectStudentByStudentId(7190766102l);
        System.out.println("班级编号为7190766的所有学生" + students);
        int delete = this.studentDao.delete(7190766102l);
        students = this.studentDao.selectStudentsByClazzId(7190766);
        System.out.println("删除学号为7190766101的学生，影响行数为：" + delete + "该学生的信息为：" + student + "，查询删除后该班级所有学生" + students);
        int insert = this.studentDao.insert(student);
        students = this.studentDao.selectStudentsByClazzId(7190766);
        System.out.println("将删除的学生添加回去，之后的班级学生" + students);
    }

}
