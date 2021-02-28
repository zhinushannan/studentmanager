package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.*;
import com.zhinushannan.studentmanager.dataobject.*;
import com.zhinushannan.studentmanager.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhinushannan
 * @date 2020/12/18 15:52
 * @subject
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired(required = false)
    private ScoreDao scoreDao;

    @Autowired(required = false)
    private TimeTableDao timeTableDao;

    @Autowired(required = false)
    private ClazzDao clazzDao;

    @Autowired(required = false)
    private StudentDao studentDao;

    @Autowired(required = false)
    private CourseDao courseDao;

    @Autowired(required = false)
    private TeacherDao teacherDao;

    /**
     * 根据教师查询成绩
     *
     * @param teacherId 教师的ID
     * @return 老师下面所有学生的成绩
     */
    @Override
    public Map<Integer, List<Score>> queryScoreByTeacherId(Integer teacherId) {
        Map<Integer, List<Score>> courseScore = new HashMap<>();
        List<TimeTable> timeTables = this.timeTableDao.selectTimeTablesByTeacher(teacherId);
        for (TimeTable timeTable : timeTables) {
            courseScore.put(timeTable.getCourseId(), this.scoreDao.selectScoreByCourseId(timeTable.getCourseId()));
        }
        return courseScore;
    }

    /**
     * 根据课程号和老师编号查询成绩单
     *
     * @param courseId  课程编号
     * @param teacherId 教师编号
     * @return 返回成绩单
     */
    @Override
    public List<ScoreDetail> queryScoreByCourseAndTeacher(Integer courseId, Integer teacherId) {
        List<ScoreDetail> scoreDetails = new ArrayList<>();

        List<Score> scores = this.scoreDao.selectScoreByCourseId(courseId);

        // 查询老师所带的班级
        List<TimeTable> timeTables = this.timeTableDao.selectTimeTablesByTeacher(teacherId);
        Set<Clazz> clazzSet = new HashSet<>();
        for (TimeTable timeTable : timeTables) {
            clazzSet.add(this.clazzDao.selectClazzByClazzId(timeTable.getClazzId()));
        }

        // 课程的基本信息
        Course course = this.courseDao.selectCourseByCourseId(courseId);

        // 老师的信息
        Teacher teacher = this.teacherDao.selectTeacherByTeacherId(teacherId);

        for (Score score : scores) {
            Student student = this.studentDao.selectStudentByStudentId(score.getStudentId());
            Clazz clazz = this.clazzDao.selectClazzByClazzId(student.getClazzId());
            if (clazzSet.contains(clazz)) {
                ScoreDetail scoreDetail = new ScoreDetail();
                scoreDetail.setClazzId(clazz.getId());
                scoreDetail.setClazzName(clazz.getName());
                scoreDetail.setStudentId(student.getId());
                scoreDetail.setStudentName(student.getName());
                scoreDetail.setCourseId(course.getId());
                scoreDetail.setCourseName(course.getName());
                scoreDetail.setTeacherId(teacher.getId());
                scoreDetail.setTeacherName(teacher.getName());
                scoreDetails.add(scoreDetail);
            }
        }
        return scoreDetails;
    }

    /**
     * 查询学生的成绩信息
     *
     * @param studentId 学生id
     * @return 返回成绩信息
     */
    @Override
    public List<ScoreDetail> queryScoreByStudent(Long studentId) {
        List<ScoreDetail> scoreDetails = new ArrayList<>();
        List<Score> scores = this.scoreDao.selectScoreByStudentId(studentId);

        Student student = this.studentDao.selectStudentByStudentId(studentId);
        Clazz clazz = this.clazzDao.selectClazzByClazzId(student.getClazzId());
        for (Score score : scores) {
            Course course = this.courseDao.selectCourseByCourseId(score.getCourseId());

            TimeTable timeTable = this.timeTableDao.selectTimeTablesByCourseAndClazz(course.getId(), student.getClazzId());
            Teacher teacher = this.teacherDao.selectTeacherByTeacherId(timeTable.getTeacherId());

            ScoreDetail scoreDetail = new ScoreDetail();

            scoreDetail.setClazzId(clazz.getId());
            scoreDetail.setClazzName(clazz.getName());
            scoreDetail.setStudentId(student.getId());
            scoreDetail.setStudentName(student.getName());
            scoreDetail.setCourseId(course.getId());
            scoreDetail.setCourseName(course.getName());
            scoreDetail.setTeacherId(teacher.getId());
            scoreDetail.setTeacherName(teacher.getName());

            scoreDetails.add(scoreDetail);
        }

        return scoreDetails;
    }


}
