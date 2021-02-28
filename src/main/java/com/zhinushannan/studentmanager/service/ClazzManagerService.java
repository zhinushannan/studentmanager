package com.zhinushannan.studentmanager.service;

import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.ClazzManager;
import com.zhinushannan.studentmanager.dataobject.Student;
import com.zhinushannan.studentmanager.dataobject.TimeTable;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 23:36
 * @subject
 */
public interface ClazzManagerService {

    /**
     * 返回辅导员信息
     * @param clazzManagerId 辅导员编号
     * @return 返回辅导员信息
     */
    ClazzManager queryManagerById(Long clazzManagerId);


    /**
     * 根据班级编号查询辅导员信息
     * @param clazzId 班级编号
     * @return 返回辅导员信息
     */
    ClazzManager queryManagerByClazzId(Integer clazzId);

    /**
     * 更改辅导员信息
     * @param clazzManager
     * @return
     */
    boolean updateManagerInfo(ClazzManager clazzManager);

    /**
     * 修改辅导员密码
     * @param clazzManager
     * @return
     */
    boolean changeManagerPassword(ClazzManager clazzManager);

}
