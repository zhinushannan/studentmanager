package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * @author zhinushannan
 * @date 2020/12/15 9:35
 * @subject  学生类
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * 学生的学号
     * 10位
     * 学生表的主键
     * 同时也是管理系统的登录账号
     */
    @NotEmpty
    private Long id;

    /**
     * 学生的姓名
     * 最长允许5个字
     */
    @NotEmpty
    @Min(value = 2)
    @Max(value = 5)
    private String name;

    /**
     * 学生的密码
     * 最长限制为20个字，同时不得低于8位
     */
    @NotEmpty
    @Min(value = 11)
    @Max(value = 20)
    private String password;

    /**
     * 班级ID
     * 7位，学号的前七位，例如7190766138，班级编号就是7190766
     * 对应的是班级表的主键 @Clazz
     */
    @NotEmpty
    private Integer clazzId;

    /**
     * 性别，
     * 1代表男，0代表女
     */
    @NotEmpty
    private Integer sex;

    /**
     * 生日
     * 可不填
     */
    private LocalDate birthday;

    /**
     * 手机号
     * 可不填
     */

    private Long mobile;

    /**
     * 电子邮箱
     */
    @Email
    private String email;

    /**
     * 家庭住址
     * 可不填
     * 最长限制是200个字
     */
    @Max(value = 200)
    private String homeAddress;

    /**
     * 学生状态，包括：
     * 正常  ......  1
     * 请假中......  2
     * 休学中......  3
     */
    @NotEmpty
    private Integer status;

}
