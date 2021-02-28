package com.zhinushannan.studentmanager.dataobject;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhinushannan
 * @date 2020/12/26 11:28
 * @subject
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfo implements Serializable {

    private String userId;

    private String password;

    private String type;

}
