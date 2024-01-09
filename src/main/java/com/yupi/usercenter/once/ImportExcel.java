package com.yupi.usercenter.once;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

import java.util.List;

/**
 * 导入excel数据
 */
public class ImportExcel {
    // 读取数据
    public static void main(String[] args) {
        String fileName = "D:\\code\\user-center-backend\\src\\main\\resources\\testExcel.xlsx";
//        readByListener(fileName);
        synchronousRead(fileName);
    }

    public static void readByListener(String fileName){
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName,XingQiuUserInfo.class,new TableListener()).sheet().doRead();
    }

    public static void synchronousRead(String fileName){
        // 这里需要指定读用哪个class去读，燃火读取第一个sheet 同步读取会自动dinash
        List<XingQiuUserInfo> totalDataList = EasyExcel.read(fileName).head(XingQiuUserInfo.class).sheet().doReadSync();
        for (XingQiuUserInfo xingQiuUserInfo : totalDataList){
            System.out.println(xingQiuUserInfo);
        }
    }

}
