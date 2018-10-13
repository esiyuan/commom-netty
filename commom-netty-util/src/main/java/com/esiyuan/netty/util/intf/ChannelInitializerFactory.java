package com.esiyuan.netty.util.intf;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public interface ChannelInitializerFactory<T extends Channel> {

    ChannelInitializer<T> newChannelInitializer();
}
