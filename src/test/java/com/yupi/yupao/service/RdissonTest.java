package com.yupi.yupao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yupao.config.RedissonConfig;
import com.yupi.yupao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 浮涟
 * @Since 2024/1/16
 * @Version 1.0
 */
@SpringBootTest
public class RdissonTest {

    @Resource
    private RedissonClient redissonClient;
    @Test
    void test(){
        // list 数据存在本地 JVM 内存中
        List<String> list = new ArrayList<>();
        list.add("yupi");
        list.get(0);
        System.out.println("list:" + list.get(0));
        list.remove(0);

        // 数据存在 redis 内存中
        RList<String> rList = redissonClient.getList("test-list");
//        rList.add("yupi");
        rList.get(0);
        System.out.println("rlist:" + rList.get(0));
        rList.remove(0);

        // map
        RMap<Object, Object> map = redissonClient.getMap("test-map");
        map.put("","");
        // set
    }

    @Test
    void testWatchDog(){
        RLock lock = redissonClient.getLock("yupao:precachejob:docache:lock");
        try {
            if(lock.tryLock(0,-1, TimeUnit.MILLISECONDS)){
                Thread.sleep(300000);
                System.out.println("getLock" + Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()){
                System.out.println("unLock" + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
