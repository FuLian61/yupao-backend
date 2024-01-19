package com.yupi.yupao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @Author 浮涟
 * @Since 2023/11/8
 * @Version 1.0
 */
@Data
public class UserLoginRequst implements Serializable {

    private static final long serialVersionUID = 7148578484249086625L;

    private String userAccount;
    private String userPassword;
}
