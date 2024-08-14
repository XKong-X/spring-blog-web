package com.xkong.blog_web.config;

import com.xkong.blog_web.common.Constants;
import com.xkong.blog_web.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 17:55
 * Version:
 */
@ResponseBody
@ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler
    public Result errorHandler(Exception e) {
        Result result = new Result();
        result.setCode(Constants.RESULT_FAIL);
        result.setErrorMsg("发生内部错误!");
        return result;
    }
}
