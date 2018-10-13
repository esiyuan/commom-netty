package com.esiyuan.netty.server;

import com.esiyuan.netty.server.intf.ResponseHandlerIntf;
import com.esiyuan.netty.util.Request;

public class DefaultResponseHandlerImpl implements ResponseHandlerIntf {

    @Override
    public Object process(Request request) {
        return "hello world!";
    }
}
