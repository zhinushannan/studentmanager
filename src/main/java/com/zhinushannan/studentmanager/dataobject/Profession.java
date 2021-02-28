package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:00
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Profession {

    /**
     * 专业ID
     * 两个长度
     */
    private Integer id;

    /**
     * 专业名称
     * 长度限制20
     */
    private String name;

    /**
     * 院系的ID
     * 为院系表的主键  @College
     * 长度限制2
     */
    private Integer collegeId;

}
