package com.yupi.yupao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户退出队伍请求体
 */
@Data
public class TeamQuitRequest implements Serializable {

    private static final long serialVersionUID = -5670307774538539197L;

    /**
     * id
     */
    private Long teamId;


}
