package com.esiyuan.netty.server;

import com.esiyuan.netty.util.intf.ChannelInitializerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class NettyServer {
    private static final NettyServer NETTY_SERVER = new NettyServer();

    private NettyServer() {
    }

    public static NettyServer getInstance() {
        return NETTY_SERVER;
    }

    public ChannelFuture bind(String host, int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup(0);
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        ServerBootstrap s = new ServerBootstrap();
        s.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(getChannelInitializerFactory().newChannelInitializer());
        return s.bind(host, port);
    }


    private ChannelInitializerFactory<SocketChannel> getChannelInitializerFactory() {
        return PropertiesManager.INSTANCE.getChannelInitializerFactory();
    }
}
