package com.yupi.yupao.service;

import com.yupi.yupao.mapper.UserMapper;
import com.yupi.yupao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserService userService;

    // CPU密集型：分配的核心线程数 = CPU -2
    // IO密集型：分配的核心线程数可以大于CPU核数
    private ExecutorService executorService = new ThreadPoolExecutor(60,1000,10000, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10000));
    /**
     * 批量插入用户
     */
//    @Test
//    public void doInsertUsers(){
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 100000;
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < INSERT_NUM; i++){
//            User user = new User();
//            user.setUsername("假鱼皮");
//            user.setUserAccount("fackyupi");
//            user.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/y28fMjHEDbVusyFNiaKwKc2RG2s94kGccLTz4YJU918q7M3PwibsHmgWBS9elFuKTtaN6rWC8fKsGJjl8SMeYHCw/132");
//            user.setGender(0);
//            user.setProfile("大家好，我是假的鱼皮");
//            user.setUserPassword("12345678");
//            user.setPhone("123");
//            user.setEmail("123@12.com");
//            user.setTags("[]");
//            user.setUserStatus(0);
//            user.setUserRole(0);
//            user.setPlanetCode("1111111");
//            userList.add(user);
//        }
//        // 26s 10万条
//        userService.saveBatch(userList,10000);
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//    }
//
//    /**
//     * 并发批量插入用户
//     */
////    @Test
//    public void doConcurrencyInsertUsers(){
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 100000;
//        int bacthSize = 5000;
//        // 分十组
//        int j = 0;
//        List<CompletableFuture<Void>> futureList = new ArrayList<>();
//        for (int i = 0; i < 20; i++){
//            List<User> userList = new ArrayList<>();
//            while (true){
//                j++;
//                User user = new User();
//                user.setUsername("假鱼皮");
//                user.setUserAccount("fackyupi");
//                user.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/y28fMjHEDbVusyFNiaKwKc2RG2s94kGccLTz4YJU918q7M3PwibsHmgWBS9elFuKTtaN6rWC8fKsGJjl8SMeYHCw/132");
//                user.setGender(0);
//                user.setProfile("大家好，我是假的鱼皮");
//                user.setUserPassword("12345678");
//                user.setPhone("123");
//                user.setEmail("123@12.com");
//                user.setTags("[]");
//                user.setUserStatus(0);
//                user.setUserRole(0);
//                user.setPlanetCode("1111111");
//                userList.add(user);
//                if (j % bacthSize == 0){
//                    break;
//                }
//            }
//            // 异步操作
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//                System.out.println("threadName:" + Thread.currentThread().getName());
//                userService.saveBatch(userList, bacthSize);
//            },executorService);
//            futureList.add(future);
//        }
//       CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
//
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//    }
}
