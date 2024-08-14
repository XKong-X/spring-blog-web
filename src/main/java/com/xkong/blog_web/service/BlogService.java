package com.xkong.blog_web.service;

import com.xkong.blog_web.mapper.BlogInfoMapper;
import com.xkong.blog_web.model.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 19:53
 * Version:
 */
@Service
public class BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    public List<BlogInfo> getBlogList(Integer userId) {
        return blogInfoMapper.queryBlogList(/*userId*/);
    }

    public BlogInfo getBlogDetail(Integer blogId) {
        return blogInfoMapper.queryBlogById(blogId);
    }

    public Integer insertBlog(BlogInfo blogInfo) {
        return blogInfoMapper.insertBlog(blogInfo);
    }

    public Integer updateBlog(BlogInfo blogInfo) {
        return blogInfoMapper.updateBlogById(blogInfo);
    }

    public Integer deleteBlog(Integer blogId) {
        return blogInfoMapper.deleteBlogById(blogId);
    }
}
