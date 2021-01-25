package com.itwang.showpictures.model.dto;

import com.itwang.showpictures.common.enums.ResultStatusEnum;

/**
 * @ProjectName: myfamily
 * @Package: com.jikewang.myfamily.common
 * @ClassName: Result
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/9/2 17:22
 * @Version: 1.0
 */
public class Result<T> {

    /**
     * 状态码
     */
    private int code = ResultStatusEnum.SUCCESS.getCode();

    /**
     * 状态信息
     */
    private String msg = ResultStatusEnum.SUCCESS.getMessage();

    /**
     * 对外返回的对象
     */
    private T data;

    public Result() {
    }

    public Result(ResultStatusEnum status) {
        super();
        this.code = status.getCode();
        this.msg = status.getMessage();
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data, int code, String msg) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> error(ResultStatusEnum status) {
        return new Result<T>(status.getCode(), status.getMessage());
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> success() {
        return new Result<T>(null, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage());
    }

    public void setStatus(ResultStatusEnum status) {
        this.code = status.getCode();
        this.msg = status.getMessage();
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "data=" + data + " & code=" + code + " & msg=" + msg;
    }
}