package com.yupi.yupao.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 浮涟
 * @Since 2024/1/18
 * @Version 1.0
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -5860707094194210842L;
    /**
     * 页面大小
     */
    protected int pageSize = 10;

    /**
     * 当前是第几页
     */
    protected int pageNum = 1;
}
