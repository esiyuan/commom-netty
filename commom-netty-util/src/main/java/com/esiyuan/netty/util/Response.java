package com.esiyuan.netty.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
@Setter
@Getter
@ToString
public class Response {
    private String id;

    private Object resp;
}
