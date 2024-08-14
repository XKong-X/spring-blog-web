package com.xkong.blog_web.mapper;

import com.xkong.blog_web.model.BlogInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 15:15
 * Version:
 */
@SpringBootTest
class BlogInfoMapperTest {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Test
    void queryBlogList() {
        System.out.println(blogInfoMapper.queryBlogList(/*1*/));
    }

    @Test
    void queryBlogById() {
        System.out.println(blogInfoMapper.queryBlogById(2));
    }

    @Test
    void updateBlogById() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("修改后的标题");
        blogInfo.setContent("修改后的正文");
        blogInfo.setBlogId(2);
        System.out.println(blogInfoMapper.updateBlogById(blogInfo));
    }

    @Test
    void deleteBlogById() {
        System.out.println(blogInfoMapper.deleteBlogById(3));
    }

    @Test
    void insertBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("新标题");
        blogInfo.setContent("新正文");
        blogInfo.setUserId(1);
        System.out.println(blogInfoMapper.insertBlog(blogInfo));
    }
}