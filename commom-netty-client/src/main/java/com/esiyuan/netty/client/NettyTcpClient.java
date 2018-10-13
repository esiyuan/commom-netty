package com.esiyuan.netty.client;

import com.esiyuan.netty.client.intf.Callback;
import com.esiyuan.netty.util.*;
import com.esiyuan.netty.util.intf.Future;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @desc : netty客户端工具类
 * @author: 18010318
 * @date : 2018/10/12
 */
public class NettyTcpClient {


    private ConcurrentMap<URL, NettyChannel> URL_CHANNEL_MAP = new ConcurrentHashMap();
    private static final NettyTcpClient NETTY_TCP_CLIENT = new NettyTcpClient();

    private NettyTcpClient() {
    }

    public static NettyTcpClient getInstance() {
        return NETTY_TCP_CLIENT;
    }

    public Response request(URL url, Request request) {
        return send(url, request).get();
    }

    private Future<Response> send(URL url, Request request) {
        request.setId(UUIDUtil.getUUid());
        NettyChannel nettyChannel = getNettyChannel(url);
        RequestFuture future = new RequestFuture();
        RequestFuture.saveFuture(request.getId(), future);
        nettyChannel.request(request);
        return future;
    }

    public void asynRequesCallback(final URL url, final Request request, final Callback callback) {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                callback.process(send(url, request).get());
            }
        });
    }

    private NettyChannel getNettyChannel(URL url) {
        NettyChannel nettyChannel = URL_CHANNEL_MAP.get(url);
        if (nettyChannel == null) {
            synchronized (this) {
                nettyChannel = URL_CHANNEL_MAP.get(url);
                if (nettyChannel == null) {
                    nettyChannel = new NettyChannel(url);
                    URL_CHANNEL_MAP.putIfAbsent(url, nettyChannel);
                }
            }
        }
        return nettyChannel;
    }


}
