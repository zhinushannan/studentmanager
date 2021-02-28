package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 19:54
 * @subject
 */
@Mapper
public interface ClazzDao {

    /**
     * 增加班级的方法
     * @param clazz 班级信息
     * @return 返回1为操作成功，0为失败
     */
    @Insert("insert into clazz (id, name, profess_id, manager_id)" +
            "values(#{id}, #{name}, #{professionId}, #{managerId})")
    int insert(Clazz clazz);

    /**
     * 根据班级编号删除班级
     * @param id 班级编号
     * @return 返回1为操作成功，0为失败
     */
    @Delete("delete from clazz where id=#{id}")
    int delete(@Param("id") Integer id);

    /**
     * 修改班级信息
     * @return 返回1为操作成功，0为失败
     */
    @Update("update clazz set id=#{id}, name=#{name}, profess_id=#{professionId}," +
            "manager_id=#{managerId} where id=#{id}")
    int update();


    /**
     * 根据班级Id查询班级信息
     * @param id 班级id
     * @return 班级信息
     */
    @Select("select id, name, profess_id as professionId, manager_id as managerId from clazz where id=#{id}")
    Clazz selectClazzByClazzId(@Param("id")Integer id);


    /**
     * 根据辅导员编号查询班级信息
     * @param managerId 辅导员编号
     * @return 班级信息
     */
    @Select("select id, name, profess_id as professionId, manager_id as managerId from clazz where manager_id=#{managerId}")
    List<Clazz> selectClazzByManagerId(@Param("managerId")Long managerId);

    /**
     * 查询所有班级
     * @return 返回班级信息
     */
    @Select("select id, name, profess_id as professionId, manager_id as managerId from clazz")
    List<Clazz> findAllClazz();
}
