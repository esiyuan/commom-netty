package com.esiyuan.netty.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 心跳发送的信息
 */
@AllArgsConstructor
@ToString
@Getter
public class HeartMsg {

    public static final int SEND = 1;
    public static final int ACK = 2;

    private int val;
}
