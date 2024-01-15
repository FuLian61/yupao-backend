package com.yupi.yupao.once;
import java.util.Date;

import com.yupi.yupao.mapper.UserMapper;
import com.yupi.yupao.model.domain.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@Component
public class InsertUsers {
    @Resource
    private UserMapper userMapper;

    /**
     * 批量插入用户
     */
//    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        for (int i = 0; i < INSERT_NUM; i++){
            User user = new User();

            user.setUsername("假鱼皮");
            user.setUserAccount("fackyupi");
            user.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/y28fMjHEDbVusyFNiaKwKc2RG2s94kGccLTz4YJU918q7M3PwibsHmgWBS9elFuKTtaN6rWC8fKsGJjl8SMeYHCw/132");
            user.setGender(0);
            user.setProfile("");
            user.setUserPassword("12345678");
            user.setPhone("123");
            user.setEmail("123@12.com");
            user.setTags("[]");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("1111111");

            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
