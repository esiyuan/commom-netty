package com.esiyuan.netty.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @desc :
 * @author: guanjie
 */
@Slf4j
public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] msgBytes = JSON.toJSONString(msg).getBytes(Charsets.UTF_8);
        out.writeInt(msgBytes.length + 1);
        if (msg instanceof Request) {
            out.writeByte(RpcConstants.REQUEST_FLAG);
        } else if (msg instanceof Response) {
            out.writeByte(RpcConstants.RESPONSE_FLAG);
        }
        out.writeBytes(msgBytes);
    }
}
