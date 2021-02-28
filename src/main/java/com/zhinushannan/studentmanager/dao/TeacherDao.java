package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:20
 * @subject
 */
@Mapper
public interface TeacherDao {

    @Insert("insert into teacher (id, name, pwd)" +
            "values(#{id}, #{name}, #{password})")
    int insert(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    int delete(@Param("id")Integer teacherId);

    @Update("update teacher set id=#{id}, name=#{name}, pwd=#{password}, email=#{email} where id=#{id}")
    int update(Teacher teacher);

    @Select("select id, name, pwd as password from teacher where id=#{id}")
    Teacher selectTeacherByTeacherId(@Param("id")Integer teacherId);

    @Select("select id, name, pwd as password from teacher where name=#{name}")
    Teacher selectTeacherByTeacherName(@Param("name")String teacherName);

    @Select("select id, name, pwd as password from teacher")
    List<Teacher> findAllTeacher();

}
