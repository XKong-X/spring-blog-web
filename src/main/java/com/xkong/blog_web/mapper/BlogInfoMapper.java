package com.xkong.blog_web.mapper;

import com.xkong.blog_web.model.BlogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 14:29
 * Version:
 */
@Mapper
public interface BlogInfoMapper {
    /**
     * 根据用户 id 获取博客列表
     */
    @Select("select blog_id,title,content,user_id,delete_flag,create_time,update_time " +
            "from blog where  delete_flag=0 order by create_time desc")/*user_id=#{userId} and*/
    List<BlogInfo> queryBlogList(/*Integer userId*/);

    /**
     * 根据博客 id 获取博客详情
     */
    @Select("select blog_id,title,content,user_id,delete_flag,create_time,update_time " +
            "from blog where blog_id=#{blogId}")
    BlogInfo queryBlogById(Integer blogId);

    /**
     * 编辑博客
     */
    @Update("update blog set title=#{title},content=#{content} where blog_id=#{blogId}")
    Integer updateBlogById(BlogInfo blogInfo);

    /**
     * 根据博客 id 删除博客(逻辑删除)
     */
    @Update("update blog set delete_flag=1 where blog_id=#{blogId}")
    Integer deleteBlogById(Integer blogId);

    /**
     * 根据输入内容添加博客
     */
    @Insert("insert into blog(title,content,user_id) values(#{title},#{content},#{userId})")
    Integer insertBlog(BlogInfo blogInfo);
}
