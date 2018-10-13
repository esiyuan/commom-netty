package com.esiyuan.netty.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
@Getter
@Setter
@ToString
public class Request {

    private String id;
    private String interfaceName;
    private String methodName;
    private Object[] args;
    private Class<?>[] parameterTypes;
}
