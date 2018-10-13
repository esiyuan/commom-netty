package com.esiyuan.netty.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorUtil {

    private static Executor executor = Executors.newFixedThreadPool(5);


    public static void execute(Runnable r){
        executor.execute(r);
    }
}
