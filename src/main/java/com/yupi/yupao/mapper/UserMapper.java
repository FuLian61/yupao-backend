package com.yupi.yupao.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.yupao.model.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @author 浮涟i
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-11-07 22:38:47
* @Entity com.yupi.yupao.model.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

}




