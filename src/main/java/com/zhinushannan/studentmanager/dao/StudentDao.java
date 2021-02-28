package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/15 16:04
 * @subject
 */
@Mapper
public interface StudentDao {

    /**
     * 新增方法
     * @param student
     * @return 返回1为操作成功,0为失败
     */
    @Insert("insert into student (id, name, pwd, clazz_id, sex, birthday, mobile, email, homeadd, status)" +
            "values(#{id}, #{name}, #{password}, #{clazzId}, #{sex}, #{birthday}, #{mobile}, #{email}, #{homeAddress}, #{status})")
    int insert(Student student);

    /**
     * 根据学生的学号删除学生
     * @param id 学生的学号
     * @return 返回1为操作成功,0则失败
     */
    @Delete("delete from student where id=#{id}")
    int delete(@Param("id") Long id);

    /**
     * 更新学生数据
     * @param student 传入需要修改的学生的信息
     * @return 返回1为操作成功,0为失败
     */
    @Update("update student set id=#{id}, name=#{name}, pwd=#{password}, clazz_id=#{clazzId}, sex=#{sex}, birthday=#{birthday}, mobile=#{mobile}, email=#{email}, homeadd=#{homeAddress}, status=#{status} where id=#{id}")
    int update(Student student);

    /**
     * 根据学号查询学生信息
     * @param id 学生的学号
     * @return 返回对应的学生信息
     */
    @Select("select id, name, pwd as password, clazz_id as clazzId, sex, birthday, mobile, email, homeadd as homeAddress, status from student where id=#{id}")
    Student selectStudentByStudentId(@Param("id") Long id);

    /**
     * 根据学生姓名查询学生信息
     * @param name 学生的姓名
     * @return 查询到的学生的信息
     */
    @Select("select id, name, pwd as password, clazz_id as clazzId, sex, birthday, mobile, email, homeadd as homeAddress, status from student where name=#{name}")
    List<Student> selectStudentsByName(@Param("name") String name);


    @Select("select id, name, pwd as password, clazz_id as clazzId, sex, birthday, mobile, email, homeadd as homeAddress, status from student where clazz_id=#{clazzId}")
    List<Student> selectStudentsByClazzId(@Param("clazzId")Integer clazzId);

    /**
     * 查询所有学生信息
     * @return 返回所有学生信息的集合
     */
    @Select("select id, name, pwd as password, clazz_id as clazzId, sex, birthday, mobile, email, homeadd as homeAddress, status from student")
    List<Student> findAllStudent();

}
