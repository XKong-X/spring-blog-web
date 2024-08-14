package com.xkong.blog_web.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 15:16
 * Version:
 */
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void queryUserByName() {
        System.out.println(userInfoMapper.queryUserByName("zhangsan"));
    }

    @Test
    void queryUserById() {
        System.out.println(userInfoMapper.queryUserById(2));
    }
}