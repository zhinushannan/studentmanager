package com.zhinushannan.studentmanager.dao;

import com.zhinushannan.studentmanager.dataobject.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 20:11
 * @subject
 */
@Mapper
public interface NoticeDao {

    @Insert("insert into notice (id, from_id, to_id, type, content, status)" +
            "values(#{id}, #{fromId}, #{toId}, #{type}, #{content}, #{status})")
    int insert(Notice notice);

    @Delete("delete from notice where id=#{id}")
    int delete(@Param("id")Integer noticeId);

    @Update("update notice id=#{id}, from_id=#{fromId}, to_id=#{toId}, type=#{type}," +
            "content=#{content}, status=#{status} where id=#{id}")
    int update(Notice notice);

    @Select("select id, from_id as fromId, to_id as toId, type, content, status from notice where from_id=#{id}")
    List<Notice> selectNoticeByFromId(@Param("id")Long fromId);

    @Select("select id, from_id as fromId, to_id as toId, type, content, status from notice where to_id=#{id}")
    List<Notice> selectNoticeByToId(@Param("id")Long toId);

    @Select("select id, from_id as fromId, to_id as toId, type, content, status from notice where type=#{type}")
    List<Notice> selectNoticeByType(@Param("type")Integer type);

    @Select("select id, from_id as fromId, to_id as toId, type, content, status from notice where status=#{status}")
    List<Notice> selectNoticeByStatue(@Param("status")Integer status);

    @Select("select id, from_id as fromId, to_id as toId, type, content, status from notice")
    List<Notice> findAllNotice();

}
