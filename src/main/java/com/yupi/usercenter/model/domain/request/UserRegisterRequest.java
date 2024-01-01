package com.yupi.usercenter.model.domain.request;

import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @Author 浮涟
 * @Since 2023/11/8
 * @Version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
