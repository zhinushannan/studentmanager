package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 9:43
 * @subject  班级类
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {

    /**
     * 班级的ID
     * 主键
     * 长度为7，是学号的前七位
     */
    private Integer id;

    /**
     * 班级名称
     * 最长限制15个字
     */
    private String name;

    /**
     * 专业编号
     * 2位
     * 专业表的主键  @Profession
     */
    private Integer professionId;

    /**
     * 辅导员ID
     * 10位
     * 对应的是辅导员表的主键 @ClazzManager
     */
    private Long managerId;

}
