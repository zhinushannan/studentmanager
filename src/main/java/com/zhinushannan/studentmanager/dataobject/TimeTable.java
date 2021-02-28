package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:35
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

    /**
     * 时间表的id，系统自动生成
     */
    private Integer id;

    /**
     * 课程ID
     * 对应课程表的主键  @Course
     * 长度7
     */
    private Integer courseId;

    /**
     * 班级ID
     * 对应班级表的主键  @Clazz
     * 长度7
     */
    private Integer clazzId;

    /**
     * 任课老师的ID
     */
    private Integer teacherId;

    /**
     * 代表周几的课
     * 周一就是1
     */
    private Integer week;

    /**
     * 第几节课
     * 1234
     */
    private Integer time;

    /**
     * 上课教室
     */
    private String room;
}
