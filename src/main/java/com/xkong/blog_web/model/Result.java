package com.xkong.blog_web.model;

import com.xkong.blog_web.common.Constants;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-29
 * Time: 17:00
 * Version:
 */
@Data
public class Result<T> {
    private Integer code;// 1 成功, 0失败
    private String errorMsg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
//        result.code = Constants.RESULT_SUCCESS;
        result.setCode(Constants.RESULT_SUCCESS);
        result.setErrorMsg("");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String errorMsg) {
        Result<T> result = new Result<>();
        result.setCode(Constants.RESULT_FAIL);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public static <T> Result<T> fail(T data, String errorMsg) {
        Result<T> result = new Result<>();
        result.setCode(Constants.RESULT_FAIL);
        result.setErrorMsg(errorMsg);
        result.setData(data);
        return result;
    }
}
