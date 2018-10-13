package com.esiyuan.netty.server;

import com.esiyuan.netty.util.intf.ChannelInitializerFactory;
import com.esiyuan.netty.util.MessageDecoder;
import com.esiyuan.netty.util.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 提供的默认的初始化channelHandler工厂
 */
public class DefaultChannelInitializerFactory implements ChannelInitializerFactory<SocketChannel> {

    @Override
    public ChannelInitializer<SocketChannel> newChannelInitializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS), new MessageEncoder(), new MessageDecoder(), new ServerMsgHandler());
            }
        };
    }
}
