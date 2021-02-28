package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Clazz;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/17 11:11
 * @subject
 */
public interface ClazzService {

    /**
     * 根据辅导员编号查询班级
     * @param managerId 辅导员编号
     * @return 辅导员所带班级的集合
     */
    List<Clazz>queryClazzByManagerId(Long managerId);

    /**
     * 根据班级编号查询班级信息
     * @param clazzId 班级编号
     * @return 班级信息
     */
    Clazz queryClazzById(Integer clazzId);

    /**
     * 根据任课老师查询班级
     * @param teacherId 任课老师的id
     * @return 返回班级
     */
    List<Clazz> queryClazzByTeacherId(Integer teacherId);

}
