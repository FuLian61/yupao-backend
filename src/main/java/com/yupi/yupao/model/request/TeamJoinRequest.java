package com.yupi.yupao.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 浮涟
 * @Since 2024/1/19
 * @Version 1.0
 */
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = -5670307774538539197L;

    /**
     * id
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;

}
