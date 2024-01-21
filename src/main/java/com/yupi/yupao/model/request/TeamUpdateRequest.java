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
public class TeamUpdateRequest implements Serializable {

    private static final long serialVersionUID = 8481854990506857034L;

    /**
     * id
     */
    private Long id;
    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;

}
