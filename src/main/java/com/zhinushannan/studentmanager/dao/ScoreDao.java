package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:16
 * @subject
 */
@Mapper
public interface ScoreDao {

    @Insert("insert into score (id, student_id, course_id, score, status)" +
            "values(#{id}, #{studentId}, #{courseId}, #{score}, #{status})")
    int insert(Score score);

    @Delete("delete from score where id=#{id}")
    int delete(@Param("id")Integer scoreId);

    @Update("update score id=#{id}, student_id=#{studentId}, course_id=#{courseId}," +
            "score=#{score}, status=#{status} where id=#{id}")
    int update(Score score);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score where id=#{id}")
    Score selectScoreByScoreId(@Param("id")Integer scoreId);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score where student_id=#{id}")
    List<Score> selectScoreByStudentId(@Param("id")Long studentId);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score where course_id=#{id}")
    List<Score> selectScoreByCourseId(@Param("id")Integer courseId);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score where score<#{man} and score>#{min}")
    List<Score> selectScoreByScoreRange(@Param("min")Double min, @Param("max")Double max);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score where status=#{status}")
    List<Score> selectScoreByScoreStatus(@Param("status")String status);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score where student_id=#{studentId} and course_id=#{courseId}")
    Score selectScoreByStudentAndCourse(@Param("studentId")Long studentId, @Param("courseId")Long courseId);

    @Select("select id, student_id as studentId, course_id as courseId, score, status from score")
    List<Score> findAllScore();

}
