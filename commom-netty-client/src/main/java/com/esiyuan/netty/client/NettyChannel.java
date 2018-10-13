package com.esiyuan.netty.client;

import com.esiyuan.netty.util.intf.ChannelInitializerFactory;
import com.esiyuan.netty.util.ChannelException;
import com.esiyuan.netty.util.Request;
import com.esiyuan.netty.util.URL;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
@Slf4j
public class NettyChannel {

    private volatile Channel channel;

    private static Properties properties;

    private static ChannelInitializerFactory<SocketChannel> channelChannelInitializerFactory;

    static {
        try {
            properties = new Properties();
            try {
                properties.load(ClassLoader.getSystemResourceAsStream("rpc.properties"));
                channelChannelInitializerFactory = (ChannelInitializerFactory<SocketChannel>) Class.forName(properties.getProperty("channelInitializerFactory")).newInstance();
            } catch (Exception e) {
                log.error("读取配置文件失败,进行默认配置", e);
                properties.load(NettyChannel.class.getResourceAsStream("init.properties"));
                channelChannelInitializerFactory = (ChannelInitializerFactory<SocketChannel>) Class.forName(properties.getProperty("channelInitializerFactory")).newInstance();
            }
        } catch (Exception e) {
            log.error("ChannelInitializer初始化失败", e);
            throw new RuntimeException(e);
        }
    }

    private URL url;

    public NettyChannel(URL url) {
        this.url = url;
    }

    public void request(Request request) {
        Channel channel = getChannel(url);
        if (channel == null || !channel.isActive()) {
            throw new ChannelException();
        }
        channel.writeAndFlush(request);
    }

    private ChannelFuture connect(URL url) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup(0));
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, false);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);
        bootstrap.handler(channelChannelInitializerFactory.newChannelInitializer());
        return bootstrap.connect(url.getHost(), url.getPort());
    }


    private Channel getChannel(URL url) {
        if (channel != null && channel.isActive()) {
            return channel;
        }
        try {
            channel = connect(url).sync().channel();
            log.info("重新建立连接 {} ", channel.isActive());
        } catch (InterruptedException e) {
            log.warn("连接中断", e);
            if (channel != null) {
                channel.close();
            }
        }
        return channel;
    }

}
