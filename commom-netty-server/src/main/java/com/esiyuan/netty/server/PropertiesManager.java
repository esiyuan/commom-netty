package com.esiyuan.netty.server;

import com.esiyuan.netty.server.intf.ResponseHandlerIntf;
import com.esiyuan.netty.util.intf.ChannelInitializerFactory;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class PropertiesManager {

    public static final PropertiesManager INSTANCE = new PropertiesManager();

    private ChannelInitializerFactory CHANNEL_INITIALIZER_FACTORY;
    private ResponseHandlerIntf RESPONSE_HANDLER_INTF;

    {
        try {
            Properties properties = new Properties();
            try {
                properties.load(ClassLoader.getSystemResourceAsStream("rpc.properties"));
                CHANNEL_INITIALIZER_FACTORY = (ChannelInitializerFactory<SocketChannel>) Class.forName(properties.getProperty("channelInitializerFactory")).newInstance();
                RESPONSE_HANDLER_INTF = (ResponseHandlerIntf) Class.forName(properties.getProperty("responseHandler")).newInstance();
            } catch (Exception e) {
                log.error("读取配置文件失败,进行默认配置", e);
                properties.load(NettyServer.class.getResourceAsStream("init.properties"));
                CHANNEL_INITIALIZER_FACTORY = (ChannelInitializerFactory<SocketChannel>) Class.forName(properties.getProperty("channelInitializerFactory")).newInstance();
                RESPONSE_HANDLER_INTF = (ResponseHandlerIntf) Class.forName(properties.getProperty("responseHandler")).newInstance();
            }
        } catch (Exception e) {
            log.error("ChannelInitializer初始化失败", e);
            throw new RuntimeException(e);
        }
    }

    private PropertiesManager() {
    }

    public ResponseHandlerIntf getResponseHandlerIntf() {
        return RESPONSE_HANDLER_INTF;
    }

    public ChannelInitializerFactory getChannelInitializerFactory() {
        return CHANNEL_INITIALIZER_FACTORY;
    }


}
