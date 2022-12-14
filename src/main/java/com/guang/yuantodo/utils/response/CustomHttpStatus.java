package com.guang.yuantodo.utils.response;

public enum CustomHttpStatus {
    /**操作成功**/
    RC100(100,"操作成功"),
    /**操作失败**/
    RC999(999,"操作失败"),
    /**服务限流**/
    RC200(200,"服务开启限流保护,请稍后再试!"),
    /**服务降级**/
    RC201(201,"服务开启降级保护,请稍后再试!"),
    /**热点参数限流**/
    RC202(202,"热点参数限流,请稍后再试!"),
    /**系统规则不满足**/
    RC203(203,"系统规则不满足要求,请稍后再试!"),
    /**授权规则不通过**/
    RC204(204,"授权规则不通过,请稍后再试!"),
    /**access_denied**/
    RC403(403,"无访问权限,请联系管理员授予权限"),
    /**access_denied**/
    RC401(401,"匿名用户访问无权限资源时的异常"),

    /**参数校验不通过**/
    RC402(402,"参数校验异常"),

    /**服务异常**/
    RC500(500,"系统异常，请稍后重试"),

    INVALID_TOKEN(2001,"访问令牌不合法"),
    ACCESS_DENIED(2003,"没有权限访问该资源"),
    AUTHENTICATION_EXPIRE(1001,"token过期"),
    AUTHENTICATION_FAILED(1002,"token认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1003,"用户名或密码错误"),
    USERNAME_EXIST(1004,"该用户已存在"),
    USERNAME_NO_EXIST(1005,"该用户不存在"),
    UNSUPPORTED_GRANT_TYPE(1006, "不支持的认证模式");

    /**自定义状态码**/
    private final int code;
    /**自定义描述**/
    private final String message;

    CustomHttpStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}