package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.College;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:04
 * @subject
 */
@Mapper
public interface CollegeDao {

    @Insert("insert into college (id, college, department) " +
            "values(#{id}, #{college}, #{department})")
    int insert(College college);

    @Delete("delete from college where id=#{id}")
    int delete(@Param("id")Integer collegeId);

    @Update("update college id=#{id}, college=#{college}, department=#{department}")
    int update(College college);

    @Select("select id, college, department from college where id=#{id}")
    College selectCollegeByCollegeId(@Param("id")Integer collegeId);

    @Select("select id, college, department from college where college=#{name}")
    College selectCollegeByCollegeName(@Param("name")String collegeName);

    @Select("select id, college, department from college")
    List<College> findAllCollege();

}
