package com.itwang.showpictures.common.enums;

/**
 * @ProjectName: myfamily
 * @Package: com.jikewang.myfamily.common.enums
 * @ClassName: ResultStatusEnum
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/9/2 16:55
 * @Version: 1.0
 */
public enum  ResultStatusEnum {
    /**
     * 正常返回 返回状态 0
     */
    SUCCESS(0,"返回成功"),

    /**
     * 返回状态 1 用户会话信息不存在，提示重新登录
     */
    RELOAD(1,"请重新登录"),

    /**
     * 返回状态 2 错误 如果是数据错误就在message中写明错误原因 不是则写上后端服务器报错
     */
    ERROR(2,"错误请仔细查看参数是否正确"),

    /**
     * 返回状态99 未知错误
     */
    UNKNOWN_ERROR(99, "未知错误");

    private int code;
    private  String message;

    ResultStatusEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
