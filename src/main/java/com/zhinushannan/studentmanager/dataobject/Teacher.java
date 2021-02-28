package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:17
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    /**
     * 老师的ID
     * 主键
     * 长度2
     */
    private Integer id;

    /**
     * 老师的姓名
     * 长度5
     */
    private String name;


    /**
     * 老师的密码
     */
    private String password;

    private String email;

}
