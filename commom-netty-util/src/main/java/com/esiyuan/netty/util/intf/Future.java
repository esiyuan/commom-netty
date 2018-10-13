package com.esiyuan.netty.util.intf;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
public interface Future<T> {

    boolean isDone();

    T get();


    void set(T t);
}
