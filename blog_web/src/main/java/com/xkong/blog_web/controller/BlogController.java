package com.xkong.blog_web.controller;

import com.xkong.blog_web.model.BlogInfo;
import com.xkong.blog_web.model.Result;
import com.xkong.blog_web.model.UserInfo;
import com.xkong.blog_web.service.BlogService;
import com.xkong.blog_web.service.UserService;
import com.xkong.blog_web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 19:51
 * Version:
 */
@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    /**
     * 根据用户 id 获取博客列表
     * @param userId
     * @return
     */
    @RequestMapping("/getBlogList")
    public List<BlogInfo> getBlogList(Integer userId) {
        return blogService.getBlogList(userId);
    }

    /**
     * 根据博客 id 获取博客详情/校验登录用户是否为作者
     * @param blogId
     * @param request
     * @return
     */
    @RequestMapping("/getBlogDetail")
    public BlogInfo getBlogDetail(Integer blogId, HttpServletRequest request) {
        BlogInfo blogInfo = blogService.getBlogDetail(blogId);
        // 获取登录用户的 id
        String token = request.getHeader("user_token");
        Integer userId = JwtUtils.getUserIdFromToken(token);
        if (userId != null && userId == blogInfo.getUserId()) {// 校验
            blogInfo.setIsLoginUser(true);
        }
        return blogInfo;
    }

    /**
     * 添加博客
     * @param title
     * @param content
     * @param request
     * @return
     */
    @RequestMapping("/addBlog")
    public Result addBlog(String title, String content, HttpServletRequest request) {
        String token = request.getHeader("user_token");
        Integer userId = JwtUtils.getUserIdFromToken(token);
        if (userId == null || userId < 1) {
            return Result.fail("用户身份非法!");
        }
        BlogInfo blogInfo = new BlogInfo(userId, title, content);
        blogService.insertBlog(blogInfo);
        return Result.success("success");
    }

    @RequestMapping("/updateBlog")
    public Boolean updateBlog(Integer blogId, HttpServletRequest request, String title, String content) {
        String token = request.getHeader("user_token");
        Integer userId = JwtUtils.getUserIdFromToken(token);
        if (userId == null || userId < 1) {
            return false;
        }

        // 校验用户身份(防止通过接口修改)
        UserInfo userInfo = userService.getAuthorInfo(blogId);
        if (userId != userInfo.getUserId()) {
            return false;
        }

        BlogInfo blogInfo = new BlogInfo(title, content);
        blogInfo.setBlogId(blogId);
        Integer updateLine = blogService.updateBlog(blogInfo);
        if (updateLine != 1) {
            return false;
        }
        return true;
    }

    @RequestMapping("/deleteBlog")
    public Boolean deleteBlog(Integer blogId, HttpServletRequest request) {
        String token = request.getHeader("user_token");
        Integer userId = JwtUtils.getUserIdFromToken(token);
        if (userId == null || userId < 1) {
            return false;
        }

        // 校验用户身份(防止通过接口修改)
        UserInfo userInfo = userService.getAuthorInfo(blogId);
        if (userId != userInfo.getUserId()) {
            return false;
        }

        Integer deleteLine = blogService.deleteBlog(blogId);
        if (deleteLine != 1) {
            return false;
        }
        return true;
    }
}
