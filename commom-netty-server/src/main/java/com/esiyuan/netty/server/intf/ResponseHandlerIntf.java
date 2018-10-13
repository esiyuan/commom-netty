package com.esiyuan.netty.server.intf;

import com.esiyuan.netty.util.Request;

public interface ResponseHandlerIntf {

    Object process(Request request);

}
