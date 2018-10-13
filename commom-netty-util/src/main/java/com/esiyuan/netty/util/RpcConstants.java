package com.esiyuan.netty.util;

/**
 * 全局常量类
 *
 * @author: guanjie
 */
public class RpcConstants {

    /**
     * 消息长度字段 的字节数
     */
    public static final int MSG_LENGTH = 4;
    /**
     * 消息成功的标志
     */
    public static final String RESULT_FLAG_SUCCESS = "success";
    /**
     * 消息失败的标志
     */
    public static final String RESULT_FLAG_FAILURE = "failure";

    /**
     * 请求标志
     */
    public static final int REQUEST_FLAG = 1;
    /**
     * 响应标志
     */
    public static final int RESPONSE_FLAG = 2;
    /**
     * 心跳标志
     */
    public static final int HEART_FLAG = 3;

}
