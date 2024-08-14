package com.xkong.blog_web.controller;

import com.xkong.blog_web.model.Result;
import com.xkong.blog_web.model.UserInfo;
import com.xkong.blog_web.service.UserService;
import com.xkong.blog_web.utils.JwtUtils;
import com.xkong.blog_web.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-30
 * Time: 18:50
 * Version:
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    public Result login(String userName, String password) {
        // 校验参数是否正确传递并接收
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return Result.fail("用户名或密码为空!");
        }
        // 校验用户是否存在
//        System.out.println(userName);
        UserInfo userInfo = userService.queryUserByName(userName);
//        System.out.println(userInfo);
        if (userInfo == null || userInfo.getUserName() == null || userInfo.getUserId() < 1) {
            return Result.fail("用户不存在!");
        }
//        // 校验密码
//        if (!password.equals(userInfo.getPassword())) {
//            return Result.fail("密码错误!");
//        }
        if (!SecurityUtils.verify(password, userInfo.getPassword())) {
            return Result.fail("密码错误!");
        }
        // 生成 Token (即令牌)
        Map<String, Object> claim = new HashMap<>();
        claim.put("userName", userName);
        claim.put("userId", userInfo.getUserId());
        String token = JwtUtils.genToken(claim);
        return Result.success(token);
    }

    /**
     * 获取用户信息（用于加载头像等）
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("user_token");
        Integer userId = JwtUtils.getUserIdFromToken(token);
//        System.out.println(userId);
        if (userId == null) {
            return null;
        }
        return userService.queryUserById(userId);
    }

    /**
     * 获取博客作者信息
     */
    @RequestMapping("/getAuthorInfo")
    public UserInfo getAuthorInfo(Integer blogId) {
        if (blogId == null || blogId < 1) {
            return null;
        }
        return userService.getAuthorInfo(blogId);
    }
}
