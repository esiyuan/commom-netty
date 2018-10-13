package com.esiyuan.netty.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharSet;

import java.util.Arrays;

/**
 * @desc :
 * @author: guanjie
 */
@Slf4j
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

    public MessageDecoder() {
        super(Integer.MAX_VALUE, 0, RpcConstants.MSG_LENGTH, 0, RpcConstants.MSG_LENGTH);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        switch (frame.readByte()) {
            case RpcConstants.REQUEST_FLAG:
                String req = frame.toString(Charsets.UTF_8);
                return JSON.parseObject(req, Request.class);
            case RpcConstants.RESPONSE_FLAG:
                return JSON.parseObject(frame.toString(Charsets.UTF_8), Response.class);
            case RpcConstants.HEART_FLAG:
                return new HeartMsg(frame.readByte());
        }
        return null;
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer("12345".getBytes(Charsets.UTF_8));
        byteBuf.readByte();

        System.out.println(byteBuf.toString(Charsets.UTF_8));
    }
}
