package com.yupi.yupao.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求参数
 */
@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = 524583683696402859L;
    private long id;
}
