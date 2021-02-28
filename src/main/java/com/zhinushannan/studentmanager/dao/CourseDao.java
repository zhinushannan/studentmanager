package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:09
 * @subject
 */
@Mapper
public interface CourseDao {

    @Insert("insert into course (id, name) " +
            "values(#{id}, #{name})")
    int insert(Course course);

    @Delete("delete from course where id=#{id}")
    int delete(@Param("id")Integer courseId);

    @Update("update course id=#{id}, name=#{name} where id=#{id}")
    int update(Course course);

    @Select("select id, name from course where id=#{id}")
    Course selectCourseByCourseId(@Param("id")Integer courseId);

    @Select("select id, name from course where name=#{name}")
    Course selectCourseByCourseName(@Param("name")String courseNameKeyWord);

    @Select("select id, name from course where teacher_id=#{id}")
    List<Course> selectCourseByTeacherId(@Param("id")Integer teacherId);

    @Select("select id, name from course")
    List<Course> findAllCourse();

}
