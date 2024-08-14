package com.xkong.blog_web.model;

import com.xkong.blog_web.utils.TimeUtils;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 14:13
 * Version:
 */
@Data
public class BlogInfo {
    private Integer blogId;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
    private Boolean isLoginUser = false;

    public String getCreateTime() {
        return TimeUtils.formatTime(this.createTime);
    }

    public BlogInfo(Integer userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public BlogInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BlogInfo() {}

}
