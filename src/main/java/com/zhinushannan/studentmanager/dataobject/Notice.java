package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

/**
 * @author zhinushannan
 * @date 2020/12/15 10:23
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    /**
     * 通知的ID
     * 通知类的主键
     * 长度6
     */
    private Integer id;

    /**
     * 发送人的ID
     * 长度限制10
     */
    private Long fromId;

    /**
     * 收信人的ID
     * 长度限制10
     */
    private Long toId;

    /**
     * 信息的类型
     * 长度限制1
     * 1 ... 请假申请
     * 2 ... 休学申请
     * 3 ... 复学申请
     * 4 ... 常规信息
     */
    private Integer type;

    /**
     * 信息的内容
     * 长度限制为200字
     */
    private String content;

    /**
     * 通知状态
     *  0 ... 未读
     *  1 ... 已读
     */
    private Integer status;

}
