package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:10
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /**
     * 课程的ID
     * 主键
     * 长度3
     */
    private Integer id;

    /**
     * 课程的名称
     * 长度10
     */
    private String name;

}
