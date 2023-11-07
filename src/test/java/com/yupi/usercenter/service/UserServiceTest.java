package com.yupi.usercenter.service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试
 * @Author 浮涟
 * @Since 2023/11/7
 * @Version 1.0
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testDigest() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String result = DigestUtils.md5DigestAsHex(("abcd" + "mypasswprd").getBytes());
        System.out.println(result);
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("dogYupi");
        user.setUserAccount("123");
        user.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/y28fMjHEDbVusyFNiaKwKc2RG2s94kGccLTz4YJU918q7M3PwibsHmgWBS9elFuKTtaN6rWC8fKsGJjl8SMeYHCw/132");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount =  "yu";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount =  "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount =  "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        checkPassword = "123456789";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount =  "dogYupi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount =  "yupi";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);


    }
}