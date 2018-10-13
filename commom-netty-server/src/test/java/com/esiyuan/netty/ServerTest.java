package com.esiyuan.netty;

import com.esiyuan.netty.server.NettyServer;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {
    @Test
    public void testServer() throws InterruptedException, IOException {
        NettyServer nettyServer = NettyServer.getInstance();
        nettyServer.bind("localhost", 9000).sync();
        System.in.read();
    }
}
