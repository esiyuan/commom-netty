package com.esiyuan.netty.util;

import com.esiyuan.netty.util.intf.Future;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
public class RequestFuture implements Future<Response> {
    private static ConcurrentMap<String, RequestFuture> REQUEST_FUTURE_MAP = new ConcurrentHashMap();

    private Response response;

    @Override
    public boolean isDone() {
        return response != null;
    }

    @Override
    public Response get() {
        synchronized (this) {
            while (response == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return response;
    }

    @Override
    public void set(Response response) {
        synchronized (this) {
            this.response = response;
            notifyAll();
        }
    }

    public static void saveFuture(String id, RequestFuture requestFuture) {
        REQUEST_FUTURE_MAP.putIfAbsent(id, requestFuture);
    }

    public static RequestFuture remove(String id) {
        return REQUEST_FUTURE_MAP.remove(id);
    }
}
