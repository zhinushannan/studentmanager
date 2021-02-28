package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Profession;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:14
 * @subject
 */
@Mapper
public interface ProfessionDao {

    @Insert("insert into profession (id, name, college_id)" +
            "values(#{id}, #{name}, #{collegeId})")
    int insert(Profession profession);

    @Delete("delete from profession where id=#{id}")
    int delete(@Param("id")Integer professionId);

    @Update("update profession id=#{id}, name=#{name}, college_id=#{collegeId}" +
            "where id=#{id}")
    int update(Profession profession);

    @Select("select id, name, college_id as collegeId from profession where id=#{id}")
    Profession selectProfessionByProfessionId(@Param("id")Integer professionId);

    @Select("select id, name, college_id as collegeId from profession where name=#{name}")
    Profession selectProfessionByProfessionName(@Param("name")String professionName);

    @Select("select id, name, college_id as collegeId from profession")
    List<Profession> findAllProfession();

}
