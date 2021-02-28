package com.zhinushannan.studentmanager.service.impl;

import com.zhinushannan.studentmanager.dao.ClazzDao;
import com.zhinushannan.studentmanager.dao.ClazzManagerDao;
import com.zhinushannan.studentmanager.dao.StudentDao;
import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.ClazzManager;
import com.zhinushannan.studentmanager.service.ClazzManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhinushannan
 * @date 2020/12/16 23:38
 * @subject
 */
@Service
public class ClazzManagerServiceImpl implements ClazzManagerService {

    @Autowired(required = false)
    private StudentDao studentDao;

    @Autowired(required = false)
    private ClazzDao clazzDao;

    @Autowired(required = false)
    private ClazzManagerDao clazzManagerDao;

    /**
     * 返回辅导员信息
     *
     * @param clazzManagerId 辅导员编号
     * @return 返回辅导员信息
     */
    @Override
    public ClazzManager queryManagerById(Long clazzManagerId) {
        return this.clazzManagerDao.selectManagerByManagerId(clazzManagerId);
    }

    /**
     * 根据班级编号查询辅导员信息
     *
     * @param clazzId 班级编号
     * @return 返回辅导员信息
     */
    @Override
    public ClazzManager queryManagerByClazzId(Integer clazzId) {
        Clazz clazz = this.clazzDao.selectClazzByClazzId(clazzId);
        return this.clazzManagerDao.selectManagerByManagerId(clazz.getManagerId());
    }

    /**
     * 更改辅导员信息
     *
     * @param clazzManager
     * @return
     */
    @Override
    public boolean updateManagerInfo(ClazzManager clazzManager) {
        return this.clazzManagerDao.update(clazzManager) == 1;
    }

    /**
     * 修改辅导员密码
     *
     * @param clazzManager
     * @return
     */
    @Override
    public boolean changeManagerPassword(ClazzManager clazzManager) {
        return this.clazzManagerDao.update(clazzManager) == 1;
    }
}
