package com.xkong.blog_web.service;

import com.xkong.blog_web.mapper.BlogInfoMapper;
import com.xkong.blog_web.mapper.UserInfoMapper;
import com.xkong.blog_web.model.BlogInfo;
import com.xkong.blog_web.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-30
 * Time: 18:50
 * Version:
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BlogInfoMapper blogInfoMapper;


    public UserInfo queryUserByName(String userName) {
        return userInfoMapper.queryUserByName(userName);
    }

    public UserInfo queryUserById(Integer userId) {
        return userInfoMapper.queryUserById(userId);
    }

    public UserInfo getAuthorInfo(Integer blogId) {
        BlogInfo blogInfo = blogInfoMapper.queryBlogById(blogId);
        if (blogInfo == null || blogInfo.getUserId() < 1) {
            return null;
        }
        return userInfoMapper.queryUserById(blogInfo.getUserId());
    }
}
