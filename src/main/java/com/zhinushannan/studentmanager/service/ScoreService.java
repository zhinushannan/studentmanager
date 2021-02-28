package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Score;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhinushannan
 * @date 2020/12/18 15:51
 * @subject
 */
public interface ScoreService {

    /**
     * 根据教师查询成绩
     *
     * @param teacherId 教师的ID
     * @return 老师下面所有学生的成绩
     */
    Map<Integer, List<Score>> queryScoreByTeacherId(Integer teacherId);

    /**
     * 根据课程号和老师编号查询成绩单
     * @param courseId 课程编号
     * @param teacherId 教师编号
     * @return 返回成绩单
     */
    List<ScoreDetail> queryScoreByCourseAndTeacher(Integer courseId, Integer teacherId);

    /**
     * 查询学生的成绩信息
     * @param studentId 学生id
     * @return 返回成绩信息
     */
    List<ScoreDetail> queryScoreByStudent(Long studentId);

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class ScoreDetail {

        private Integer clazzId;

        private String clazzName;

        private Long studentId;

        private String studentName;

        private Integer courseId;

        private String courseName;

        private Integer teacherId;

        private String teacherName;

    }
}
