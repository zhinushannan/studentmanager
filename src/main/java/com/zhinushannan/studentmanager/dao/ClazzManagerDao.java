package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.ClazzManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 19:59
 * @subject
 */
@Mapper
public interface ClazzManagerDao {

    /**
     * 新增辅导员方法
     * @param clazzManager 辅导员信息
     * @return 返回1为操作成功，0为失败
     */
    @Insert("insert into clazzmanager (id, name, password) " +
            "values(#{id}, #{name}, #{pwd})")
    int insert(ClazzManager clazzManager);

    /**
     * 删除辅导员
     * @param id 辅导员编号
     * @return   返回1为操作成功，0为失败
     */
    @Delete("delete from clazzmanager where id=#{id}")
    int delete(@Param("id")Long id);

    /**
     * 更新管理员信息
     * @param clazzManager 管理员信息
     * @return 返回1为操作成功，0为失败
     */
    @Update("update clazzmanager set id=#{id}, name=#{name}, pwd=#{password} where id=#{id}")
    int update(ClazzManager clazzManager);

    /**
     * 根据辅导员id查询辅导员
     * @param id 辅导员编号
     * @return 辅导员信息
     */
    @Select("select id, name, pwd as password from clazzmanager where id=#{id}")
    ClazzManager selectManagerByManagerId(@Param("id")Long id);


    /**
     * 查询所有辅导员
     * @return 辅导员信息
     */
    @Select("select id, name, pwd as password from clazzmanager")
    List<ClazzManager> findAllManager();

}
