package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:18
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    /**
     * 分数表的ID
     * 主键
     * 长度6
     */
    private Integer id;

    /**
     * 学生的ID
     * 学生表的主键 @Student
     * 长度10
     */
    private Long studentId;

    /**
     * 课程的ID
     * 课程表的主键  @Course
     * 长度3
     */
    private Integer courseId;

    /**
     * 课程分数
     */
    private Double score;

    /**
     * 状态，包括
     * 挂科
     * 旷考
     * 作弊
     */
    private String status;

}
