package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.ClazzDao;
import com.zhinushannan.studentmanager.dao.TimeTableDao;
import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.TimeTable;
import com.zhinushannan.studentmanager.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhinushannan
 * @date 2020/12/17 11:12
 * @subject
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired(required = false)
    private ClazzDao clazzDao;

    @Autowired(required = false)
    private TimeTableDao timeTableDao;

    /**
     * 根据辅导员编号查询班级
     *
     * @param managerId 辅导员编号
     * @return 辅导员所带班级的集合
     */
    @Override
    public List<Clazz> queryClazzByManagerId(Long managerId) {
        return this.clazzDao.selectClazzByManagerId(managerId);
    }

    /**
     * 根据班级编号查询班级信息
     *
     * @param clazzId 班级编号
     * @return 班级信息
     */
    @Override
    public Clazz queryClazzById(Integer clazzId) {
        return this.clazzDao.selectClazzByClazzId(clazzId);
    }

    /**
     * 根据任课老师查询班级
     *
     * @param teacherId 任课老师的id
     * @return 返回班级
     */
    @Override
    public List<Clazz> queryClazzByTeacherId(Integer teacherId) {
        List<TimeTable> timeTables = this.timeTableDao.selectTimeTablesByTeacher(teacherId);
        Set<Clazz> clazzes = new HashSet<>();
        for (TimeTable timeTable : timeTables) {
            clazzes.add(this.clazzDao.selectClazzByClazzId(timeTable.getClazzId()));
        }
        List<Clazz> result = new ArrayList<>();
        for (Clazz clazz : clazzes) {
            result.add(clazz);
        }
        return result;
    }
}
