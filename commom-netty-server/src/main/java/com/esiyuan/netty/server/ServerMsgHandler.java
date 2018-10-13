package com.esiyuan.netty.server;

import com.esiyuan.netty.util.HeartMsg;
import com.esiyuan.netty.util.Request;
import com.esiyuan.netty.util.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过消息调用对应的方法
 *
 * @author: guanjie
 */
@Slf4j
public class ServerMsgHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("msg = {}", msg);
        if (msg instanceof Request) {
            Response response = new Response();
            response.setId(((Request) msg).getId());
            response.setResp(PropertiesManager.INSTANCE.getResponseHandlerIntf().process((Request) msg));
            ctx.writeAndFlush(response);
        } else if (msg instanceof HeartMsg) {
            ctx.writeAndFlush(new HeartMsg(HeartMsg.ACK));
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            log.info("当前通道已空闲30秒了，直接关闭！");
            ctx.channel().close();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
