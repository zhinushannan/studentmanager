package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.TimeTable;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:21
 * @subject
 */
@Mapper
public interface TimeTableDao {

    @Insert("insert into timetable (id, course_id, clazz_id, teacher_id, week, time, room) " +
            "values (#{id}, #{courseId}, #{clazzId}, #{teacherId}, #{week}, #{time}, #{room})")
    int insert(TimeTable timeTable);

    @Delete("delete from timetable where id=#{id}")
    int delete(@Param("id")Integer timeTableId);

    @Update("update timetable id=#{id}, course_id=#{courseId}, clazz_id=#{clazzId}, " +
            "teacher_id=#{teacherId}, week=#{week}, time=#{time}, room=#{room} where id=#{id}")
    int update(TimeTable timeTable);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where course_id=#{id}")
    List<TimeTable> selectTimeTablesByCourseId(@Param("id")Integer courseId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where clazz_id=#{clazzId}")
    List<TimeTable> selectTimeTablesByClazzId(@Param("clazzId")Integer clazzId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where teacher_id=#{teacherId}")
    List<TimeTable> selectTimeTablesByTeacher(@Param("teacherId")Integer teacherId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where time=#{time} and clazz_id=#{clazzId}")
    List<TimeTable> selectTimeTablesByTimeAndClazz(@Param("time")Integer time, @Param("clazzId")Integer clazzId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where week=#{week} and time=#{time} and clazz_id=#{clazzId}")
    TimeTable selectTimeTableByWeekAndTimeAndClazz(@Param("week")Integer week, @Param("time")Integer time, @Param("clazzId")Integer clazzId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where week=#{week} and time=#{time} and teacher_id=#{teacherId}")
    TimeTable selectTimeTableByWeekAndTimeAndTeacher(@Param("week")Integer week, @Param("time")Integer time, @Param("teacherId")Integer teacherId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where week=#{week} and clazz_id=#{clazzId}")
    List<TimeTable> selectTimeTablesByWeekAndClazz(@Param("week")Integer week, @Param("clazzId")Integer clazzId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room " +
            "from timetable where course_id=#{courseId} and clazz_id=#{clazzId}")
    TimeTable selectTimeTablesByCourseAndClazz(@Param("courseId")Integer courseId, @Param("clazzId")Integer clazzId);

    @Select("select id, course_id as courseId, clazz_id as clazzId, teacher_id as teacherId, week, time, room from timetable")
    List<TimeTable> findAllTimeTable();

}
