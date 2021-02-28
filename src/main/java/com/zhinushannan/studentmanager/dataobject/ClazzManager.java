package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 9:51
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClazzManager {

    /**
     * 辅导员的编号
     * 10位
     * 主键
     */
    private Long id;

    /**
     * 辅导员姓名
     * 最长限制为5个字
     */
    private String name;

    /**
     * 辅导员密码
     * 最长限制是20位，最短是8位
     */
    private String password;


    private String email;
}
