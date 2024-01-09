package com.yupi.usercenter.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 导入星球用户到数据库
 */
public class ImportXingQiuUser {
    public static void main(String[] args) {
        String fileName = "D:\\code\\user-center-backend\\src\\main\\resources\\testExcel.xlsx";
        // 这里需要指定读用哪个class去读，燃火读取第一个sheet 同步读取会自动dinash
        List<XingQiuUserInfo> userInfoList = EasyExcel.read(fileName).head(XingQiuUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList);
        Map<String, List<XingQiuUserInfo>> listMap =
                userInfoList.stream()
                        .filter(userInfo -> StringUtils.isNoneEmpty(userInfo.getUsername()))
                        .collect(Collectors.groupingBy(XingQiuUserInfo::getUsername));
        System.out.println("不重复昵称数 = " + listMap.keySet().size());
    }
}
