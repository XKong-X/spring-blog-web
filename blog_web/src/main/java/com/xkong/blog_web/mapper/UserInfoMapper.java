package com.xkong.blog_web.mapper;

import com.xkong.blog_web.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 14:18
 * Version:
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 根据用户名查
     */
    @Select("select user_id,user_name,password,github_url,delete_flag,create_time,update_time " +
            "from user where user_name=#{userName} and delete_flag=0")
    UserInfo queryUserByName(String userName);

    /**
     * 根据用户 id 查
     */
    @Select("select user_id,user_name,password,github_url,delete_flag,create_time,update_time " +
            "from user where user_id=#{userId} and delete_flag=0")
    UserInfo queryUserById(Integer userId);
}
