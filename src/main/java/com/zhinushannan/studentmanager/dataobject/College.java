package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:08
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class College {

    /**
     * 主键
     * 长度2
     */
    private Integer id;

    /**
     * 二级学院名称
     * 长度限制10
     */
    private String college;

    /**
     * 系名
     * 长度限制20
     */
    private String department;
}
