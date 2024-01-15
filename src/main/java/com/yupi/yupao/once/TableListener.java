package com.yupi.yupao.once;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 浮涟
 * @Since 2024/1/7
 * @Version 1.0
 */
@Slf4j
public class TableListener implements ReadListener<XingQiuUserInfo> {
    /**
     * 这个每一条数据解析都会来调用
     * @param xingQiuUserInfo
     * @param analysisContext
     */
    @Override
    public void invoke(XingQiuUserInfo xingQiuUserInfo, AnalysisContext analysisContext) {
        System.out.println(xingQiuUserInfo);
    }

    /**
     * 所有数据解析完成了都会来调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("已解析完成");
    }
}
