package com.yupi.usercenter.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.usercenter.model.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @author 浮涟i
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-11-07 22:38:47
* @Entity com.yupi.usercenter.model.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户
     * @TableName user
     */
    @TableName(value ="user")
    @Data
    class User implements Serializable {
        /**
         * id
         */
        @TableId(type = IdType.AUTO)
        private Long id;

        /**
         * 用户昵称
         */
        private String username;

        /**
         * 账号
         */
        private String userAccount;

        /**
         * 用户头像
         */
        private String avatarUrl;

        /**
         * 性别
         */
        private Integer gender;

        /**
         * 密码
         */
        private String userPassword;

        /**
         * 电话
         */
        private String phone;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 状态 0 - 正常
         */
        private Integer userStatus;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 更新时间
         */
        private Date updateTime;

        /**
         * 是否删除
         */
        private Integer isDelete;

        /**
         * 用户角色 0 - 普通用户 1 - 管理员
         */
        private Integer userRole;

        /**
         * 星球编号
         */
        private String planetCode;

        @TableField(exist = false)
        private static final long serialVersionUID = 1L;
    }
}




