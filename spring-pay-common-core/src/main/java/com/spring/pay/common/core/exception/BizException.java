/*
 * ====================================================================
 * 龙果学院： www.roncoo.com （微信公众号：RonCoo_com）
 * 超级教程系列：《微服务架构的分布式事务解决方案》视频教程
 * 讲师：吴水成（水到渠成），840765167@qq.com
 * 课程地址：http://www.roncoo.com/details/7ae3d7eddc4742f78b0548aa8bd9ccdb
 * ====================================================================
 */
package com.spring.pay.common.core.exception;

import com.spring.pay.common.core.utils.ResultCode;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = -5875371379845226068L;

//    /**
//     * 数据库操作,insert返回0
//     */
//    public static final BizException DB_INSERT_RESULT_0 = new BizException(
//            10040001, "数据库操作,insert返回0");
//
//    /**
//     * 数据库操作,update返回0
//     */
//    public static final BizException DB_UPDATE_RESULT_0 = new BizException(
//            10040002, "数据库操作,update返回0");
//
//    /**
//     * 数据库操作,selectOne返回null
//     */
//    public static final BizException DB_SELECTONE_IS_NULL = new BizException(
//            10040003, "数据库操作,selectOne返回null");
//
//    /**
//     * 数据库操作,list返回null
//     */
//    public static final BizException DB_LIST_IS_NULL = new BizException(
//            10040004, "数据库操作,list返回null");
//
//    /**
//     * Token 验证不通过
//     */
//    public static final BizException TOKEN_IS_ILLICIT = new BizException(
//            10040005, "Token 验证非法");
//    /**
//     * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
//     */
//    public static final BizException SESSION_IS_OUT_TIME = new BizException(
//            10040006, "会话超时");
//
//    /**
//     * 生成序列异常时
//     */
//    public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(
//            10040007, "序列生成超时");

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public BizException() {
        super();
    }

    public BizException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public BizException(int errorCode, String errorMessage) {
        this.code = errorCode;
        this.msg = errorMessage;
    }

    public BizException(Throwable cause, int errorCode, String errorMessage) {
        super(errorMessage, cause);
        this.code = errorCode;
        this.msg = errorMessage;
    }

    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public String getMsg() {
        return "code=[" + this.code + "],message=[" + this.msg + "]";
    }

    public int getCode() {
        return code;
    }
}
