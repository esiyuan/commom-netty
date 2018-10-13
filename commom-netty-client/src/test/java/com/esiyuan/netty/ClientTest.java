package com.esiyuan.netty;

import com.esiyuan.netty.client.NettyTcpClient;
import com.esiyuan.netty.client.intf.Callback;
import com.esiyuan.netty.util.Request;
import com.esiyuan.netty.util.Response;
import com.esiyuan.netty.util.URL;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ClientTest {
    /**
     * 测试同步消息
     */
    @Test
    public void testSyncMsg() throws InterruptedException {
        NettyTcpClient nettyTcpClient = NettyTcpClient.getInstance();
        while (true) {
            Request request = new Request();
            Response response = nettyTcpClient.request(new URL("localhost", 9000), request);
            System.out.println(response);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    /**
     * 测试异步回调
     */
    @Test
    public void testAsyncMsg() throws InterruptedException {
        NettyTcpClient nettyTcpClient = NettyTcpClient.getInstance();
        while (true) {
            System.out.println(Thread.currentThread().getName());
            Request request = new Request();
            nettyTcpClient.asynRequesCallback(new URL("localhost", 9000), request, new Callback() {
                @Override
                public void process(Object msg) {
                    System.out.println(Thread.currentThread().getName() + " - 异步处理消息 - " + msg);
                }
            });

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
