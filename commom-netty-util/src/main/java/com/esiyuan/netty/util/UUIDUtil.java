package com.esiyuan.netty.util;

import com.google.common.base.CharMatcher;

import java.util.UUID;

/**
 * @desc :
 * @author: 18010318
 * @date : 2018/10/12
 */
public class UUIDUtil {


    private static CharMatcher charMatcher = CharMatcher.is('-');


    public static String getUUid() {
        return charMatcher.removeFrom(UUID.randomUUID().toString());
    }
}
