package com.esiyuan.netty.client.msg;

import com.esiyuan.netty.util.HeartMsg;
import com.esiyuan.netty.util.RequestFuture;
import com.esiyuan.netty.util.Response;
import com.sun.org.apache.regexp.internal.RE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Response) {
            RequestFuture.remove(((Response) msg).getId()).set((Response) msg);
        } else if (msg instanceof HeartMsg) {
            log.info("心跳回应 {}", ((HeartMsg) msg).getVal());
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelRegistered");
        super.channelRegistered(ctx);
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.channel().close();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        log.info("", cause);
    }
}
